import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
class Robot {
    int x;
    int y;
    public enum Direction{
        Noord,
        Oost,
        Zuid,
        West;
        public Direction rechts() {
            if (this.ordinal() < Direction.values().length - 1) {
                return Direction.values()[this.ordinal() + 1];
            }
            return Direction.values()[0];
        }
        public Direction links(){
            if (this.ordinal() > 0){
                return Direction.values()[this.ordinal() - 1];
            }
            return Direction.values()[Direction.values().length - 1];
        }
    }
    Direction direction;
    public Robot(int startX, int startY, Direction startDirection){
        this.x = startX;
        this.y = startY;
        this.direction = startDirection;

    }
    public void forward() {
        switch (direction) {
            case Noord:
                y = y + 1;
                return;
            case Oost:
                x = x + 1;
                return;
            case Zuid:
                y = y - 1;
                return;
            case West:
                x = x - 1;
                return;
        }
    }
    public void backward() {
        switch (direction) {
            case Noord:
                y = y - 1;
                return;
            case Oost:
                x = x - 1;
                return;
            case Zuid:
                y = y + 1;
                return;
            case West:
                x = x + 1;
                return;
        }
    }
}
interface Command{
    public void execute();          //dit deel snap ik niet zo goed. Volgens mij is het nodig zodat java IETS kan invullen in List<Command>
}
class executor {
    private static final List<Command> commandList = new ArrayList<>();        //Creeert nieuwe lege arraylist genaamd commandlist

    public static void commandAdd(Command command) {                    //maakt commando "commandAdd"
        commandList.add(command);           //commandList is final en private en mag dus niet aangepast worden vanuit buiten deze module
    }                                            //door deze line toe te voegen "outsource" je dat probleem naar dezelfde class
    public static void executeALL() {           //weer binnen dezelfde class, een execute all commando
        for (Command command : commandList) {   //voor alle units in de lijst
            command.execute();                  //execute
        }
        commandList.clear();                    //gooi lijst leeg voor hergebruik
    }
}
public class Roborally {
    public static void main(String[] args){
        Robot robot1 = new Robot(0,1, Robot.Direction.West);        //definieert robot 1
        Robot robot2 = new Robot(1,0, Robot.Direction.Oost);        //definieert robot 2
        while(true) {
            System.out.println("U staat nu op (" + robot1.x + "," + robot1.y + ").");   //geeft huidige positie weer
            System.out.println("U kijkt nu naar de richting \"" + robot1.direction + "\""); //geeft huidige richting weer
            System.out.println("Welke kant wilt U op? (links, rechts, rechtdoor, terug) Typ \"klaar\" om uw zetten te doen.");
            Scanner action = new Scanner(System.in);        //setup user input
            Scanner speed = new Scanner(System.in);
            boolean loop = true;        //activeert loop
            while (loop) {
                String userInput = action.nextLine();       //input
                if (userInput.matches("links")) {       //check input
                    executor.commandAdd(() -> robot1.direction = robot1.direction.links());
                } else if (userInput.matches("rechts")) {
                    executor.commandAdd(() -> robot1.direction = robot1.direction.rechts());
                } else if (userInput.matches("rechtdoor")) {
                    boolean speedSet = false;
                    while(!speedSet) {
                        System.out.println("Hoe snel? (1-3)");      //check snelheid
                        String userType = speed.nextLine();
                        int userSpeed = 0;
                        if (userType.isEmpty()) {
                            userSpeed = 1;                          //zet speed 1 bij geen selectie
                            speedSet = true;
                        }else {
                            try {
                                userSpeed = Integer.parseInt(userType);
                                if (userSpeed > 3) {                          //zet maximum snelheid 3
                                    userSpeed = 3;
                                }
                                if (userSpeed < 1) {                          //zet minimum snelheid 1
                                    userSpeed = 1;
                                }
                                while (userSpeed > 0) {                       //voert commando vooruit meerdere keren uit (lui)
                                    executor.commandAdd(() -> robot1.forward()); //gooit het commando forward de lijst in
                                    userSpeed = userSpeed - 1;
                                }
                                speedSet = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Dat is geen nummer, probeer opnieuw.");
                            }

                        }
                    }
                } else if (userInput.matches("terug")) {
                    executor.commandAdd(() -> robot1.backward());
                } else if (userInput.matches("klaar")) {
                    loop = false;
                } else {
                    System.out.println("Ongeldige zet, probeer opnieuw.");
                }
                if(loop) {
                    System.out.println("Volgende zet? Of typ \"klaar\"");
                }
            }
        executor.executeALL(); //als de gebruiker "klaar" is, verstuurt alles tegelijk
        }
    }

}
