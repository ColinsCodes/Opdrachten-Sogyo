import java.util.Scanner;
class Validator{
    public boolean nameVal(String username){
        if(username.matches("[a-zA-Z]+") && username.length() >= 8 && username.length() <= 32){
            String databaseEntryUN = username;      //add username to database (not in this assignment)
            return true;
        } else {
            return false;
        }
    }
    public boolean pwVal(String password){
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$") && password.length() >= 10 && password.length() <= 32) {
            String databaseEntryPW = password;      //add password to database (not in this assignment)
            return true;
        } else {
            return false;
        }
    }
}

public class exceptionalUser {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        Validator inputValidation = new Validator();
        System.out.println("Enter desired username (8-32 characters; letters only)");
        boolean validName = false;
        while (!validName){
            String nameInput = userInput.nextLine();
            validName = inputValidation.nameVal(nameInput);
            if(!validName){
                System.out.println("Invalid username. Please re-read rules.");
            }

        }
        System.out.println("Username set.");
        System.out.println("Enter desired password (10-32 characters, at least 1 number, at least 1 (non-)capital letter, NO special characters)");
        boolean validPW = false;
        while (!validPW){
            String pwInput = userInput.nextLine();
            validPW = inputValidation.pwVal(pwInput);
            if(!validPW){
                System.out.println("Invalid password. Please read the rules.");
            }
        }
        System.out.println("Password set.");
        userInput.close();
    }
}
