package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

public class MancalaFaçade {
    private final MancalaSharedData sharedData = new MancalaSharedData();
    private final MancalaPocketGeneric pocket1 =  new MancalaPocket(sharedData.boardGenerator(2, sharedData), 1, sharedData);
    public void initializeGame() {
        pocket1.loopCloser();
    }
    public int gameTurn() {
        return sharedData.getPlayerTurn();
    }
    public boolean isGameEnd() {
        return sharedData.isGameEnd();
    }
    public int getWinner() {
        return sharedData.getWinner();
    }
    public int[] gameState() {
        int[] gameState = new int[14];
        for (int i = 0; i < 14; i++) {
            gameState[i] = pocket1.nextPocket(i).getStones();
        }
        return gameState;
    }
    public Exception playPocket(int pocketNr) {
        try {
            pocket1.nextPocket(pocketNr - 1).playPocket();
            return null;
        } catch (UnplayablePocketException e) {
            return e;
        }
    }
    public int getScore(int playerNr) {
        switch (playerNr) {
            case 1 -> { return sharedData.getScorePlayer1(); }
            case 2 -> { return sharedData.getScorePlayer2(); }
            default -> { return 0; }
        }
    }
}
