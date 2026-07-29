package nl.sogyo.javaopdrachten;

public class MancalaPocket extends MancalaPocketGeneric {
    public MancalaPocket(MancalaPocketGeneric nextPocket) {
        super();
        this.stones = 4;
        this.nextPocket = nextPocket;
    }
    public void transferStones (int stonesAmount) {
        this.stones++;
        stonesAmount--;
        if (stonesAmount > 0) {
            //nextPocket(1).transferStones(stonesAmount);
        } else {
            //nextTurn();
        }
    }
//    public int checkOpposite (){
//        //return nextPocket(7).getStones();
//    }
//    public void claimOpposite () {
//
//    }
//    public boolean checkPlayer () {
//
//    }
}
