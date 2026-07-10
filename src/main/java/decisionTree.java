import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

class listicle {
    public static ArrayList<String> getStrings(InputStream tree) {     //new method for an ArrayList
        Scanner treeReader = new Scanner(tree);                         //initialize user input
        ArrayList<String> navData = new ArrayList<String>();            //create ArrayList "navData"
        while (treeReader.hasNextLine()) {                              //if file has next line
            String[] line = treeReader.nextLine().split(", ");    //add next line to temp string array, split by ","
            if (line.length == 3) {                                     //if the string array of the input line has 3 entries
                navData.add(line[0]);                                   //add all three entries as separate entities in navData
                navData.add(line[1]);
                navData.add(line[2]);
            } else {                                                    //if string array of input does NOT have 3 entries (meaning it has 2)
                navData.add(line[0]);                                   //add both entries plus an empty value
                navData.add(line[1]);
                navData.add("0");
            }
        }
        return navData;
    }
}

public class decisionTree {
    static void main(String[] args){
        Scanner userSay = new Scanner(System.in);
        String newLine = System.lineSeparator();
        System.out.println("Welkom bij BoomSelector!");
        InputStream tree = decisionTree.class.getClassLoader().getResourceAsStream("intermediate/decision-tree-data.txt");      //pulls resource file
        if (tree == null) {     //error message if file not found
            System.out.println("Error: filename.txt not found in resources folder.");
            return;
        }
        ArrayList<String> navData = listicle.getStrings(tree);      //populates new arraylist navData
        String currentNode = "N1";                                  //selects starter message
        int currentCoord = 0;                                       //defines starting coordinate
        boolean answer = false;                                     //defines loop exit
        while(!answer){                                             //initialize loop
            int j = 0;                                              //defines (+resets) internal loops
            while(j < navData.size()-1){                            //while j is less than total size of arraylist - 1
                if(navData.get(j).matches(currentNode) && navData.get(j+2).matches("0")) {        //check if value at j matches value "currentNode" and the following value does not match ja or nee
                        System.out.print(navData.get(j + 1));       //print msg after j
                        currentCoord = j;                           //store value of j if needed
                        j = 500;                                    //ends loop
                }
                j++;
            }
            j = 0;
            if(!(navData.get(currentCoord+1).length() < 10 && navData.get(currentCoord + 2).matches("0"))) {
                System.out.println(" (ja/nee)");                    //maakt de zin af die boven begonnen is
                String yesNo = userSay.nextLine();                  //user input
                if (yesNo.matches("ja") || yesNo.matches("Ja")) {       //if input matches
                    while (j < navData.size() - 1) {                //check entire array for match with current node AND match with user input
                        if (navData.get(j).matches(currentNode) && navData.get(j + 2).matches("Ja")) {
                            currentNode = navData.get(j + 1);       //set currentNode to the node referenced by prev. node
                            currentCoord = j;                       //stores loop value
                            j = 500;                                //exits loop
                        }
                        j++;
                    }
                } else if (yesNo.matches("nee") || yesNo.matches("Nee")) {      //see "ja" above
                    while (j < navData.size() - 1) {
                        if (navData.get(j).matches(currentNode) && navData.get(j + 2).matches("Nee")) {
                            currentNode = navData.get(j + 1);
                            currentCoord = j;
                            j = 500;
                        }
                        j++;
                    }
                } else {
                    System.out.println("Dat verstond ik niet. probeer het opnieuw.");
                }
            } else {
                answer = true;      //terminates loop
            }
        }
    }
}
