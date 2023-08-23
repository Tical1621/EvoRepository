package map;

import entity.*;
import movement.Coordinates;
import movement.Move;
import movement.Vertical;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class MapRenderer {

    public static final String EMPTY_CELL = "\uD83D\uDFEB";

    public static final String TREE = "\uD83C\uDF32";

    public static final String ROCK = "\uD83D\uDDFF";

    public static final String GRASS = "\uD83C\uDF38";

    public static final String SHEEP = "\uD83D\uDC11";

    public HashMap<Coordinates, Entity>entities = new HashMap<>();
    public void render(MapClass map) {
        StringBuilder sb = new StringBuilder();
        entities = map.entities;
        initSimulationRendering(sb);
        System.out.println(sb);

    }

    public void renderFromFEN(MapClass map) throws InterruptedException {
            StringBuilder sb = new StringBuilder();
            entities = map.entities;
//                map.makeMove(doMove());
                initSimulationRendering(sb);
                System.out.println(sb);
    }

//    private Move doMove() {
//        //овечка двигается-вопрос как ей двигаться после правил генерации
//        {
//            return new Move(new Coordinates(Vertical.L, 1), new Coordinates(Vertical.L, 2));
//        }
//    }


    private void initSimulationRendering(StringBuilder cells) {
        for(Vertical vertical : Vertical.values()) {
            for(int horizontal = 1;horizontal <=12; horizontal ++) {
                Coordinates coordinates = new Coordinates(vertical, horizontal);
                if(isSquareLockedByEntity(coordinates)) {
                    cells.append(getSpriteForEmptyCell(calculateEntity(coordinates)));
                } else {
                    cells.append(getSpriteForEmptyCell(EMPTY_CELL));
                }
            }
            cells.append("\n");
        }
    }

    private String calculateEntity(Coordinates coordinates) {
        return entities.get(coordinates).type.toString();
    }

    private boolean isSquareLockedByEntity(Coordinates coordinates) {
        boolean asdf = entities.keySet().stream().anyMatch(coordinates::equals);
        boolean asd = entities.containsKey(new Coordinates(Vertical.values()[coordinates.getVertical().ordinal()],coordinates.horizontal));
        System.out.println(new Coordinates(Vertical.values()[coordinates.getVertical().ordinal()],coordinates.horizontal));
//        for(Coordinates coordinates1: entities.keySet()) {
//            if(coordinates1.hashCode()-coordinates.hashCode() ==0) {
//                return true;
//            }
//        }
       return asdf;
    }
    public void generateEntities() {

        Set<String> generatedCoordinates = new HashSet<>(generateCoordinates());
        int index = 0;
        for(String coordinate : generatedCoordinates) {
            String[]  split = coordinate.split("/");
            Coordinates coordinates = new Coordinates(Vertical.valueOf(split[0]),Integer.valueOf(split[1]));
            index++;
            Integer range = isBetween(index);
            switch(range) {
                case(0):
                    Tree tree = new Tree(coordinates,Type.TREE);
                    entities.put(coordinates,tree);
                    break;
                case(1):
                    Rock rock = new Rock(coordinates,Type.ROCK);
                    entities.put(coordinates,rock);
                    break;
                case(2):
                    Grass grass = new Grass(coordinates,Type.GRASS);
                    entities.put(coordinates,grass);
                    break;
                case(3):
                    Herbivore herbivore = new Herbivore(coordinates,Type.HERBIVORE);
                    entities.put(coordinates,herbivore);
                    break;
                case(4):
                    Predator predator = new Predator(coordinates,Type.PREDATOR);
                    entities.put(coordinates,predator);
                    break;
            }
        }
    }

    private Integer isBetween(int index) {
        if(index <=8) {
            return 0;//TREE
        }
        else if(index >8 && index <=16) {
            return 1;//ROCK
        }
        else if (index >16 && index <=32) {
            return 2;//GRASS
        }
        else if(index >32 && index <=34) {
            return 3;//HERBIVORE
        } else {
            return 4;//PREDATOR
        }
    }

    public HashSet<String> generateCoordinates() {
        HashSet<String> set = new HashSet<>();
        int a = 1;
        int b = 12;
        while (set.size()< 33) {
            String vert = Vertical.randomVertical().toString();
            int horizon = a+(int) (Math.random() *b);
            set.add(vert+ "/" +horizon);
        }
        return set;
    }



    private String getSpriteForEmptyCell(String sprite) {

        return colorizeSprite(sprite);
    }

    private String colorizeSprite(String entitySprite) {

        String sprite = "";
        if(Objects.equals(entitySprite, "TREE")) {
            sprite = TREE + sprite;
        } else if(Objects.equals(entitySprite, "GRASS")) {
            sprite =  GRASS + sprite;
        }
        else if(Objects.equals(entitySprite, "ROCK")) {
            sprite =  ROCK + sprite;
        }
        else if(Objects.equals(entitySprite, "HERBIVORE")) {
             sprite = SHEEP + sprite;
        } else {
            sprite = EMPTY_CELL + sprite;
        }
        return sprite;
    }




}
