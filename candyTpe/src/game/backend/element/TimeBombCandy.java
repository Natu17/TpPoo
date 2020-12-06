package game.backend.element;


public class TimeBombCandy extends Candy{

    private int moves;

    public TimeBombCandy(int moves, CandyColor color){
        super(color);
        this.moves = moves;

    }
    public void subMove(){
        moves --;
    }

    public int getMoves(){
        return moves;
    }

    public String stringSpecial(){
        return String.valueOf(moves);
    }


    @Override
    public long getScore() {
        return 50;
    }

}
