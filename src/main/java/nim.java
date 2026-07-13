import java.util.InputMismatchException;
import java.util.Scanner;

class InputHandler {
    public static int playerCount = 0;
    public static int matches = 11;
    public static int currentPlayer;
    public static int i;
    public int getValidInput(Scanner userInput){
        int count = 0;
        try {
            count = userInput.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Error: Not an integer.");
            userInput.nextLine();
        }
        return count;
    }
    public boolean moveChecker(int removeMatches){
        if (removeMatches <= matches && removeMatches < 5 && removeMatches > 0){
            matches = matches - removeMatches;
            return true;
        } else if (removeMatches > matches) {
            System.out.println("Error: There are only " + matches + " matches left.");
            return false;
        } else {
            System.out.println("You can only remove 1-4 matches");
            return false;
        }
    }
    public void compMove(int currentMatches){
        matches = currentMatches;
        for(i = 1; i<5; i++) {
            if (InputHandler.matches - i == 1 || InputHandler.matches - i == 6) {
                InputHandler.matches = InputHandler.matches - i;
                System.out.println("The computer removes " + i + " matches.");
                i = 10;
            }
        }
        if(i == 5){
            InputHandler.matches = InputHandler.matches - 1;
            System.out.println("The computer removes 1 match.");
        }
    }
}

public class nim {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        InputHandler handledInput = new InputHandler();
        InputHandler removeMatch = new InputHandler();
        String newLine = System.lineSeparator();
        System.out.println("Welcome to nim!" + newLine + "1 or 2 players?");
        boolean numInput = false;
        while(!numInput) {
            InputHandler.playerCount = handledInput.getValidInput(userInput);
            if (InputHandler.playerCount > 0 && InputHandler.playerCount < 3) {
                numInput = true;
            } else {
                System.out.println("Please input a valid number of players (1 or 2).");
            }
        }
        boolean game = true;
        while(game) {
            InputHandler.currentPlayer = 1;
            System.out.println("It is player 1's turn." + newLine + "There are " + InputHandler.matches + " matches. How many to remove?");
            numInput = false;
            while (!numInput){
                numInput = handledInput.moveChecker(handledInput.getValidInput(userInput));
            }

            if (InputHandler.playerCount == 2 && InputHandler.matches != 0) { //player 2's turn
                InputHandler.currentPlayer = 2;
                System.out.println("It is player 2's turn." + newLine + "There are " + InputHandler.matches + " matches. How many to remove?");
                numInput = false;
                while (!numInput){
                    numInput = handledInput.moveChecker(handledInput.getValidInput(userInput));
                }

            } else if (InputHandler.matches != 0) {
                InputHandler.currentPlayer = 3;
                handledInput.compMove(InputHandler.matches);
            }

            if(InputHandler.matches == 0){
                game = false;
            }
        }

        System.out.print("The last match has been lit!" + newLine + "The last match was drawn by: ");
        if(InputHandler.currentPlayer == 1){
            System.out.println("Player 1");
            if(InputHandler.playerCount == 2) {
                System.out.println("The game was won by Player 2!");
            } else {
                System.out.println("You've lost the game!");
            }
        } else if (InputHandler.currentPlayer == 2) {
            System.out.println("Player 2" + newLine + "The game was won by Player 1!");
        } else {
            System.out.println("Computer" + newLine + "Impressive! You've beaten the ultimate challenge and WON the game!");
        }
    }
}
