import java.util.InputMismatchException;
import java.util.Scanner;
import nl.sogyo.javaopdrachten.*;

class MancalaRunner {
    private final Scanner scanner = new Scanner(System.in);
    void main(String[] args) {
        String newLine = System.lineSeparator();
        MancalaFaçade Façade = new MancalaFaçade();
        Façade.initializeGame();
        System.out.println("Welcome to Mancala! Please decide who is player 1.");
        while (!Façade.isGameEnd()) {
            System.out.println(newLine + "The current gameboard:");
            gameBoard(Façade.gameState());
            System.out.println("It is player " + Façade.gameTurn() + "'s turn." + newLine);
            Exception e = Façade.playPocket(getInput());
            if (e != null) {
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
            case -1 -> {
                System.out.println("Congratulations to player 2 for winning the game!");
            }
            case 0 -> {
                System.out.println("The game ends in a tie (womp womp).");
            }
            default -> {
                System.out.println("The game has not ended yet. How did you get here?");
            }
        }
        System.out.println("Press ENTER to exit the program.");
        String PauseFinalScreen = scanner.nextLine();
    }
    public int getInput() {
        boolean gotInput = false;
        int input = 0;
        while (!gotInput) {
            try {
                input = scanner.nextInt();
                gotInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please input a number between 1 and 13.");
                scanner.nextLine();
            }
        }
        return input;
    }
    public void gameBoard(int[] gameState) {
        System.out.print("  ");
        for (int i = 12; i>6; i--) {
            System.out.print(gameState[i] + " ");
        }
        System.out.println(" Player 2");
        System.out.println(gameState[13] + "             " + gameState[6]);
        System.out.print("  ");
        for (int i = 0; i<6; i++) {
            System.out.print(gameState[i] + " ");
        }
        System.out.println(" Player 1");
    }
}
