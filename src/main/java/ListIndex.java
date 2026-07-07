import java.util.Arrays;
import java.util.Random;

public class ListIndex {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();  //useful for printing later
        Random rand = new Random();
        int[] numArray = new int[10];                       //makes array size 10
        int i = 0;                                          //sets up loop
        for (i = 0; i < numArray.length; i++) {               //for loop
            numArray[i] = rand.nextInt(101);         //ads random number between 0 and 100
        }
        System.out.println("The following array was created:" + newLine + Arrays.toString(numArray) + newLine);    //prints full array

        //begin of bubble sort
        int j, temp;                                 //defines two integers
        boolean swapped;                                //defines boolean "swapped"
        i = 0;
        for (i = 0; i < numArray.length - 1; i++) {     //for length of numArray minus 1 (stop sort at last)
            swapped = false;
            for (j = 0; j < numArray.length - i - 1; j++) { //for length of numArray minus progress in sort
                if (numArray[j] > numArray[j + 1]) {        //compare numArray value j and j+1
                    temp = numArray[j];                     //Save current value of numArray[j]
                    numArray[j] = numArray[j + 1];          //replace numArray[j] with numArray[j+1]
                    numArray[j + 1] = temp;                 //replace numArray[j+1] with previous value of numArray[j]
                    swapped = true;                         //confirm swapped, which repeats the for loop
                }
            }
            if (!swapped)                   //if no more swapping, break loop
                break;                      //how to avoid break?
        }
        int[] numSorted;
        numSorted = numArray.clone();       //save state of numArray before it is used later
        //end of bubbleSort
        System.out.println("The highest number is: " + numArray[numArray.length-1] + newLine + "The lowest two numbers together are: " + (numArray[0] + numArray[1]) + newLine + newLine + "The even numbers are: ");
        i = 0;
        for (i = 0; i < numArray.length; i++) {      //for loop to check every array entry
            if (numArray[i] % 2 == 0) {               //if number has 0 remainder on modulo 2 (if number is even)
                System.out.print(numArray[i] + " ");  //print number if true
            }
        }
        i = 0;
        int[] numDiv2 = new int[numArray.length];   //create array same size as numArray
        int[] numDiv3 = new int[numArray.length];   //same but for div by 3
        int[] numDiv5 = new int[numArray.length];   //same but for div by 5
        for (i = 0; i < numArray.length; i++) {      //for loop to check numArray content
            if (numArray[i] % 2 == 0) {               //for every number divisible by 2 add number to numDiv2
                numDiv2[i] = numArray[i];
            }
            if (numArray[i] % 3 == 0) {          //see previous
                numDiv3[i] = numArray[i];
            }
            if (numArray[i] % 5 == 0) {          //see previous
                numDiv5[i] = numArray[i];
            }
            if(numDiv2[i] != 0 || numDiv3[i] != 0 || numDiv5[i] != 0){
                numArray[i] = 200;                 //set entry of numArray to 200 if entry was divisible
            }
        }
        System.out.println(newLine + "The following numbers are divisible by 2: ");
        i = 0;
        for (i = 0; i < numArray.length; i++) {      //for loop to determine if numDiv2 contains numbers
            if (numDiv2[i] != 0) {                    //for loop to determine if numDiv2 does not equal 0
                System.out.print(numDiv2[i] + " ");
            }

        }
        System.out.println(newLine + "The following numbers are divisible by 3: ");
        i = 0;
        for (i = 0; i < numArray.length; i++) {      //for loop to determine if numDiv3 contains numbers
            if (numDiv3[i] != 0) {
                System.out.print(numDiv3[i] + " ");
            }

        }
        System.out.println(newLine + "The following numbers are divisible by 5: ");
        i = 0;
        for (i = 0; i < numArray.length; i++) {      //for loop to determine if numDiv5 contains numbers
            if (numDiv5[i] != 0) {                   //check numDiv5 entry
                System.out.print(numDiv5[i] + " ");  //print numDiv5 entry
            }
        }
        System.out.println(newLine + "The remaining numbers are: ");
        i = 0;
        for (i = 0; i < numArray.length; i++) {      //for loop to determine if numArray contains numbers
            if (numArray[i] != 200) {                   //check if number does NOT equal 200
                System.out.print(numArray[i] + " ");  //print number that is not 200
            }
        }
        System.out.println(newLine + newLine + "The sorted array is as follows: " + Arrays.toString(numSorted));
    }
}