package bellos;

public enum Direction {
    DIRECTION_UP_DESCRIPTION("^"),
    DIRECTION_RIGHT_DESCRIPTION(">"),
    DIRECTION_DOWN_DESCRIPTION("v"),
    DIRECTION_LEFT_DESCRIPTION("<");

    private String description;

    Direction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
