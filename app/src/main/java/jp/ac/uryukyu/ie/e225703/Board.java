package jp.ac.uryukyu.ie.e225703;
import java.util.ArrayList;

public class Board implements CommonConst{
    //ゲーム実行中フラグ
    public boolean game = true;
    
    String[][] board = new String[8][8];
    final static String BLACK = "⚫️";
    final static String WHITE = "⚪️";
    final static String EMPTY = "🟩";
    ArrayList<int[]> komaList;

    static String stone;
    static String rev_stone;

    boolean isput = false;

    public Board() {
        initialize();
        this.komaList = new ArrayList<>();
    }

    public void initialize() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                board[i][j] = EMPTY;
            }
        }

        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;

        //次うつ駒の色を指定
        stone = BLACK;
        rev_stone = WHITE;

        //ゲーム実行中フラグ
        game = true;
    }

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
                System.out.print(" ");
      
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
            System.out.println(stone + "ゲーム終了！");
            game = false;
        }
    }

    public void setStone(int x, int y) {
        x = x-1;
        y = y-1;

        canPut(x, y);
    }



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
                // 次うつ駒の設定
                String next_rev_storn = stone;
                stone = rev_stone;
                rev_stone = next_rev_storn;
            
                // オセロ版の描写
                display();

                this.isput = true;
        
            } else {
                // 既に駒がおいてある位置を指定した場合
                System.out.println("その位置に駒はおけません");
                break;
            }
        }
    }

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

    public void turnStone(int x, int y) {
            board[y][x] = stone;
            for(int[] coordinate : komaList) {
                int row = coordinate[0];
                int column = coordinate[1];
                board[row][column] = stone;
            }
            this.komaList = new ArrayList<>();
    }

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
}
    

    