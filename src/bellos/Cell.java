package bellos;

public class Cell {
    public static final int STATE_EMPTY = 0;
    public static final int STATE_OCCUPIED = 1;

    private int state;

    private Direction direction;

    public Cell(int state, Direction direction) {
        this.state = state;
        this.direction = direction;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getDirectionDescription() {
        return direction.getDescription();
    }

    public void flip() {
        int newDirection = direction.ordinal() + 1;
        if (newDirection > 3) {
            newDirection = 0;
        }

        direction = Direction.class.getEnumConstants()[newDirection];
    }
}
