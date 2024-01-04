package example_bot_3.enums;

public enum HumanAndPoint {
    FIRST ("Друг", 1),
    SECOND ("Приятель", 2),
    THIRD ("Хозяин", 3),
    FOURTH ("Раб", 4);

    public final String name;
    public final int point;

        HumanAndPoint(String name, int point) {
        this.name = name;
        this.point = point;
    }
}
