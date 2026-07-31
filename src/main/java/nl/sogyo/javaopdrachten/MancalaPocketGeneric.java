package nl.sogyo.javaopdrachten;

public abstract class MancalaPocketGeneric {
    int stones;
    int pocketOwner;
    MancalaPocketGeneric nextPocket;
    MancalaSharedData sharedData;
    public abstract void playPocket();
    abstract void passStones(int stonesAmount);
    MancalaPocketGeneric(MancalaPocketGeneric nextPocket, int identity, MancalaSharedData sharedData, int stones){
        this.nextPocket = nextPocket;
        this.pocketOwner = identity;
        this.sharedData = sharedData;
        this.stones = stones;
    }
    public int getStones() {
        return this.stones;
    }
    void setStones(int count) {
        this.stones = count;
    }
    void addStones(int count){
        this.stones += count;
    }
    int stepsFromKalaha(){
        if(this instanceof MancalaKalaha) {
            return 0;
        }
        if (this.nextPocket instanceof MancalaKalaha) {
            return 1;
        } else {
            return this.nextPocket.stepsFromKalaha() + 1;
        }
    }
    public MancalaPocketGeneric nextPocket(int nr){
        if (nr < 1) {
            return this;
        }
        return this.nextPocket.nextPocket(nr - 1);
    }
    void switchPlayer() {
        if (!sharedData.isGameEnd()) {
            sharedData.switchPlayerTurn();
        }
    }
    void checkGameEnd() {
        emptyChecker(this.getFirstPocketBelongToPlayer(1), 0);
        emptyChecker(this.getFirstPocketBelongToPlayer(2), 0);
    }
    void emptyChecker(MancalaPocketGeneric pocket, int num) {
        if (pocket.getStones() == 0 && !(pocket instanceof MancalaKalaha)) {
            emptyChecker(pocket.nextPocket,num+1);
        }
        if (pocket instanceof MancalaKalaha) {
            sharedData.endGame(pocket);
        }
    }
    public MancalaPocketGeneric getFirstPocketBelongToPlayer(int playerNumber) {
        if (this instanceof MancalaKalaha && this.nextPocket.pocketOwner == playerNumber) {
            return this.nextPocket;
        } else {
            return this.nextPocket.getFirstPocketBelongToPlayer(playerNumber);
        }
    }
}
