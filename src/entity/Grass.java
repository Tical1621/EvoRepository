package entity;

import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Grass extends StaticEntitiesBehaviour {
    public Grass(Coordinates coordinates, Type type) {
        super(coordinates, type);
    }

}