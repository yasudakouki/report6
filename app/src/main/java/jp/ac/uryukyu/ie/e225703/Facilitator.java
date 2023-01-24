package jp.ac.uryukyu.ie.e225703;
import java.util.Scanner;

/**
 * ゲームを進行するためのクラス
 */
public class Facilitator {
    Board board;
    Computer computer;
    
    /**
     * コンストラクタ。ボードとコンピュータを用意。
     */
    public Facilitator() {
        this.board = new Board();
        this.computer = new Computer();
    }
    /**
     * 対人専用で遊ぶ時のためのメソッド。
     */
    public void start() {
        board.initialize();
        board.display();

        Scanner s = new Scanner(System.in);

        while(board.getGame() == true){

            System.out.print("ヨコの座標を入力してください:");
            int x = s.nextInt();

            System.out.print("タテの座標を入力してください:");
            int y = s.nextInt();

            board.setStone(x, y);
        }

        s.close();
    }
    /**
     * コンピュータと遊ぶ時のためのメソッド
     */
    public void vsComputer() {
        board.initialize();
        board.display();

        Scanner s = new Scanner(System.in);

        while(board.getGame() == true){

            System.out.print("ヨコの座標を入力してください:");
            int x = s.nextInt();

            System.out.print("タテの座標を入力してください:");
            int y = s.nextInt();

            board.setStone(x, y);
            try {
                Thread.sleep(2*1000);
                computer.addBoard(board);
                computer.putComputer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            

        }

        s.close();
    }
}
