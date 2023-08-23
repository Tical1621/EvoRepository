package movement;

import java.util.Random;

public enum Vertical {
    A,B,C,D,E,F,G,H,I,J,K,L;

    private static final Random random = new Random();

    public static Vertical randomVertical() {
        Vertical[] verticals = values();
        return verticals[random.nextInt(verticals.length)];
    }

}
