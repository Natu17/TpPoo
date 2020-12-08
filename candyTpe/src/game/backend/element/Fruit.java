package game.backend.element;

import javafx.beans.binding.ListExpression;

import java.util.Objects;

public class Fruit extends Element {

    private FruitType type;
    @Override
    public boolean isMovable() {
        return true;
    }

    public Fruit() {
    }

    public Fruit(FruitType type) {
        this.type = type;
    }

    public void setType(FruitType type) {
        this.type = type;
    }

    public FruitType getType() {
        return type;
    }

    @Override
    public String getKey() {
        return "FRUIT";
    }


   /* @Override
    public String getFullKey() {
        return type.toString();
    }

    */

}
