package game.backend.level;

import game.backend.GameListener;
import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.*;
import game.backend.move.Move;
import game.backend.move.MoveMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Level2 extends Grid {

    private static int MAX_BOMBS = 10;
    private static int MAX_MOVES = 15;
    private static int PROBABILITY = 5;
    private Level2State level2State;

    @Override
    protected void fillCells() {
        CandyGeneratorCell candyGeneratorCell = new CandyGeneratorCell(this,PROBABILITY,createBomb);
        fillCells(candyGeneratorCell);
    }

    @Override
    protected GameState newState() {
        level2State = new Level2State(MAX_BOMBS);
        return level2State;

    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        level2State.moves(-1);
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            level2State.addMove();
        }else{
            level2State.moves(1);
        }
        return ret;
    }



    public Supplier<Element> createBomb = ()->{
        int i = (int)(Math.random() * CandyColor.values().length);

        if(level2State.getBombsAlredyAppear() >= MAX_BOMBS){
            Candy candy = new Candy(CandyColor.values()[i]);
            return (Element) candy;
        }else {
            TimeBombCandy timeBombCandy = new TimeBombCandy(MAX_MOVES, CandyColor.values()[i]);
            level2State.addBombs(timeBombCandy);
            return (Element) timeBombCandy;
        }

    };

    private class Level2State extends GameState {
        private List<TimeBombCandy> timeBombCandiesNow;
        private int maxBombs;

        public Level2State(int maxBombs) {
            this.maxBombs = maxBombs;
            timeBombCandiesNow = new ArrayList<>();
        }
        public int getBombsAlredyAppear() {
            return MAX_BOMBS-maxBombs + timeBombCandiesNow.size();
        }

        public void addBombs(TimeBombCandy timeBombCandy){
            timeBombCandiesNow.add(timeBombCandy);
        }

        public void moves(int n){
            timeBombCandiesNow.forEach(timeBombCandy -> timeBombCandy.setMove(n));
        }

        public void removeBombs(TimeBombCandy timeBombCandy){
            timeBombCandiesNow.removeIf(timeBombCandy1 -> timeBombCandy1 == timeBombCandy);
            maxBombs --;
        }

        public boolean gameOver() { return playerWon() || (timeBombCandiesNow.size()!=0 && timeBombCandiesNow.get(0).getMoves() == 0); }

        public boolean playerWon() {
            return maxBombs == 0;
        }

        public String getState(){
            if(timeBombCandiesNow.size() > 0) {
                return super.getState() + " Movientos Restantes: " + String.valueOf(timeBombCandiesNow.get(0).getMoves());
            }
            return super.getState();
        }
    }

    @Override
    public void cellExplosion(Element e) {
        super.cellExplosion(e);
        if (e instanceof TimeBombCandy) {
            level2State.removeBombs((TimeBombCandy) e);
        }
    }


}
