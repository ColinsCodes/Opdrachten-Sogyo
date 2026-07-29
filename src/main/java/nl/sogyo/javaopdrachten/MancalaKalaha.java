package nl.sogyo.javaopdrachten;

public class MancalaKalaha extends MancalaPocketGeneric {
    public MancalaKalaha(MancalaPocketGeneric nextPocket){
        super();
        this.stones = 0;
        this.nextPocket = nextPocket;
    }
    private int stones = 4;
    public void transferStones(int stonesAmount){
        if (true) {
            stonesAmount--;
            this.stones++;
        }
        if (stonesAmount > 0) {
            //nextPocket.transferStones(stonesAmount);
        } else {
            //nextTurn();
        }
    }
}
