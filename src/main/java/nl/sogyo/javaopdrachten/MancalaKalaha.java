package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

public class MancalaKalaha extends MancalaPocketGeneric {
    MancalaKalaha(MancalaPocketGeneric nextPocket, int identity, MancalaSharedData sharedData) {
        super(nextPocket, identity, sharedData, 0);
    }
    void passStones(int stonesAmount) {
        if (sharedData.getPlayerTurn() == pocketOwner) {
            --stonesAmount;
            this.addStones(1);
            //no need to switchPlayer() here because player gets another turn if the final stone lands in kalaha
        }
        if (stonesAmount > 0) {
            this.nextPocket.passStones(stonesAmount);
        }
        checkGameEnd();
    }
    public void playPocket() {
        throw new UnplayablePocketException("This pocket cannot be played. Please select another.");
    }
}
