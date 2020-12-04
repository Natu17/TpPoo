package game.backend.element;


public class TimeBombCandy extends Candy{

    int moves;

    TimeBombCandy(int moves){
        this.moves = moves;
    }
    public void move(){
        moves --;
    }

    public int getMoves(){
        return moves;
    }

    @Override
    public String getKey() {
        return "TIME-BOMB-" + super.getKey();
    }

    @Override
    public String getFullKey() {
        return "TIME-BOMB-" + super.getFullKey();
    }

    @Override
    public long getScore() {
        return 50;
    }

}
