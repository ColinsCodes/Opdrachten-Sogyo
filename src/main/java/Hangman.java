import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Hangman {

    //public stringGenerator(){
    //    String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";        //definieert het alfabet
    //    StringBuilder randchar = new StringBuilder();                 //nieuwe string
    //    Random rnd = new Random();                                      //nieuw random
    //    while (randchar.length() < rnd.nextInt(15)) {            //lengte 'woord' willekeurig <15
    //        int index = (int) (rnd.nextFloat() * alfabet.length());
    //        randchar.append(alfabet.charAt(index));
    //    }
    //    String randString = randchar.toString();
    //    System.out.println(randString);
    //}

    public static void main(String[] args) {
        String newLine = System.lineSeparator();    // handig voor print
        System.out.println("Welkom bij Galgje!" + newLine + "Voer hier uw woord in. Bij behoefte aan een willekeurig gegenereerd 'woord', vul niks in.");  //intro berichtje
        Scanner wordInput = new Scanner(System.in);
        String userWord = wordInput.nextLine();     //user input = string
        // momenteel niet in gebruik. Wordt ooit een random String generator.
        // if(!userWord.isEmpty()) {
            //userWord = randString.clone();

        //    int n = userWord.length();
        // }
        // else{
        //    int n = userWord.length();
        //test edit
                
        char[] noGuess = new char[userWord.length()];       //New array with length of word
        noGuess = userWord.toCharArray();                   //fill array with "unguessed" letters
        char[] hangBoard = new char[userWord.length()];     //create new array with length of word
        int i = 0;
        for (i=0; i<userWord.length(); i++) {               //fill array with character '-' for every letter
            hangBoard[i] = '-';
        }
        System.out.println("Huidig spelbord:" + newLine + Arrays.toString(hangBoard) + newLine + "Wat is uw zet?");
        Scanner letterInput = new Scanner(System.in);       //letter input
        char letterHuidig = wordInput.nextLine().charAt(0); //fill array slot with
        i = 0;
        boolean match = false;
        for (i=0; i<userWord.length(); i++){
            if (letterHuidig == hangBoard[i]){
                hangBoard[i] = letterHuidig;
                match = true;
            }
        }
        //clear screen
        System.out.println("\f");
        if (match){
            System.out.println("Correcte letter!");
        }
        else{
        System.out.println("Deze letter is niet goed..." + newLine + "Huidige levens: ");
        }



    }
}
