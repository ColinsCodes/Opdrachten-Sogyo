import java.util.InputMismatchException;
import java.util.Scanner;

import Exceptions.UnplayablePocketException;
import nl.sogyo.javaopdrachten.*;

class MancalaRunner {
    private final Scanner scanner = new Scanner(System.in);
    boolean pocketsSet = false;
    void main(String[] args) {
        String newLine = System.lineSeparator();
        System.out.println("How many pockets would you like to create on each side?");
        MancalaFaçade Façade = new MancalaFaçade(getInput());
        System.out.println("Welcome to Mancala! Please decide who is player 1.");
        while (!Façade.isGameEnd()) {
            System.out.println(newLine + "The current gameboard:");
            gameBoard(Façade.gameState());
            System.out.println("It is player " + Façade.gameTurn() + "'s turn." + newLine);
            try {
                Façade.playPocket(getInput());
            } catch (UnplayablePocketException | InputMismatchException e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }
        System.out.println("The game is over!" + newLine + "The game has been ended by player " + Façade.gameTurn());
        System.out.println("The current scores are:");
        System.out.println("Player 1: " + Façade.getScore(1));
        System.out.println("Player 2: " + Façade.getScore(2));
        switch (Façade.getWinner()) {
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
    }
    public int getInput() {
        boolean gotInput = false;
        int input = 0;
        while (!gotInput) {
            try {
                input = scanner.nextInt();
                if ((input<1) || ((input > 99) && !pocketsSet)) {
                    throw new InputMismatchException("");
                }
                gotInput = true;
                if (!pocketsSet) {
                    pocketsSet = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please input a number between 0 and 99.");
                scanner.nextLine();
            }
        }
        return input;
    }
    public void gameBoard(int[] gameState) {
        System.out.print("  ");
        for (int i = gameState.length - 2; i>gameState.length/2 - 1; i--) {
            System.out.print(gameState[i] + " ");
        }
        System.out.println(" Player 2");
        System.out.println(gameState[gameState.length - 1] + " ".repeat(gameState.length) + gameState[gameState.length / 2 - 1]);
        System.out.print("  ");
        for (int i = 0; i< gameState.length / 2 -1; i++) {
            System.out.print(gameState[i] + " ");
        }
        System.out.println(" Player 1");
    }
}
