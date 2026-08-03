package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

public class MancalaFaçade {
    private final MancalaSharedData sharedData = new MancalaSharedData();
    private final MancalaPocketGeneric pocket1;
    {
        pocket1 = new MancalaPocket(boardGenerator(2, sharedData), 1, sharedData);
        pocket1.nextPocket(13).setNextPocket(pocket1);
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
    public void playPocket(int pocketNr) {
        pocket1.nextPocket(pocketNr - 1).playPocket();
    }
    public int getScore(int playerNr) {
        switch (playerNr) {
            case 1 -> { return sharedData.getScorePlayer1(); }
            case 2 -> { return sharedData.getScorePlayer2(); }
            default -> { return 0; }
        }
    }
    MancalaPocketGeneric boardGenerator(int pocketnr, MancalaSharedData sharedData) {
        if (pocketnr < 7) {
            return new MancalaPocket(boardGenerator(pocketnr+1, sharedData), 1, sharedData);
        }
        if (pocketnr == 7) {
            return new MancalaKalaha(boardGenerator(pocketnr+1, sharedData), 1, sharedData);
        }
        if (pocketnr < 14) {
            return new MancalaPocket(boardGenerator(pocketnr+1, sharedData), 2, sharedData);
        }
        return new MancalaKalaha(null, 2, sharedData);
    }
}
