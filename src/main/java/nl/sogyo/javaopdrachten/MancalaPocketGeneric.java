package nl.sogyo.javaopdrachten;

public abstract class MancalaPocketGeneric {
    protected int stones;
    protected MancalaPocketGeneric nextPocket;
    public MancalaPocketGeneric(){
    }
    public int getStones() {
        return stones;
    }
    public abstract void transferStones(int stonesAmount);
    public MancalaPocketGeneric getPocket(int nr){
        if (nr < 2) {
            return this;
        }
        return this.nextPocket.getPocket(nr - 1);

    }
//    public static MancalaPocketGeneric createBoard(int initialPocket) {
//        if (initialPocket % 7 == 0) {
//            return new MancalaKalaha(createBoard(++initialPocket));
//        } else if (initialPocket < 14) {
//            return new MancalaPocket(createBoard(++initialPocket));
//        } else {
//            return null;
//        }
//    }
}
