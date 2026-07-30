package nl.sogyo.javaopdrachten;

public class MancalaSharedData {
    private int playerTurn = 1;
    private int emptyPlayer = 0;
    private boolean gameEnd = false;
    protected void switchPlayerTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
    }
    //may be accessed by GUI if necessary
    public int getPlayerTurn() {
        return playerTurn;
    }
    protected void setEmptyPlayer(int player){
        emptyPlayer = player;
    }
    protected int getEmptyPlayer() {
        return emptyPlayer;
    }
    protected void endGame(){
        gameEnd = true;
    }
    protected boolean isGameEnd(){
        return gameEnd;
    }
    public MancalaPocketGeneric boardGenerator(int pocketnr, MancalaSharedData sharedData) {
        if (pocketnr < 7) {
            return new MancalaPocket(boardGenerator(pocketnr+1, sharedData), 1, sharedData);
        }
        if (pocketnr == 7) {
            return new MancalaKalaha(boardGenerator(pocketnr+1, sharedData), 1, sharedData);
        }
        if (pocketnr == 14) {
            return new MancalaKalaha(null, 2, sharedData);
        }
        return new MancalaPocket(boardGenerator(pocketnr+1, sharedData), 2, sharedData);
    }
    public void loopCloser(MancalaPocketGeneric pocket1) {
        pocket1.nextPocket(13).nextPocket = pocket1;
    }
}
