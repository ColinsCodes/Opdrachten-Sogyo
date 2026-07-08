import java.util.Scanner;

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
public class Roborally {
    public static void main(String[] args){
        Robot robot1 = new Robot(0,1, Robot.Direction.West);
        Robot robot2 = new Robot(1,0, Robot.Direction.Oost);
        while(true) {
            System.out.println("U staat nu op (" + robot1.x + "," + robot1.y + ").");
            System.out.println("U kijkt nu naar de richting \"" + robot1.direction + "\"");
            System.out.println("Welke kant wilt U op? (links, rechts, rechtdoor, terug)");
            Scanner action = new Scanner(System.in);
            Scanner speed = new Scanner(System.in);
            String userInput = action.nextLine();
            if (userInput.matches("links")) {
                robot1.direction = robot1.direction.links();
            } else if (userInput.matches("rechts")) {
                robot1.direction = robot1.direction.rechts();
            } else if (userInput.matches("rechtdoor")) {
                System.out.println("Hoe snel? (1-3)");
                String userType = speed.nextLine();
                int userSpeed;
                if(userType.isEmpty()){
                    userSpeed = 1;
                }
                else {
                    userSpeed = Integer.parseInt(userType);
                }
                if(userSpeed > 3){
                    userSpeed = 3;
                }
                if(userSpeed < 1){
                    userSpeed = 1;
                }
                while(userSpeed > 0){
                    robot1.forward();
                    userSpeed = userSpeed - 1;
                }
            } else if (userInput.matches("terug")) {
                robot1.backward();
            } else{
                System.out.println("Ongeldige zet, probeer opnieuw.");
            }
        }
    }

}
