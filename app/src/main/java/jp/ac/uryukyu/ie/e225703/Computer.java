package jp.ac.uryukyu.ie.e225703;
import java.util.ArrayList;
import java.util.Random;

/**
 * 自動でボードの置ける場所に駒を置いてくれるコンピュータクラス。
 */
public class Computer implements CommonConst{
    Board comboard;
    ArrayList<int[]> candidate;

    /**
     * コンストラクタ。
     * ボードの初期化とボードの置けるマスのリストの初期化をする。
     */
    public Computer() {
        this.comboard = new Board();
        this.candidate = new ArrayList<>();
    }

    /**
     * ボードの情報を受け取る。
     * @param _board ボードの状態
     */
    public void addBoard(Board _board) {
        this.comboard = _board;
    }

    /**
     * すべてのボードに置けるマスからランダムに一つのマスに駒を置く。
     */
    public void putComputer() {
        for (int i=0; i<BOARD_SIZE; i++) {
            for (int j=0; j<BOARD_SIZE; j++) {
                if (comboard.board[j][i].equals(EMPTY)) {
                    comboard.check(i, j);
                    if (comboard.komaList.size() != 0) {
                        int[] zahyou = {i, j};
                        candidate.add(zahyou);
                        comboard.komaList = new ArrayList<>();
                    }
                }
            }
        }
        if (candidate.size() != 0) {
            int num = new Random().nextInt(candidate.size());
            int[] chosenCoordinate = candidate.get(num);
            comboard.canPut(chosenCoordinate[0], chosenCoordinate[1]);
            
            
            // comboard.turnStone(chosenCoordinate[0], chosenCoordinate[1]);
            // String next_rev_storn = comboard.stone;
            // comboard.stone = comboard.rev_stone;
            // comboard.rev_stone = next_rev_storn;
            // comboard.display();
        } 
        this.candidate = new ArrayList<>();
    }



}
