package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

public class MancalaPocket extends MancalaPocketGeneric {
    public MancalaPocket(MancalaPocketGeneric nextPocket, int identity, MancalaSharedData sharedData) {
        super(nextPocket, identity, sharedData, 4);
    }
    void passStones(int stonesAmount) {
        this.addStones(1);
        stonesAmount -= 1;
        if (stonesAmount > 0) {
            this.nextPocket.passStones(stonesAmount);
        }
        if (this.oppositePocket().getStones() > 0 && this.getStones() == 1 && stonesAmount <= 0 && this.pocketOwner == sharedData.getPlayerTurn()) {
            claimOpposite();
            this.nextPocket(this.stepsFromKalaha()).addStones(this.getStones());
            this.setStones(0);
        }
        if (stonesAmount <= 0) {
            checkGameEnd();
            switchPlayer();
        }
    }
    MancalaPocketGeneric oppositePocket(){
        return this.nextPocket(this.stepsFromKalaha()*2);
    }
    void claimOpposite () {
        this.addStones(this.oppositePocket().getStones());
        this.oppositePocket().setStones(0);
    }
    public void playPocket() {
        if (this.getStones() == 0) {
            throw new UnplayablePocketException("This pocket is empty. Please select another.");
        }
        if (sharedData.getPlayerTurn() == pocketOwner) {
            int stonesToPass = this.getStones();
            this.setStones(0);
            this.nextPocket.passStones(stonesToPass);
        } else {
            throw new UnplayablePocketException("This pocket is not yours to select. The turn belongs to player: " + sharedData.getPlayerTurn());

        }
    }
}
