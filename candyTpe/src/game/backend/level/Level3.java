package game.backend.level;

import game.backend.GameListener;
import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Level3 extends Grid {

    private static int REQUIRED_SCORE = 5000;
    private static int MAX_MOVES = 20;
    private static int MAX_FRUITS = 7;
    private Level3State level3State;
    @Override
    protected void fillCells() {
        CandyGeneratorCell candyGeneratorCell = new CandyGeneratorCell(this, 5, createFruit);
        fillCells(candyGeneratorCell);
    }

    @Override
    protected GameState newState() {
        level3State = new Level3.Level3State(MAX_FRUITS, MAX_MOVES);
        return level3State;
    }



    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            Cell[][] myMatrix = g();
        }
        return ret;
    }

    public Supplier<Element> createFruit = ()->{
        int i = (int)(Math.random() * CandyColor.values().length);

        if(level3State.getFruitsAlreadyAppear() >= MAX_FRUITS){
            Candy candy = new Candy(CandyColor.values()[i]);
            return (Element) candy;
        }else {
            if (i % 2 == 0) {
                Fruit hazelnut = new Fruit(FruitType.HAZELNUT);
                level3State.addFruits(hazelnut);
                return (Element) hazelnut;
            }else {
                Fruit cherry = new Fruit(FruitType.CHERRY);
                level3State.addFruits(cherry);
                return (Element) cherry;
            }
        }

    };



    private class Level3State extends GameState {
        private int requiredFruits;
        private long maxMoves;
        private List<Fruit> fruitsNow;

        public Level3State(int requiredFruits, int maxMoves) {
            fruitsNow = new ArrayList<>();
            this.requiredFruits = requiredFruits;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return requiredFruits == 0;
        }

        public int getFruitsAlreadyAppear() {
            return MAX_FRUITS-requiredFruits + fruitsNow.size();
        }

        public void addFruits(Fruit fruit){
            fruitsNow.add(fruit);
        }
    }


    public void cellExplosion(Element e) {
        if (e.getClass() != Fruit.class) {
            super.cellExplosion(e);
        }
    }


}
