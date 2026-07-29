package nl.sogyo.javaopdrachten;
import nl.sogyo.javaopdrachten.MancalaPocket;
import nl.sogyo.javaopdrachten.MancalaPocketGeneric;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class MancalaPocketGenericTest {
    @Test
    public void newPocketContentsEqualsFour() {
        MancalaPocketGeneric pocket1 = new MancalaPocket(null);
        int stones = pocket1.getStones();
        assertEquals(4, stones);
    }
    @Test
    public void newKalahaContentsEqualsZero() {
        MancalaPocketGeneric pocket1 = new MancalaKalaha(null);
        int stones = pocket1.getStones();
        assertEquals(0, stones);
    }
    @Test
    public void createSeveralPockets(){
        MancalaPocketGeneric pocket1 = new MancalaPocket(new MancalaPocket(new MancalaPocket(null)));
        int stones = pocket1.getPocket(3).getStones();
        assertEquals(4, stones);
    }
    @Test
    public void createAllPocketsAndKalahas(){
        MancalaPocketGeneric pocket1 =  new MancalaPocket(
                                                new MancalaPocket(
                                                        new MancalaPocket(
                                                                new MancalaPocket(
                                                                        new MancalaPocket(
                                                                                new MancalaPocket(
                                                                                        new MancalaKalaha(
                                                                                                new MancalaPocket(
                                                                                                        new MancalaPocket(
                                                                                                                new MancalaPocket(
                                                                                                                        new MancalaPocket(
                                                                                                                                new MancalaPocket(
                                                                                                                                        new MancalaPocket(
                                                                                                                                                new MancalaKalaha(null))))))))))))));
        int stones = pocket1.getPocket(14).getStones();
        assertEquals(0, stones);
    }
    @Test
    public void circularizePockets(){
        MancalaPocketGeneric pocket1 =  new MancalaPocket(
                new MancalaPocket(
                        new MancalaPocket(
                                new MancalaPocket(
                                        new MancalaPocket(
                                                new MancalaPocket(
                                                        new MancalaKalaha(
                                                                new MancalaPocket(
                                                                        new MancalaPocket(
                                                                                new MancalaPocket(
                                                                                        new MancalaPocket(
                                                                                                new MancalaPocket(
                                                                                                        new MancalaPocket(
                                                                                                                new MancalaKalaha(null))))))))))))));
        pocket1.getPocket(14).nextPocket = pocket1;
        int stones = pocket1.getPocket(36).getStones();
        assertEquals(4, stones);
    }
//    @Test
//    public void recursiveCreation(){
//        MancalaPocketGeneric pocket1 = MancalaPocketGeneric.createBoard(0);
//        pocket1.getPocket(14).nextPocket = pocket1;
//        int stones = pocket1.getStones();
//        int stonesbig = pocket1.nextPocket(50).getStones();
//    }
}
