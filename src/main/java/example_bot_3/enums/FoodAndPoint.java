package example_bot_3.enums;

public enum FoodAndPoint {
    FIRST ("Молоко", 1),
    SECOND ("Сметана", 2),
    THIRD ("Рыба", 3),
    FOURTH ("Мясо", 4);

    public final String name;
    public final int point;

    FoodAndPoint(String name, int point) {
        this.name = name;
        this.point = point;
    }
}
