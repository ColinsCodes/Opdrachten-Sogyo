package nl.sogyo.javaopdrachten;

public class MancalaSharedData {
    private int playerTurn = 1;
    private boolean gameEnd;
    private int winner;
    private int scorePlayer1;
    private int scorePlayer2;

    public int getPlayerTurn() {
        return playerTurn;
    }
    public int getScorePlayer1() {
        return scorePlayer1;
    }
    public int getScorePlayer2() {
        return scorePlayer2;
    }
    boolean isGameEnd(){
        return gameEnd;
    }
    int getWinner() {
        return winner;
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
    public void loopCloser(MancalaPocketGeneric firstPocket) {
        firstPocket.nextPocket(13).nextPocket = firstPocket;
    }
    void switchPlayerTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
    }
    void endGame(MancalaPocketGeneric pocket){
        scorePlayer1 = scoreAdder(pocket.getFirstPocketBelongToPlayer(1));
        scorePlayer2 = scoreAdder(pocket.getFirstPocketBelongToPlayer(2));

        if (scorePlayer1 == scorePlayer2) {
            winner = 3;
        }
        if (scorePlayer2 > scorePlayer1) {
            winner = 2;
        }
        if (scorePlayer1 > scorePlayer2) {
            winner = 1;
        }
        gameEnd = true;
    }
    int scoreAdder(MancalaPocketGeneric firstPocketOfPlayerX){
        if (firstPocketOfPlayerX instanceof MancalaKalaha) {
            return firstPocketOfPlayerX.getStones();
        } else {
            return scoreAdder(firstPocketOfPlayerX.nextPocket, firstPocketOfPlayerX.getStones());
        }
    }
    int scoreAdder(MancalaPocketGeneric pocket, int num){
        if (pocket instanceof MancalaKalaha) {
            return num + pocket.getStones();
        } else {
            return scoreAdder(pocket.nextPocket, num + pocket.getStones());
        }
    }

}
