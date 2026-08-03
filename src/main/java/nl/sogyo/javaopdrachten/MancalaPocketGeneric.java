package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

abstract class MancalaPocketGeneric {
    private int stones;
    private final int pocketOwner;
    private MancalaPocketGeneric nextPocket;
    MancalaSharedData sharedData;
    abstract void playPocket();
    abstract void passStones(int stonesAmount);
    abstract int kalahaStepCounter();
    MancalaPocketGeneric(MancalaPocketGeneric nextPocket, int identity, MancalaSharedData sharedData, int stones){
        this.nextPocket = nextPocket;
        this.pocketOwner = identity;
        this.sharedData = sharedData;
        this.stones = stones;
    }
    int getStones() {
        return this.stones;
    }
    void setStones(int count) {
        this.stones = count;
    }
    void addStones(int count){
        stones += count;
    }
    int getPocketOwner() {
        return pocketOwner;
    }
    void setNextPocket(MancalaPocketGeneric firstPocket){
        nextPocket = firstPocket;
    }
    MancalaPocketGeneric nextPocket(int nr){
        if (nr == 0) {
            return this;
        }
        if (nr < 0) {
            throw new UnplayablePocketException("Invalid pocket. Select a positive pocket number (0-14).");
        }
        return nextPocket.nextPocket(nr - 1);
    }
    void switchPlayer() {
        if (!sharedData.isGameEnd()) {
            sharedData.switchPlayerTurn();
        }
    }
    MancalaPocketGeneric getFirstPocketBelongToPlayer(int playerNumber) {
        if (this instanceof MancalaKalaha && nextPocket.pocketOwner == playerNumber) {
            return nextPocket;
        } else {
            return nextPocket.getFirstPocketBelongToPlayer(playerNumber);
        }
    }
    void checkGameEnd() {
        emptyChecker(getFirstPocketBelongToPlayer(1));
        emptyChecker(getFirstPocketBelongToPlayer(2));
    }
    void emptyChecker(MancalaPocketGeneric pocket) {
        if (pocket.getStones() == 0 && !(pocket instanceof MancalaKalaha)) {
            emptyChecker(pocket.nextPocket);
        }
        if (pocket instanceof MancalaKalaha) {
            tallyScores();
            sharedData.setWinner();
        }
    }
    int scoreAdder(MancalaPocketGeneric firstPocketOfPlayerX){
        return scoreAdder(firstPocketOfPlayerX.nextPocket, firstPocketOfPlayerX.getStones());
    }
    int scoreAdder(MancalaPocketGeneric pocket, int num){
        if (pocket instanceof MancalaKalaha) {
               return (num + pocket.getStones());
        } else {
            return scoreAdder(pocket.nextPocket, num + pocket.getStones());
        }
    }
    void tallyScores(){
        sharedData.setScorePlayer1(scoreAdder(getFirstPocketBelongToPlayer(1)));
        sharedData.setScorePlayer2(scoreAdder(getFirstPocketBelongToPlayer(2)));
    }
}
