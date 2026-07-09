import java.util.Scanner;
class fraction {
    public int firstWhole;
    public int firstDenom;
    public int firstNume;
    public int secondWhole;
    public int secondNume;
    public int secondDenom;
    public int numerator;
    public int denominator;
    public float decimate;
    public String isString;
    public int loopValue;
    boolean match;
    public enum operators {
        Add,
        Subtract,
        Multiply,
        Divide,
        StringOp,
        DeciOp;
        public operators doOpera(fraction data) {
            switch (this) {
                case Add:
                    if(data.firstNume != 0){
                        if(data.secondNume != 0){       //first num is frac, second num is frac
                            data.numerator = data.firstDenom * data.secondNume + (data.firstNume * data.secondDenom);
                            data.denominator = data.firstDenom * data.secondDenom;
                            int loop = data.denominator;
                            data.match = false;
                            for(data.match = false; 0 < loop; loop--){       //calculate highest common fraction
                                if(data.denominator % loop == 0 && data.numerator % loop == 0){
                                    data.denominator = data.denominator / loop;
                                    data.numerator = data.numerator / loop;
                                    data.match = true;
                                }
                            }

                        } else {        //first num is frac, second num is int
                            data.numerator = data.secondWhole * data.firstWhole + data.firstNume;
                            data.denominator = data.firstDenom;
                        }
                    } else if (data.secondNume != 0) { //first num is int, second num is frac
                        data.numerator = data.firstWhole * data.secondDenom + data.secondNume;
                        data.denominator = data.secondDenom;
                    } else {                    //both first num and second num are integers
                        data.numerator = data.firstWhole + data.secondWhole;
                        data.denominator = 1;
                    }
                break;
                case Multiply:
                    if(data.firstNume != 0) {        //first num is frac
                        if (data.secondNume != 0) {   //first & second num are frac
                            data.numerator = data.firstNume * data.secondNume;
                            data.denominator = data.secondDenom * data.firstDenom;
                            int loop = data.denominator;
                            data.match = false;
                            while (loop != 0 && !data.match) {       //calculate highest common fraction
                                if (data.denominator % loop == 0 && data.numerator % loop == 0) {
                                    data.loopValue = loop;
                                    data.match = true;
                                }
                                loop = loop - 1;
                            }
                            data.denominator = data.denominator / data.loopValue;
                            data.numerator = data.numerator / data.loopValue;
                        } else {        //first num is frac, second is int
                            data.numerator = data.secondWhole * data.firstDenom + data.firstNume;
                            data.denominator = data.firstDenom;
                        }
                    } else if (data.secondNume != 0) {       //first num is int, second num is frac
                            data.numerator = data.secondDenom * data.firstWhole + data.secondNume;
                            data.denominator = data.secondDenom;
                    } else { //first num & second num are int
                        data.numerator = data.firstWhole * data.secondWhole;
                        data.denominator = 1;
                        }
                break;
                case Subtract:
                    if(data.firstNume != 0){        //first num is frac
                        if(data.secondNume != 0){       //first num is frac, second num is frac
                            data.numerator = data.firstNume*data.secondDenom - data.secondNume*data.firstDenom;
                            data.denominator = data.secondDenom * data.firstDenom;
                            int loop = data.denominator;
                            data.match = false;
                            for (data.match = false; 0 < loop; loop--) {       //calculate highest common fraction
                                if (data.denominator % loop == 0 && data.numerator % loop == 0) {
                                    data.denominator = data.denominator / loop;
                                    data.numerator = data.numerator / loop;
                                    data.match = true;
                                }
                            }
                        } else {        //first num is frac, second num is int
                            data.denominator = data.firstNume - data.secondWhole*data.firstDenom;
                            data.numerator = data.firstDenom;
                        }
                    } else if(data.secondNume != 0){ //first num is int, second num is frac
                        data.numerator = data.firstWhole * data.secondDenom - data.secondNume;
                        data.denominator = data.secondDenom;
                    } else {    //first num & second num are both int
                            data.numerator = data.firstWhole - data.secondWhole;
                            data.denominator = 1;
                    }
                break;
                case Divide:
                    if(data.firstNume != 0){        //first num is frac
                        if(data.secondNume != 0){       //first num is frac, second num is frac
                            data.numerator = data.firstNume * data.secondDenom;
                            data.denominator = data.firstDenom * data.secondNume;
                            int loop = data.denominator;
                            data.match = false;
                            for (data.match = false; 0 < loop; loop--) {       //calculate highest common fraction
                                if (data.denominator % loop == 0 && data.numerator % loop == 0) {
                                    data.denominator = data.denominator / loop;
                                    data.numerator = data.numerator / loop;
                                    data.match = true;
                                }
                            }
                        } else {        //first num is frac, second num is int
                            data.numerator = data.firstNume / data.secondWhole;
                            data.denominator = data.firstDenom;
                        }
                    } else if(data.secondNume != 0){ //first num is int, second num is frac
                        data.numerator = data.firstWhole * data.secondDenom - data.secondNume;
                        data.denominator = data.secondDenom;
                    } else {    //first num & second num are both int
                        data.numerator = data.firstWhole / data.secondWhole;
                        data.denominator = 1;
                    }
                case StringOp:
                    data.isString = Integer.toString(data.firstNume) + "/" + Integer.toString(data.firstDenom);
                break;
                case DeciOp:
                    data.decimate = (float) data.firstNume/data.firstDenom;
                break;
                default:
            }
        return null;
        }
    }
}
class scannerInput {
    private static final Scanner userInput = new Scanner(System.in);
    public static String getInput() {
        return userInput.nextLine();
    }
}

public class fracCalc {
    public void main(String[] args){
        String operation = "";
        String newLine = System.lineSeparator();
        Scanner userInput = new Scanner(System.in);     //initialize scanner
        fraction.operators currentOp = null;
        fraction data = new fraction();
        while(true) {
            boolean hasInput = false;                       //initialize loop
            while (!hasInput) {
                System.out.println("Please specify first number");
                String userNum = userInput.nextLine();
                try {       //checks if string can be split into at least 2 numbers
                    String[] tempFirst = userNum.split("/");
                    data.firstDenom = Integer.parseInt(tempFirst[1]);
                    data.firstNume = Integer.parseInt(tempFirst[0]);
                    hasInput = true;
                } catch (ArrayIndexOutOfBoundsException e) {     //if this cannot be done
                    try {
                        data.firstWhole = Integer.parseInt(userNum);        //check if string can be parsed for an integer
                        hasInput = true;
                    } catch (NumberFormatException f) {          //if this cannot be done, restart loop
                        System.out.println("I'm sorry, I don't understand. Please retry.");
                    }
                }
            }
            System.out.println("Please specify operation. Type \"help\" to display operations.");
            hasInput = false;               //resets loop
            while (!hasInput) {
                operation = userInput.nextLine();
                if (operation.matches("help")) {
                    System.out.println("The following operations are available:" + newLine + "Decimate: Displays provided fraction as a decimal number." + newLine + "String along: Returns provided fraction wholly." + newLine + "Standard operations: Add, Subtract, Multiply, Divide");
                } else if (operation.matches("Add") || operation.matches("Multiply") || operation.matches("Subtract") || operation.matches("Divide") || operation.matches("String along") || operation.matches("Decimate")) {
                    currentOp = switch (operation) {
                        case "Decimate" -> fraction.operators.DeciOp;
                        case "String along" -> fraction.operators.StringOp;
                        case "Add" -> fraction.operators.Add;
                        case "Multiply" -> fraction.operators.Multiply;
                        case "Subtract" -> fraction.operators.Subtract;
                        case "Divide" -> fraction.operators.Divide;
                        default -> null;
                    };
                    hasInput = true;
                } else {
                    System.out.println("I'm sorry, I don't understand. Please try again.");
                }
            }
            hasInput = false; //initialize loop
            if (operation.matches("Decimate") || operation.matches("String along")) {
                hasInput = true;            //cancels loop if no math operation
            }
            while (!hasInput) {
                System.out.println("Please specify second number");
                String userNum = scannerInput.getInput();
                try {       //checks if string can be split into at least 2 numbers
                    String[] tempSecond = userNum.split("/");
                    data.secondDenom = Integer.parseInt(tempSecond[1]);
                    data.secondNume = Integer.parseInt(tempSecond[0]);
                    hasInput = true;
                } catch (ArrayIndexOutOfBoundsException e) {     //if this cannot be done
                    try {
                        data.secondWhole = Integer.parseInt(userNum);        //check if string can be parsed for an integer
                        hasInput = true;
                    } catch (NumberFormatException f) {          //if this cannot be done, restart loop
                        System.out.println("I'm sorry, I don't understand. Please retry.");
                    }
                }
            }
            currentOp.doOpera(data);
            if (operation.matches("Decimate")) {
                System.out.println("Uw antwoord is:" + newLine + data.decimate);
            } else if (operation.matches("String along")) {
                System.out.println("Uw antwoord is:" + newLine + data.isString);
            } else if (operation.matches("Add") || operation.matches("Multiply") || operation.matches("Subtract") || operation.matches("Divide")) {
                System.out.println("Uw antwoord is:" + newLine + data.numerator + "/" + data.denominator);
            }
            data.firstNume = data.firstDenom = data.secondNume = data.secondDenom = 0;
            data.firstWhole = data.secondWhole = 0;
        }
    }
}