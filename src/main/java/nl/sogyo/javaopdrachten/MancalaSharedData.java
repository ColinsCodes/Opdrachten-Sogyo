package nl.sogyo.javaopdrachten;

public class MancalaSharedData {
    private int playerTurn = 1;
    private boolean gameEnd;
    private int winner;
    private int scorePlayer1;
    private int scorePlayer2;

    int getPlayerTurn() {
        return playerTurn;
    }
    int getScorePlayer1() {
        return scorePlayer1;
    }
    int getScorePlayer2() {
        return scorePlayer2;
    }
    int getWinner() { return winner; }
    boolean isGameEnd() { return gameEnd; }
    void setScorePlayer1(int score) {
        scorePlayer1 = score;
    }
    void setScorePlayer2(int score) {
        scorePlayer2 = score;
    }
    void setWinner() {
        winner = (Integer.compare(scorePlayer1, scorePlayer2));
        gameEnd = true;
    }
    void switchPlayerTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
    }
    MancalaPocketGeneric boardGenerator(int pocketnr, MancalaSharedData sharedData) {
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
}
