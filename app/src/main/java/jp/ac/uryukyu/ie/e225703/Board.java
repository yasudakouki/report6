package jp.ac.uryukyu.ie.e225703;
import java.util.ArrayList;

/**
 * オセロボードクラス。
 * 
 * ボードを用意すること、オセロの駒を置くことが可能
 */
public class Board implements CommonConst{
    /** 
     * ゲームが続けられるか判断するためのboolean。trueなら継続、falseなら終了。
     */
    private boolean game = true;

    /** 
     * 各マスの状態を保存するリスト 
     */
    String[][] board = new String[8][8];

    /**
     * 挟まれた駒を保存するリスト。
     */
    ArrayList<int[]> komaList;

    /**
     * 現在の駒の色を保存する。
     */
    String stone;

    /**
     * 次の駒の色を保存する。
     */
    String rev_stone;

    /**
     * 駒を置いたのかを判断するboolean。置いたらtrueになる。
     */
    private boolean isput = false;

    /**
     * コンストラクタ。ボードを初期化する。
     */
    public Board() {
        initialize();
        this.komaList = new ArrayList<>();
    }

    /**
     * 初期化用メソッド
     */
    public void initialize() {
        for (int i=0; i<BOARD_SIZE; i++) {
            for (int j=0; j<BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }

        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;

        stone = BLACK;
        rev_stone = WHITE;

        setGame(true);;
    }

    /**
     * オセロボードをターミナル上に表示する。
     */
    public void display() {
        //まだ空いている座標があるか
        boolean existempty = false;
        //黒い駒の数集計用
        int cnt_black = 0;
        //白い駒の数集計用
        int cnt_white = 0;
        
        int x = 0;
        System.out.println("   １ ２ ３ ４ ５ ６ ７ ８ ");
        System.out.println("   －-－-－-－-－-－-－-－ ");
        for (String[] row : board) {
            System.out.print(x+1 + "| ");
            for (String column : row) {
      
                System.out.print(column);
                System.out.print("|");
      
                //空いている座標があるか、各駒数の集計
                if (column.equals(EMPTY)) {
                    existempty = true;
                } else if (column.equals(BLACK)) {
                    cnt_black++;
                } else if (column.equals(WHITE)) {
                    cnt_white++;
                }
      
            }
            System.out.println();
            System.out.println("   －-－-－-－-－-－-－-－ ");   
            x++;
        }
        System.out.println(BLACK + ":" + cnt_black);
        System.out.println(WHITE + ":" + cnt_white);
        System.out.println("――――――――――――――");

        if (existempty) {
            System.out.println(stone + "のターンです");
        } else {
            System.out.println("ゲーム終了！");
            if (cnt_black > cnt_white) {
                System.out.println(BLACK + "の勝ち！");
            } else if (cnt_black < cnt_white) {
                System.out.println(WHITE + "の勝ち！");
            } else if (cnt_black == cnt_white) {
                System.out.println("引き分け！");
            }
            setGame(false);;
        }
    }

    /**
     * 入力された座標のマスに駒を置くことを検討する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの縦の座標
     */
    public void setStone(int x, int y) {
        x = x-1;
        y = y-1;

        canPut(x, y);
    }

    /**
     * 指定された座標のマスに駒が置けるかどうか判断する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの縦の座標
     */
    public void canPut(int x, int y) {
        this.isput = false;
        while(isput == false){
            if (x > 7 || y > 7) {
                System.out.println("その位置に駒はおけません");
                break;
            }
            if (board[y][x].equals(EMPTY)) {
                check(x, y);
                if (komaList.size() != 0 ){
                    turnStone(x, y);
                } else {
                    System.out.println("その位置に駒はおけません");
                    break;
                }

                String next_rev_storn = stone;
                stone = rev_stone;
                rev_stone = next_rev_storn;
            
                display();

                this.isput = true;
        
            } else {
                System.out.println("その位置に駒はおけません");
                break;
            }
        }
    }

    /**
     * 指定されたますの周囲(8方向)の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの縦の座標
     */
    public void check(int x, int y) {
        canPutUp(x, y);
        canPutUpRight(x, y);
        canPutRight(x, y);
        canPutDownRight(x, y);
        canPutDown(x, y);
        canPutDownLeft(x, y);
        canPutLeft(x, y);
        canPutUpLeft(x, y);
    }

    /**
     * 指定されたマスでひっくり返すことができる駒を自分の駒にする。
     * @param x オセロボードの横の座標
     * @param y オセロボードの縦の座標
     */
    public void turnStone(int x, int y) {
            board[y][x] = stone;
            for(int[] coordinate : komaList) {
                int row = coordinate[0];
                int column = coordinate[1];
                board[row][column] = stone;
            }
            this.komaList = new ArrayList<>();
    }

    /**
     * 指定されたますの上の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutUp(int x, int y) {
        if(y > 1) {
            String next = board[y-1][x];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((y-i < 0) || (board[y-i][x].equals(EMPTY))) {
                        break;
                    } else if (board[y-i][x].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y-t, x};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            }
        }
    }

    /**
     * 指定されたますの右上の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutUpRight(int x, int y) {
        if ((y>1) && (x<6)) {
            String next = board[y-1][x+1];
            
            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((x+i > 7) || (y-i < 0) || (board[y-i][x+i].equals(EMPTY))) {
                        break;
                    } else if (board[y-i][x+i].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y-t, x+t};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            }
        }
    }

    /**
     * 指定されたますの右の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutRight(int x, int y) {
        if (x < 6) {
            String next = board[y][x+1];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((x+i > 7) || ((board[y][x+i].equals(EMPTY)))) {
                        break;
                    } else if (board[y][x+i].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y, x+t};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            }
        }
    }

    /**
     * 指定されたますの右下の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutDownRight(int x, int y) {
        if ((y < 6) && (x < 6)) {
            String next = board[y+1][x+1];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((x+i > 7) || (y+i > 7) || (board[y+i][x+i].equals(EMPTY))) {
                        break;
                    } else if (board[y+i][x+i].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y+t, x+t};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            } 
        }
    }

    /**
     * 指定されたますの下の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutDown(int x, int y) {
        if (y < 6) {
            String next = board[y+1][x];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((y+i > 7) || ((board[y+i][x].equals(EMPTY)))) {
                        break;
                    } else if (board[y+i][x].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y+t, x};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            }
        }
    }

    /**
     * 指定されたますの左下の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutDownLeft(int x, int y) {
        if ((y < 6) && (x > 1)) {
            String next = board[y+1][x-1];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((x-i < 0) || (y+i > 7) || (board[y+i][x-i].equals(EMPTY))) {
                        break;
                    } else if (board[y+i][x-i].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y+t, x-t};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            } 
        }
    }

    /**
     * 指定されたますの左の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutLeft(int x, int y) {
        if (x > 1) {
            String next = board[y][x-1];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((x-i < 0) || ((board[y][x-i].equals(EMPTY)))) {
                        break;
                    } else if (board[y][x-i].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y, x-t};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            }
        }
    }

    /**
     * 指定されたますの左上の駒の状態を確認する。
     * @param x オセロボードの横の座標
     * @param y オセロボードの横の座標
     */
    public void canPutUpLeft(int x, int y) {
        if ((y > 1) && (x > 1)) {
            String next = board[y-1][x-1];

            if (next.equals(rev_stone)) {
                for (int i = 2; true; i++) {
                    if ((x-i < 0) || (y-i < 0) || (board[y-i][x-i].equals(EMPTY))) {
                        break;
                    } else if (board[y-i][x-i].equals(stone)) {
                        for (int t=1; t<i; t++) {
                            int[] coordinate = {y-t, x-t};
                            this.komaList.add(coordinate);
                        }
                    }
                }
            } 
        }
    }

    /**
     * ゲームの状態を設定するsetter
     * @param _game ゲームの状態
     */
    public void setGame(boolean _game) {
        this.game = _game;
    }
    /**
     * ゲームの状態を返すgetter
     */
    public boolean getGame() {
        return this.game;
    }
}
    

    