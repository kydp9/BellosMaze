package bellos;

import java.util.Random;

public class Maze {
    private static final String DEFAULT_FONT = "\u001B[0m";
    private static final String GREEN_FONT = "\u001B[32m";

    private int size;
    private int currentX;
    private int currentY;

    private Cell[][] field;

    public static Maze generate(int size) {
        Maze res = new Maze();
        res.size = size;

        res.field = new Cell[size][size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res.field[i][j] = new Cell(0, Direction.class.getEnumConstants()[random.nextInt(4)]);
            }
        }

        res.currentX = 0;
        res.currentY = 0;
        res.field[res.currentY][res.currentY].setState(Cell.STATE_OCCUPIED);

        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].getState() == Cell.STATE_OCCUPIED) {
                    res.append(GREEN_FONT);
                }
                res.append("[").append(field[i][j].getDirectionDescription()).append("]");
                if (field[i][j].getState() == Cell.STATE_OCCUPIED) {
                    res.append(DEFAULT_FONT);
                }

            }
            res.append(System.lineSeparator());
        }
        return res.toString();
    }

    public boolean isSolved() {
        return field[field.length-1][field.length-1].getState() == Cell.STATE_OCCUPIED
                && field[field.length-1][field.length-1].getDirection() == Direction.DIRECTION_RIGHT_DESCRIPTION;
    }

    public void move() {
        Cell prev = field[currentY][currentX];
        prev.setState(Cell.STATE_EMPTY);

        switch (field[currentY][currentX].getDirection()) {
            case DIRECTION_UP_DESCRIPTION:
                if (currentY != 0)
                    currentY--;
                break;
            case DIRECTION_RIGHT_DESCRIPTION:
                if (currentX != size-1)
                    currentX++;
                break;
            case DIRECTION_DOWN_DESCRIPTION:
                if (currentY != size-1)
                    currentY++;
                break;
            case DIRECTION_LEFT_DESCRIPTION:
                if (currentX != 0)
                    currentX--;
                break;
        }

        prev.flip();

        field[currentY][currentX].setState(Cell.STATE_OCCUPIED);
    }
}
