package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Level2 extends Grid {


    private static int REQUIRED_SCORE = 5000;
    private static int MAX_BOMBS = 20;
    private static int MAX_MOVES = 15;
    private Level2State level2State;

    @Override
    protected void fillCells() {
        CandyGeneratorCell candyGeneratorCell = new CandyGeneratorCell(this,3,createBomb);
        fillCells(candyGeneratorCell);
    }

    @Override
    protected GameState newState() {
        level2State = new Level2State(REQUIRED_SCORE,MAX_BOMBS);
        return level2State;

    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    public Supplier<Element> createBomb = ()->{
        int i = (int)(Math.random() * CandyColor.values().length);
        TimeBombCandy timeBombCandy = new TimeBombCandy(MAX_MOVES,CandyColor.values()[i]);
        level2State.timeBombCandiesNow.add(timeBombCandy);
        return (Element) timeBombCandy;
    };

    private class Level2State extends GameState {
        private List<TimeBombCandy> timeBombCandiesNow;
        private long requiredScore;
        private long maxBombs;

        public Level2State(long requiredScore, int maxBombs) {
            this.requiredScore = requiredScore;
            this.maxBombs = maxBombs;
            timeBombCandiesNow = new ArrayList<>();
        }

        public void addMoves(){
            timeBombCandiesNow.stream().forEach(timeBombCandy -> timeBombCandy.subMove());
        }


        public boolean gameOver() {
            return playerWon() || timeBombCandiesNow.get(0).getMoves() == 0;
        }
        public boolean playerWon() {
            return maxBombs == 0;
        }
    }

}
