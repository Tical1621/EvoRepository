package map;

import entity.Entity;
import entity.EntityFactory;
import movement.Coordinates;
import movement.Vertical;

import java.util.HashMap;

public class MapFactory {

    private final EntityFactory entityFactory = new EntityFactory();
    StringBuilder cache = new StringBuilder();


    public MapClass fromFEN(String fen) {
        HashMap<Coordinates, Entity> fenMap = new HashMap<>();
        HashMap<String, Boolean> rulesMap = new HashMap<>();
        MapClass map = new MapClass(fen,fenMap,rulesMap);
        //получить fen-string,распарсить
        String[] rows = fen.split("/");

       /*
       перебрать каждую строку массива, 1)определить есть ли там entity,
                                                              2)если есть - вызвать setEntity,
                                                              3)если нет - установить пустую клетку
                                                              4)вызвать метод, который отрисует доску по заданному шаблону.
        */
        int vertIndex = 0;
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            char charForEntity = ' ';
            Vertical vertical = null;
            for (int j = 0; j < row.length(); j++) {
                boolean outOfIndex = false;
                char fenChar = row.charAt(j);
                //проверить не является ли осматриваемый символ последним
                if (j == row.length() - 1) {
                    outOfIndex = true;
                }
                if (Character.isDigit(fenChar)) {

                    if (Character.isDigit(fenChar) && !outOfIndex && Character.isDigit(row.charAt(j + 1))) {
                        String sum = fenChar + String.valueOf(row.charAt(j + 1));
                        int bigHorizonValue = Integer.parseInt(sum);
                        Entity entity = entityFactory.fromFEN(charForEntity, new Coordinates(vertical, bigHorizonValue));
                        fenMap.put(entity.getCoordinates(), entity);
                        j++;
                    } else {
                        Entity entity = entityFactory.fromFEN(charForEntity, new Coordinates(vertical, Character.getNumericValue(fenChar)));
                        fenMap.put(entity.getCoordinates(), entity);
                    }
                } else {
                    vertical = Vertical.values()[vertIndex];
                    charForEntity = Character.toUpperCase(fenChar);

                }
            }
            vertIndex++;
        }
        return map;
    }
}
