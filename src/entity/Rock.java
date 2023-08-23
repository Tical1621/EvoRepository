package entity;

import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public  class Rock extends StaticEntitiesBehaviour {
    public Rock(Coordinates coordinates, Type type) {
        super(coordinates, type);
    }


}