package nl.sogyo.javaopdrachten;
import Exceptions.UnplayablePocketException;
import org.junit.jupiter.api.*;

public class MancalaPocketGenericTest {
    private MancalaSharedData sharedData;
    private MancalaPocketGeneric pocket1;
    @BeforeEach
    public void testSetup() {
        sharedData = new MancalaSharedData();
        pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        sharedData.loopCloser(pocket1);
    }
    @Test
    public void newPocketContentsEqualsFour() {
        MancalaPocketGeneric pocket1 = new MancalaPocket(null, 1, null);
        int stones = pocket1.getStones();
        Assertions.assertEquals(4, stones);
    }
    @Test
    public void newKalahaContentsEqualsZero() {
        MancalaPocketGeneric pocket1 = new MancalaKalaha(null, 1, null);
        int stones = pocket1.getStones();
        Assertions.assertEquals(0, stones);
    }
    @Test
    public void createSeveralPockets(){
        MancalaPocketGeneric pocket1 = new MancalaPocket(new MancalaPocket(new MancalaPocket(null, 1, null), 1, null), 1, null);
        int stones = pocket1.nextPocket(2).getStones();
        Assertions.assertEquals(4, stones);
    }
    @Test
    public void createAllPocketsAndKalahas(){
        MancalaSharedData sharedData = new MancalaSharedData();
        MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
        int stones = pocket1.nextPocket(13).getStones();
        Assertions.assertEquals(0, stones);
    }
    @Test
    public void circularizePockets(){
        int stones = pocket1.nextPocket(36).getStones();

        Assertions.assertEquals(4, stones);
    }
    @Test
    public void stonePassing() {
        pocket1.nextPocket(2).playPocket();

        Assertions.assertEquals(0, pocket1.nextPocket(2).getStones());
    }
    @Test
    public void stoneReceiving() {
        pocket1.nextPocket(2).playPocket();

        Assertions.assertEquals(5, pocket1.nextPocket(3).getStones());
    }
    @Test
    public void stonesNonInfinite() {
        pocket1.nextPocket(2).playPocket();

        Assertions.assertEquals(4, pocket1.nextPocket(10).getStones());
    }
    @Test
    public void playerTurnMatters(){
        Assertions.assertThrows(UnplayablePocketException.class, () -> pocket1.nextPocket(10).playPocket());
    }
    @Test
    public void playerTurnSwitches(){
        pocket1.nextPocket(3).playPocket();

        Assertions.assertEquals(2, sharedData.getPlayerTurn());
    }
    @Test
    public void kahalaNonPlayable() {
        Assertions.assertThrows(UnplayablePocketException.class, () -> pocket1.nextPocket(6).playPocket());
    }
    @Test
    public void personalKalahaFilledButOpponentKalahaSkipped(){
        pocket1.nextPocket(5).setStones(10);

        pocket1.nextPocket(5).playPocket();

        Assertions.assertEquals(1, pocket1.nextPocket(6).getStones());
        Assertions.assertEquals(0, pocket1.nextPocket(13).getStones());
    }
    @Test
    public void pocketBelongToPlayerGetterWorks() {
        pocket1.nextPocket(268);
        int playernr = pocket1.nextPocket(268).getFirstPocketBelongToPlayer(2).pocketOwner;
        Assertions.assertEquals(2, playernr);
    }
    @Test
    public void oppositeStonesClaimedWhenFinalPocketEmpty() {
        pocket1.nextPocket(4).setStones(0);

        pocket1.playPocket();

        Assertions.assertEquals(5, pocket1.nextPocket(6).getStones());
    }
    @Test
    public void playerNotSwitchedWhenKalahaFinalStone(){
        pocket1.nextPocket(2).playPocket();

        Assertions.assertEquals(1, sharedData.getPlayerTurn());
    }
    @Test
    public void gameCanEnd(){
        pocket1.setStones(0);
        pocket1.nextPocket.setStones(0);
        pocket1.nextPocket(2).setStones(0);
        pocket1.nextPocket(3).setStones(0);
        pocket1.nextPocket(4).setStones(0);

        pocket1.nextPocket(5).playPocket();

        Assertions.assertTrue(sharedData.isGameEnd());
    }
    @Test
    public void winnerDeclared(){
        pocket1.setStones(0);
        pocket1.nextPocket.setStones(0);
        pocket1.nextPocket(2).setStones(0);
        pocket1.nextPocket(3).setStones(0);
        pocket1.nextPocket(4).setStones(0);
        pocket1.nextPocket(6).setStones(3);

        pocket1.nextPocket(5).playPocket();

        Assertions.assertEquals(2, sharedData.getWinner());
    }
    @Test
    public void scoreAdderAddsScores() {
        sharedData.endGame(pocket1);

        int scorePlayer1 = pocket1.getStones() +
                pocket1.nextPocket.getStones() +
                pocket1.nextPocket(2).getStones() +
                pocket1.nextPocket(3).getStones() +
                pocket1.nextPocket(4).getStones() +
                pocket1.nextPocket(5).getStones() +
                pocket1.nextPocket(6).getStones();

        Assertions.assertEquals(scorePlayer1, sharedData.getScorePlayer1());
    }
}
