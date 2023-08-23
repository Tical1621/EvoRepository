package movement;

import java.util.Objects;

public class Coordinates {

    public    Vertical vertical;
    public   Integer horizontal;

    public Coordinates(Vertical vertical, Integer horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Coordinates shift(CoordinatesShift shift, Coordinates coordinates) {
        return new Coordinates(Vertical.values()[coordinates.vertical.ordinal() + shift.vertShift], coordinates.horizontal + shift.horShift);
    }

    public boolean canShift(CoordinatesShift shift) {
        int v = vertical.ordinal() + shift.vertShift;
        int h = horizontal + shift.horShift;
        if((v < 0) || (v > 11)) return false;
        if((h <1) || (h >12)) return false;
    return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        if(vertical !=that.vertical) return false;
        return horizontal.equals(that.horizontal);
    }

    @Override
    public int hashCode() {
        int result = vertical.hashCode();
        result = 31 * result + horizontal.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "vertical=" + vertical +
                ", horizontal=" + horizontal +
                '}';
    }

    public Vertical getVertical() {
        return vertical;
    }

    public void setVertical(Vertical vertical) {
        this.vertical = vertical;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Integer horizontal) {
        this.horizontal = horizontal;
    }
}
