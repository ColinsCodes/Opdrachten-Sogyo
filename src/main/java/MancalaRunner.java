import java.util.Scanner;
import nl.sogyo.javaopdrachten.*;
public class MancalaRunner {
    private boolean game = true;
    private String newLine = System.lineSeparator();
    private Scanner scanner = new Scanner(System.in);
    public void main(String[] args) {
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        sharedData.loopCloser(pocket1);
        System.out.println("Welcome to Mancala! Please decide who is player 1.");
        while (game) {
            System.out.println("The current gameboard:");
            gameBoard(pocket1);
        }
    }
    public void gameBoard(MancalaPocketGeneric pocket1) {
        System.out.println(pocket1.nextPocket(1).getStones() + pocket1.getStones());
        for (int i = 13; i>7; i--) {
            System.out.print(pocket1.nextPocket(i).getStones() + " ");
        }

    }

}
