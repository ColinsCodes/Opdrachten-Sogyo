package nl.sogyo.javaopdrachten.quote;
import java.util.Random;
import java.time.*;

public class Quote {
    static String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };

    public static void main(String... args) {
        String newLine = System.lineSeparator();
        System.out.println("Vandaag is het " + LocalDate.now() + newLine + "Uw quote:");
        int day = LocalDate.now().getDayOfYear() % quotes.length;          //berekent huidige dag in vergelijking met totale hoeveelheid quotes
        System.out.print(quotes[day][0].substring(0,1).toUpperCase() + quotes[day][0].substring(1) + ": ");        //print eerst de eerste letter van de naam in hoofdletter > rest van de naam > dubbele punt
        if(quotes[day][1].matches(".*[.?!,;']")){       //zoekt of er al een punctuation mark aanwezig is
            System.out.print('"' + quotes[day][1].substring(0,1).toUpperCase() + quotes[day][1].substring(1) + '"'); //print zonder punct. als er al punct. is
        }
        else{
            System.out.print('"' + quotes[day][1].substring(0,1).toUpperCase() + quotes[day][1].substring(1) + ".\""); //print met punct. als er nog geen punct. is
        }

    }
}
