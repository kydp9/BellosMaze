package visual;

import bellos.Cell;
import bellos.Direction;
import visual.graphics.MyImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MazeVisual {

    private int size;
    private int currentX;
    private int currentY;
    private GridLayout gridLayout;
    private Cell[][] field;
    private MyImagePanel[][] gField;
    private JButton buttonMove;



    public static void main(String[] args) {
        new MazeVisual(10);
    }

    public MazeVisual(int size) {
        this.size = size;
        field = new Cell[size][size];
        gField =  new MyImagePanel[size][size];
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                buttonMove = new JButton("Move");
                JFrame frame = new JFrame("Maze");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Panel(size));

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


    public class Panel extends JPanel {

        public Panel(int size) {
            gridLayout = new GridLayout(size, size, 0, 0);

            setLayout(gridLayout);
            Random random = new Random();
            Color[] colors = new Color[]{Color.GREEN, Color.RED};
            for (int col = 0; col < size; col++) {
                for (int row = 0; row < size; row++) {
                    int randomInt = random.nextInt(4);
                    field[col][row] = new Cell(0, Direction.class.getEnumConstants()[randomInt]);
                    MyImagePanel cell = new MyImagePanel(0, Direction.class.getEnumConstants()[randomInt]);

                    if(row ==0 && col ==0 ){
                        cell.setBackground(Color.RED);
                    } else {
                        cell.setBackground(Color.GREEN);
                    }
                    gField[col][row] = cell;
                    add(cell);
                }
            }
        }
    }

    public boolean isSolved() {

        return field[field.length - 1][field.length - 1].getState() == Cell.STATE_OCCUPIED
                && field[field.length - 1][field.length - 1].getDirection() == Direction.DIRECTION_RIGHT_DESCRIPTION;
    }

    public void move() {

        Cell prev = field[currentY][currentX];
        MyImagePanel jPrev = gField[currentY][currentX];
        prev.setState(Cell.STATE_EMPTY);
        jPrev.setState(Cell.STATE_EMPTY);
        int direction = 0;
        switch (field[currentY][currentX].getDirection()) {
            case DIRECTION_UP_DESCRIPTION:
                direction = 2;
                if (currentY != size - 1)
                    currentY++;
                break;
            case DIRECTION_RIGHT_DESCRIPTION:
                direction = 3;
                if (currentX != 0)
                    currentX--;
                break;
            case DIRECTION_DOWN_DESCRIPTION:
                direction = 0;
                if (currentY != 0)
                    currentY--;
                break;

            case DIRECTION_LEFT_DESCRIPTION:
                direction = 1;
                if (currentX != size - 1)
                    currentX++;
                break;

        }

        prev.flip();
        jPrev.flip();

        gField[currentY][currentX].setState(Cell.STATE_OCCUPIED);

        jPrev.repaint();
        field[currentY][currentX].setState(Cell.STATE_OCCUPIED);
        gField[currentY][currentX].setBackground(Color.RED);
        jPrev.setBackground(Color.GREEN);
    }

}