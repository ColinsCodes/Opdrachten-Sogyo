package nl.sogyo.javaopdrachten;

import java.util.InputMismatchException;

abstract class MancalaPocketGeneric {
    private int stones;
    private final int pocketOwner;
    MancalaPocketGeneric nextPocket;
    MancalaSharedData sharedData;
    abstract void playPocket();
    abstract void passStones(int stonesAmount);
    abstract int kalahaStepCounter();
    MancalaPocketGeneric(MancalaPocketGeneric nextPocket, int owner, MancalaSharedData sharedData, int stones){
        this.nextPocket = nextPocket;
        this.pocketOwner = owner;
        this.sharedData = sharedData;
        this.stones = stones;
    }
    int getStones() {
        return this.stones;
    }
    void setStones(int count) {
        stones = count;
    }
    void addStones(int count){
        stones += count;
    }
    int getPocketOwner() {
        return pocketOwner;
    }
    MancalaPocketGeneric nextPocket(int nr){
        if (nr == 0) {
            return this;
        }
        if (nr < 0) {
            throw new InputMismatchException("Invalid input. Please select a positive pocket number (0+)");
        }
        return nextPocket.nextPocket(nr - 1);
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
