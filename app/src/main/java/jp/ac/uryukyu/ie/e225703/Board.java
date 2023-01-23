package jp.ac.uryukyu.ie.e225703;
import java.util.ArrayList;

public class Board implements CommonConst{
    //ゲーム実行中フラグ
    public boolean game = true;
    
    String[][] board = new String[8][8];
    final static String BLACK = "⚫️";
    final static String WHITE = "⚪️";
    final static String EMPTY = "🟩";
    ArrayList<Integer> komaList;

    static String stone;
    static String rev_stone;

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
            System.out.println(" －-－-－-－-－-－-－-－ ");   
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
}
    