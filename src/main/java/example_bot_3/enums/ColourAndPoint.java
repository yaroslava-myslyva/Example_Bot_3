package example_bot_3.enums;

public enum ColourAndPoint {
    FIRST ("Белый", 1),
    SECOND ("Серый", 2),
    THIRD ("Рыжий", 3),
    FOURTH ("Чёрный", 4);

    public final String name;
    public final int point;

        ColourAndPoint(String name, int point) {
        this.name = name;
        this.point = point;
    }
}
