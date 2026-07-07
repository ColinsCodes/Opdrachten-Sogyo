import java.util.Scanner;       //import scanner class for user input

public class LeapYear {
    public static void main(String[] args) {
        Scanner yearInput = new Scanner(System.in);     //creates scanner input object?
        System.out.println("What is the year?");
        int queryYear = yearInput.nextInt();       //creates user input called "queryYear"
        if(queryYear % 4 == 0){
            if(queryYear % 100 == 0){
                if(queryYear % 400 == 0){
                System.out.println("Input year is a leap year!");
                }
                else
            System.out.println("Input year is NOT a leap year!");
            }
            else
        System.out.println("Input year is a leap year!");
        }
        else System.out.println("Input year is NOT a leap year!");
    }
}