package jp.ac.uryukyu.ie.e225703;
import java.util.Scanner;

public class Facilitator {
    Board board;
    Player player;
    Computer computer;
    
    public Facilitator() {
        this.board = new Board();
        this.player = new Player();
        this.computer = new Computer();
    }
    public void start() {
        board.initialize();
        board.display();

        Scanner s = new Scanner(System.in);

        while(board.game){

            System.out.print("ヨコの座標を入力してください:");
            int x = s.nextInt();

            System.out.print("タテの座標を入力してください:");
            int y = s.nextInt();

            board.setStone(x, y);
        }

        s.close();
    }
}
