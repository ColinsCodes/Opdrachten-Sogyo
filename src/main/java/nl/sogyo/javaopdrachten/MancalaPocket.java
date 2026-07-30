package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

import java.util.Objects;

public class MancalaPocket extends MancalaPocketGeneric {
    public MancalaPocket(MancalaPocketGeneric nextPocket, int identity, MancalaSharedData sharedData) {
        super();
        this.stones = 4;
        this.nextPocket = nextPocket;
        this.playerBelong = identity;
        this.sharedData = sharedData;
    }
    protected void passStones(int stonesAmount) {
        this.addStones(1);
        --stonesAmount;
        if (stonesAmount > 0) {
            this.nextPocket.passStones(stonesAmount);
        }
        if (stonesAmount <= 0) {
            switchPlayer();
        }
        if (this.checkOpposite() > 0 && this.getStones() == 1 && stonesAmount <= 0) {
            claimOpposite();
            this.nextPocket(this.stepsFromKalaha()).addStones(this.getStones());
            this.setStones(0);
        }
    }
    protected int checkOpposite (){
        return this.nextPocket(this.stepsFromKalaha()*2).getStones();
    }
    protected void claimOpposite () {
        this.addStones(this.nextPocket(this.stepsFromKalaha()*2).getStones());
        this.nextPocket(this.stepsFromKalaha()*2).setStones(0);
    }
    public void playPocket() {
        if (sharedData.getPlayerTurn() == playerBelong) {
            int stonesToPass = this.getStones();
            this.setStones(0);
            this.nextPocket.passStones(stonesToPass);
        } else {
            throw new UnplayablePocketException("This pocket is not yours to select. The turn belongs to player: " + sharedData.getPlayerTurn());
        }
    }
    protected int stepsFromKalaha(){
        if (Objects.equals(this.nextPocket.getClass().toString(), "MancalaKalaha")) {
            return 1;
        } else {
            return this.nextPocket.stepsFromKalaha() + 1;
        }
    }
    public void loopCloser(MancalaPocketGeneric pocket1) {
        pocket1.nextPocket(13).nextPocket = pocket1;
    }
}
