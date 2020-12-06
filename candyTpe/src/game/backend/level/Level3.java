package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;

public class Level3 extends Grid {
    @Override
    protected void fillCells() {
        CandyGeneratorCell candyGeneratorCell = new CandyGeneratorCell(this);
        fillCells(candyGeneratorCell);
    }

    private static int REQUIRED_SCORE = 5000;
    private static int MAX_MOVES = 20;



    @Override
    protected GameState newState() {
        return new Level3.Level3State(REQUIRED_SCORE, MAX_MOVES);
    }



    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    private class Level3State extends GameState {
        private long requiredScore;
        private long maxMoves;

        public Level3State(long requiredScore, int maxMoves) {
            this.requiredScore = requiredScore;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getScore() > requiredScore;
        }

    }

}
