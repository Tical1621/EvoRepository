package entity;

import map.MapClass;
import movement.Coordinates;
import movement.CoordinatesShift;

import java.util.*;

public  class EntityRenderingRules {

    private Coordinates coordinates;

    private EntityFactory entityFactory;
    private static final StaticEntitiesBehaviour shift = new StaticEntitiesBehaviour(null,null);


    public   MapClass satisfyingRules(MapClass mapWithoutRules) {
       HashMap<String, Boolean> rules = new HashMap<>(); //зачем здесь rules?
       HashMap<Coordinates, Entity> entities = mapWithoutRules.entities;
       satisfyingFirstRule(entities);
//       satisfyingSecondRule(entities);
//       satisfyingThirdRule(entities);
        return new MapClass(mapWithoutRules.startingFEN,entities,rules);
    }
    Set<CoordinatesShift> shifts = shift.getCoordinatesAround();//получил возможные клетки для перемещения
    public HashMap<Coordinates, Entity> satisfyingFirstRule(HashMap<Coordinates, Entity> entities) { //одинаковые Entity не могут соприкасаться ни по одному направлению
        //для каждой сущности вычислить список окружающих её координат
        HashMap<Coordinates, Entity> result = new HashMap<>();
        for (Coordinates coordinates : entities.keySet()) {
            String currentEntity = String.valueOf(entities.get(coordinates).type);
            Set<Coordinates> shiftResult = new HashSet<>();
//            Set<CoordinatesShift> shifts = shift.getCoordinatesAround();
            for (CoordinatesShift shift1 : shifts) {
                if (coordinates.canShift(shift1)) {//проверить не выходит ли координата за пределы поля
                    shiftResult.add(coordinates.shift(shift1, coordinates));
                }
            }
            boolean isSomethingAround = shiftResult.stream().anyMatch(entities::containsKey);//проверить что вокруг кто-то есть
            if(isSomethingAround) {//проверить есть ли в радиусе=1 entity такого же типа
                String type = "";
                HashMap<Coordinates, Entity> sameEntityAround = new HashMap<>();
                for(Coordinates coordinates1: entities.keySet()) {
                    if(shiftResult.contains(coordinates1)) {
                        type = String.valueOf(entities.get(coordinates1).type);
                        if(type.equals(currentEntity)) {
                            sameEntityAround.put(coordinates1,entities.get(coordinates));
                        }
                    }
                }
                if(!sameEntityAround.isEmpty()) {//если есть - переместить, перемещать пока условие не будет выполнено
                       Entity afterMove = moveEntityUntilRuleBeingSatisfied(sameEntityAround, coordinates, shiftResult, type);
                       result.put(afterMove.getCoordinates(),afterMove);

                }
            }
        }
        return result;
    }

    private Entity moveEntityUntilRuleBeingSatisfied(
            HashMap<Coordinates, Entity> sameEntityAround,
            Coordinates coordinatesOfCurrentEntity,
            Set<Coordinates> squaresAbleToMove,
            String type) {
            Set<CoordinatesShift> recalculatingLocation = shift.getCoordinatesAround();
            while(squaresAbleToMove.stream().anyMatch(sameEntityAround::containsKey)) {
                 Set<Coordinates> newShiftResult = new HashSet<>();
                    coordinatesOfCurrentEntity.setHorizontal(coordinatesOfCurrentEntity.getHorizontal()+1);
                    for(CoordinatesShift shift1 : recalculatingLocation) {
                        if (coordinatesOfCurrentEntity.canShift(shift1)) {//проверить не выходит ли координата за пределы поля
                            newShiftResult.add(coordinatesOfCurrentEntity.shift(shift1, coordinatesOfCurrentEntity));
                        }
                    }
                    squaresAbleToMove.clear();
                    squaresAbleToMove = newShiftResult;

            }
            return new EntityFactory().fromFEN(type.charAt(0),coordinatesOfCurrentEntity);
    }



    public  HashMap<String, Boolean> satisfyingSecondRule(HashMap<Coordinates, Entity> entities) {
        return null;
    }

    public  HashMap<String, Boolean> satisfyingThirdRule( HashMap<Coordinates, Entity> entities) {
        //Entity не могут(кроме grass) загораживать проход к любой другой клетке
        return null;
    }
}
