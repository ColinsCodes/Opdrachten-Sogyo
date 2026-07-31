import java.util.InputMismatchException;
import java.util.Scanner;

import Exceptions.UnplayablePocketException;
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
            System.out.println(newLine + "The current gameboard:");
            gameBoard(pocket1);
            System.out.println("It is player " + sharedData.getPlayerTurn() + "'s turn." + newLine);
            try {
                pocket1.nextPocket(getInput() - 1 + 7 * (sharedData.getPlayerTurn() - 1)).playPocket();
            } catch (UnplayablePocketException e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
            if (sharedData.isGameEnd()) {
                game = false;
            }
        }
        System.out.println("The game is over!" + newLine + "The game has been ended by player " + sharedData.getPlayerTurn());
        System.out.println("The current scores are:");
        System.out.println("Player 1: " + sharedData.getScorePlayer1());
        System.out.println("Player 2: " + sharedData.getScorePlayer2());
        switch (sharedData.getWinner()) {
            case 1 -> {
                System.out.println("Congratulations to player 1 for winning the game!");
            }
            case 2 -> {
                System.out.println("Congratulations to player 2 for winning the game!");
            }
            case 3 -> {
                System.out.println("The game ends in a tie (womp womp).");
            }
            default -> {
                System.out.println("The game has not ended yet. How did you get here?");
            }
        }
        String finalScreen = scanner.nextLine();
    }
    public void gameBoard(MancalaPocketGeneric pocket1) {
        System.out.print("  ");
        for (int i = 12; i>6; i--) {
            System.out.print(pocket1.nextPocket(i).getStones() + " ");
        }
        System.out.println(" Player 2");
        System.out.println(pocket1.nextPocket(13).getStones() + "             " + pocket1.nextPocket(6).getStones());
        System.out.print("  ");
        for (int i = 0; i<6; i++) {
            System.out.print(pocket1.nextPocket(i).getStones() + " ");
        }
        System.out.println(" Player 1");

    }
    public int getInput() {
        boolean input = false;
        int john = 0;
        while (!input) {
            try {
                john = scanner.nextInt();
                input = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please input a number between 1 and 6.");
                scanner.nextLine();
            }
        }
        return john;
    }
}
