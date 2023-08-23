package entity;

import movement.Coordinates;

abstract public class Creature extends Entity {
    //базовый класс для существ
    public Creature(Coordinates coordinates, Type type) {
        super(coordinates, type);
    }

    protected void move() {}

    protected void eat() {}

}
