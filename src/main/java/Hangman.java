import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();    // handig voor print
        System.out.println("Welkom bij Galgje!" + newLine + "Voer hier uw woord in. Bij behoefte aan een willekeurig gegenereerd 'woord', vul niks in.");  //intro berichtje
        Scanner wordInput = new Scanner(System.in);
        String userWord = wordInput.nextLine();     //user input = string
        Random rnd = new Random();
        String[] wordsList = {"difference", "take", "leaflet", "feedback", "discover", "hip", "secure", "recommend", "autonomy", "smart", "candidate", "term", "sample", "middle", "courtship", "note", "indulge", "brag", "sermon", "association"};
        if(userWord.length() == 0){
            userWord = wordsList[rnd.nextInt(wordsList.length - 1)];
        }
        char[] noGuess = new char[userWord.length()];       //New array with length of word
        noGuess = userWord.toCharArray();                   //fill array with "unguessed" letters
        char[] hangBoard = new char[userWord.length()];     //create new array with length of word
        ArrayList<Character> wrongLetters = new ArrayList<Character>();
        int i = 0;
        for (i=0; i<userWord.length(); i++) {               //fill array with character '-' for every letter
            hangBoard[i] = '-';
        }
        int lives = 10;
        while(lives > 0 && lives != 20) {
            System.out.println("Huidig spelbord:");
            System.out.println(hangBoard);
            if(!wrongLetters.isEmpty()){
                System.out.println("Deze letters zijn al gebruikt:");
                System.out.println(wrongLetters);
            }
            System.out.println("Wat is uw zet?");
            Scanner letterInput = new Scanner(System.in);       //letter input
            char letterHuidig = wordInput.nextLine().charAt(0); //letterHuidig is first letter of user input
            i = 0;
            boolean match = false;                              //setup boolean om te bepalen of de gok goed was
            for (i = 0; i < userWord.length(); i++) {                //check het hele woord voor matches
                if (letterHuidig == noGuess[i]) {              //als gecheckte letter i gelijk is aan huidige letter
                    hangBoard[i] = letterHuidig;
                    match = true;
                }
            }
            //clear screen
            System.out.println("\f");
            if (!match) {
                lives = lives -1;
                System.out.println("Deze letter is niet goed..." + newLine + "Huidige levens: " + lives);
                wrongLetters.add(letterHuidig);
            }
            i = 0;
            boolean charsLeft = false;
            for(i = 0; i < userWord.length(); i++){
                if(hangBoard[i] == '-'){
                    charsLeft = true;
                }
            }
            if(!charsLeft){
                lives = 20;
            }

        }
        if(lives == 0){
            System.out.println("Je hebt verloren. Volgende keer beter :-(");
        }
        if(lives == 20){
            System.out.println("Je hebt gewonnen! Gefeliciteerd!!" + newLine + "Het woord was: ");
            System.out.println(hangBoard);
        }
    }
}
