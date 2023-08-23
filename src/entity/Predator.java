package entity;

import entity.Creature;
import map.MapClass;
import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Predator extends Creature {
    public Predator(Coordinates coordinates, Type type) {
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
        for(int vertShift = -3; vertShift <=3; vertShift++) {
            for(int horShift = -3; horShift <=3; horShift++) {
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
