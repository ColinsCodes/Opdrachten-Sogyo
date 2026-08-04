package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

class MancalaKalaha extends MancalaPocketGeneric {
    MancalaKalaha(MancalaPocketGeneric nextPocket, int owner, MancalaSharedData sharedData) {
        super(nextPocket, owner, sharedData, 0);
    }
    int kalahaStepCounter() {
        return 0;
    }
    void passStones(int stonesAmount) {
        if (sharedData.getPlayerTurn() == getPocketOwner() && stonesAmount > 0) {
            stonesAmount -= 1;
            addStones(1);
        }
        if (stonesAmount > 0) {
            nextPocket(1).passStones(stonesAmount);
        } else {
            checkGameEnd();
        }
    }
    void playPocket() {
        throw new UnplayablePocketException("This pocket cannot be played. Please select another.");
    }
}
