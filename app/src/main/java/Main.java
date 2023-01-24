import jp.ac.uryukyu.ie.e225703.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //StartView view = new StartView();
        Facilitator facilitator = new Facilitator();
        System.out.println("遊ぶ方法を選んでください");
        System.out.println("プレイヤーのみで遊ぶ場合、\"0\"を入力");
        System.out.println("コンピュータと遊ぶ場合、\"1\"を入力");
        int s = new Scanner(System.in).nextInt();
        if (s == 0) {
            facilitator.start();
        } else if (s == 1); {
            facilitator.vsComputer();
        }
        

    }
}
