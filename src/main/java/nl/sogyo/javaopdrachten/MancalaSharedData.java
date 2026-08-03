package nl.sogyo.javaopdrachten;

public class MancalaSharedData {
    private int playerTurn = 1;
    private int scorePlayer1;
    private int scorePlayer2;
    private int winner;
    private boolean gameEnd;
    int getPlayerTurn() {
        return playerTurn;
    }
    int getScorePlayer1() {
        return scorePlayer1;
    }
    int getScorePlayer2() {
        return scorePlayer2;
    }
    int getWinner() {
        return winner;
    }
    boolean isGameEnd() {
        return gameEnd;
    }
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
}
