package nl.sogyo.javaopdrachten;
import Exceptions.UnplayablePocketException;
import org.junit.jupiter.api.*;

import java.lang.management.ManagementFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MancalaPocketGenericTest {
    @Test
    public void newPocketContentsEqualsFour() {
        MancalaPocketGeneric pocket1 = new MancalaPocket(null, 1, null);
        int stones = pocket1.getStones();
        assertEquals(4, stones);
    }
    @Test
    public void newKalahaContentsEqualsZero() {
        MancalaPocketGeneric pocket1 = new MancalaKalaha(null, 1, null);
        int stones = pocket1.getStones();
        assertEquals(0, stones);
    }
    @Test
    public void createSeveralPockets(){
        MancalaPocketGeneric pocket1 = new MancalaPocket(new MancalaPocket(new MancalaPocket(null, 1, null), 1, null), 1, null);
        int stones = pocket1.nextPocket(2).getStones();
        assertEquals(4, stones);
    }
    @Test
    public void createAllPocketsAndKalahas(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        int stones = pocket1.nextPocket(13).getStones();
        assertEquals(0, stones);
    }
    @Test
    public void circularizePockets(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;
        int stones = pocket1.nextPocket(36).getStones();
        assertEquals(4, stones);

    }
    @Test
    public void stonePassing() {
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;

        pocket1.nextPocket(2).playPocket();
        assertEquals(0, pocket1.nextPocket(2).getStones());
    }
    @Test
    public void stoneReceiving() {
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;

        pocket1.nextPocket(2).playPocket();

        assertEquals(5, pocket1.nextPocket(3).getStones());
    }
    @Test
    public void stonesNonInfinite() {
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;

        pocket1.nextPocket(2).playPocket();

        assertEquals(4, pocket1.nextPocket(10).getStones());
    }
    @Test
    public void playerTurnMatters(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;


        assertThrows(UnplayablePocketException.class, () -> {pocket1.nextPocket(10).playPocket();});
    }
    @Test
    public void playerTurnSwitches(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;

        pocket1.playPocket();

        assertEquals(2, sharedData.getPlayerTurn());
    }
    @Test
    public void kahalaNonPlayable() {
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;

        assertThrows(UnplayablePocketException.class, () -> {pocket1.nextPocket(6).playPocket();});
    }
    @Test
    public void personalKalahaFilledButOpponentKalahaSkipped(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;
        pocket1.nextPocket(5).setStones(10);

        pocket1.nextPocket(5).playPocket();

        assertEquals(1, pocket1.nextPocket(6).getStones());
        assertEquals(0, pocket1.nextPocket(13).getStones());
    }
    @Test
    public void oppositeStonesClaimedWhenFinalPocketEmpty() {
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;
        pocket1.nextPocket(4).setStones(0);

        pocket1.playPocket();

        assertEquals(5, pocket1.nextPocket(6).getStones());
    }
    @Test
    public void playerNotSwitchedWhenKalahaFinalStone(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;

        pocket1.nextPocket(2).playPocket();

        assertEquals(1, sharedData.getPlayerTurn());
    }
    @Test
    public void gameCanEnd(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).nextPocket = pocket1;
        pocket1.setStones(0);
        pocket1.nextPocket.setStones(0);
        pocket1.nextPocket(2).setStones(0);
        pocket1.nextPocket(3).setStones(0);
        pocket1.nextPocket(4).setStones(0);

        pocket1.nextPocket(5).playPocket();

        assertEquals(1, sharedData.getEmptyPlayer());
    }
}
