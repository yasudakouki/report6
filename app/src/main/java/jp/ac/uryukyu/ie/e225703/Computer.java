package jp.ac.uryukyu.ie.e225703;
import java.util.ArrayList;
import java.util.Random;

public class Computer implements CommonConst{
    Board comboard;
    ArrayList<int[]> candidate;


    public Computer() {
        this.comboard = new Board();
        this.candidate = new ArrayList<>();
    }

    public void addBoard(Board _board) {
        this.comboard = _board;
    }

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
