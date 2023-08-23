package entity;

import entity.Creature;
import map.MapClass;
import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, Type type) {
        super(coordinates, type);
    }

    @Override
    protected void move() {
        super.move();
    }

    @Override
    protected void eat() {
        super.eat();
    }

    @Override
    protected Set<CoordinatesShift> getCoordinatesAround() {
        Set<CoordinatesShift> result = new HashSet<>();
        for(int vertShift = -2; vertShift <=2; vertShift++) {
            for(int horShift = -2; horShift <=2; horShift++) {
                if((vertShift ==0)&& (horShift ==0)) {
                    continue;
                }
                result.add(new CoordinatesShift(vertShift,horShift));
            }
        }
        return result;
    }

    @Override
    protected HashMap<Coordinates, Entity> checkNeighbors(Coordinates coordinates, MapClass map) {
        return null;
    }
}
