package map;

import entity.Entity;
import movement.Coordinates;
import movement.Move;

import java.util.HashMap;
import java.util.Objects;

public class MapClass {

    public final String startingFEN;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapClass mapClass = (MapClass) o;
        return Objects.equals(entities, mapClass.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entities);
    }

    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public HashMap<String, Boolean> rules = new HashMap<>();

    public MapClass(String startingFEN) {
        this.startingFEN = startingFEN;
    }

    public MapClass(String startingFEN, HashMap<Coordinates, Entity> entities, HashMap<String, Boolean> rules) {
        this.startingFEN = startingFEN;
        this.entities = entities;
        this.rules = rules;
    }

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates,entity);
    }

    public void makeMove(Move move) {
        Entity entity = getEntity(move.from);
        removeEntity(move.from);
        setEntities(move.to,entity);
    }

    private void removeEntity(Coordinates from) {
        entities.remove(from);
    }

    public Entity getEntity(Coordinates from) {
            return entities.get(from);
    }
}
