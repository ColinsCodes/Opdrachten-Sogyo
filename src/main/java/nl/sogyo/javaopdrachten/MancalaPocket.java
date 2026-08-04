package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

class MancalaPocket extends MancalaPocketGeneric {
    private final int stepsFromKalaha;
    MancalaPocket(MancalaPocketGeneric nextPocket, int owner, MancalaSharedData sharedData) {
        super(nextPocket, owner, sharedData, 4);
        this.stepsFromKalaha = kalahaStepCounter();
    }
    int kalahaStepCounter(){
        if (nextPocket(1) instanceof MancalaKalaha) {
            return 1;
        } else {
            return nextPocket(1).kalahaStepCounter() + 1;
        }
    }
    MancalaPocketGeneric oppositePocket(){
        return nextPocket(stepsFromKalaha*2);
    }
    void claimOpposite () {
        addStones(oppositePocket().getStones());
        oppositePocket().setStones(0);
    }
    void passStones(int stonesAmount) {
        if (stonesAmount > 0) {
            addStones(1);
            stonesAmount -= 1;
        }
        if (oppositePocket().getStones() > 0 && getStones() == 1 && stonesAmount == 0 && getPocketOwner() == sharedData.getPlayerTurn()) {
            claimOpposite();
            nextPocket(stepsFromKalaha).addStones(getStones());
            setStones(0);
        }
        if (stonesAmount > 0) {
            nextPocket(1).passStones(stonesAmount);
        } else {
            checkGameEnd();
            sharedData.switchTurn();
        }
    }
    void playPocket() {
        if (sharedData.getPlayerTurn() == getPocketOwner() && getStones() > 0) {
            int stonesAmount = getStones();
            setStones(0);
            nextPocket(1).passStones(stonesAmount);
            return;
        }
        if (sharedData.getPlayerTurn() != getPocketOwner()) {
            throw new UnplayablePocketException("This pocket is not yours. Current player: " + sharedData.getPlayerTurn());
        }
        if (getStones() == 0 && sharedData.getPlayerTurn() == getPocketOwner()) {
            throw new UnplayablePocketException("This pocket is empty. Please select another.");
        }
    }
}
