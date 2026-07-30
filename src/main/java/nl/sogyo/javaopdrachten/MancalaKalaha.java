package nl.sogyo.javaopdrachten;

import Exceptions.UnplayablePocketException;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.naming.InvalidNameException;

public class MancalaKalaha extends MancalaPocketGeneric {
    public MancalaKalaha(MancalaPocketGeneric nextPocket, int identity, MancalaSharedData sharedData) {
        super();
        this.stones = 0;
        this.nextPocket = nextPocket;
        this.playerBelong = identity;
        this.sharedData = sharedData;
    }

    protected void passStones(int stonesAmount) {
        if (sharedData.getPlayerTurn() == playerBelong) {
            --stonesAmount;
            this.addStones(1);
            //no need to switchPlayer() here because player gets another turn if the final stone lands in kalaha
        }
        if (stonesAmount > 0) {
            this.nextPocket.passStones(stonesAmount);
        }
    }
    public void playPocket() {
        throw new UnplayablePocketException("This pocket cannot be played. Please select another.");
    }
    protected int stepsFromKalaha(){
        return 0;
    }

}
