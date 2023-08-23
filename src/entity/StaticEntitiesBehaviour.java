package entity;

import map.MapClass;
import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StaticEntitiesBehaviour extends Entity {

    public StaticEntitiesBehaviour(Coordinates coordinates, Type type) {
        super(coordinates, type);
    }




    @Override
    protected HashMap<Coordinates, Entity> checkNeighbors(Coordinates coordinates, MapClass map) {
        return null;
    }


    @Override
    protected Set<CoordinatesShift> getCoordinatesAround() {
        Set<CoordinatesShift> result = new HashSet<>();
        for(int vertShift = -1; vertShift <=1; vertShift++) {
            for(int horShift = -1; horShift <=1; horShift++) {
                if((vertShift ==0)&& (horShift ==0)) {
                    continue;
                }
                result.add(new CoordinatesShift(vertShift,horShift));
            }
        }
        return result;
    }

    public void moveToComplyWithTheRule() {//мув для статик объектов для соблюдения правил генерации

    }
}
