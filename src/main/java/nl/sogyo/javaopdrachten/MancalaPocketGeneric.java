package nl.sogyo.javaopdrachten;

public abstract class MancalaPocketGeneric {
    protected int stones;
    protected int playerBelong;
    protected MancalaPocketGeneric nextPocket;
    protected MancalaSharedData sharedData;
    public abstract void playPocket();
    protected abstract void passStones(int stonesAmount);
    protected abstract int stepsFromKalaha();

    protected MancalaPocketGeneric(){}
    public int getStones() {
        return stones;
    }
    protected void setStones(int stoneCount) {
        this.stones = stoneCount;
    }
    public void addStones(int count){
        this.stones += count;
    }
    public MancalaPocketGeneric nextPocket(int nr){
        if (nr < 1) {
            return this;
        }
        return this.nextPocket.nextPocket(nr - 1);
    }
    protected void switchPlayer() {
        if ((this.nextPocket(this.stepsFromKalaha()).nextPocket.getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(2).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(3).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(4).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(5).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(6).getStones() == 0)
                ||
                (this.nextPocket(this.stepsFromKalaha()).nextPocket(8).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(9).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(10).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(11).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(12).getStones() == 0 &&
                this.nextPocket(this.stepsFromKalaha()).nextPocket(13).getStones() == 0)) {
            sharedData.endGame();
            sharedData.setEmptyPlayer(sharedData.getPlayerTurn());
        }
        if (!sharedData.isGameEnd()) {
            sharedData.switchPlayerTurn();
        }
    }
}
