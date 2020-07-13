package sweeper;

import java.awt.*;

public enum Box {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,

    OPENED, // открытая клетка (т.е. отсутствие картинки)
    CLOSED, // закрытая клетка
    FLAGGED, // клетка с флагом
    BOMBED, // клетка с бомбой
    NO_BOMB;

    public Image image;

    public Box nextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }

    public int getNumber() {
        int ord = ordinal();
        if (ord >= Box.NUM1.ordinal() && ord <= Box.NUM8.ordinal())
            return ord;
        return -1;
    }
}
