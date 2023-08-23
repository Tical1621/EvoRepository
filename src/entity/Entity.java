package entity;

import map.MapClass;
import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.HashMap;
import java.util.Set;

public abstract class Entity {
    //базовый класс для всех сущностей, которые могут быть на карте
    public Coordinates coordinates;
    public final Type type;

    public Entity(Coordinates coordinates, Type type) {
        this.coordinates = coordinates;
        this.type = type;
    }

    public Coordinates getCoordinates() {
        return coordinates;

    }

    public Type getType() {
        return type;
    }

    protected abstract Set<CoordinatesShift> getCoordinatesAround ();

        protected abstract HashMap<Coordinates, Entity> checkNeighbors (Coordinates coordinates, MapClass map);

    }

