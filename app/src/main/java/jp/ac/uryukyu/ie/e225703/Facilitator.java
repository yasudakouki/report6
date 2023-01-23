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

        
    }

    public void choose() {
        Scanner s = new Scanner(System.in);

    }
}
