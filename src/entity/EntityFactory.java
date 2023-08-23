package entity;

import movement.Coordinates;

public class EntityFactory {

    public Entity fromFEN(char entityChar, Coordinates coordinates) {
        switch(entityChar) {
            case('T'):
                return new Tree(coordinates,Type.TREE);
            case('R'):
                return new Rock(coordinates,Type.ROCK);
            case('G'):
                return new Grass(coordinates,Type.GRASS);
            case ('H'):
                return new Herbivore(coordinates,Type.HERBIVORE);
            case('P'):
                return new Predator(coordinates,Type.PREDATOR);
            default:
                throw new RuntimeException("unknown fen char for entity");
        }
    }
}
