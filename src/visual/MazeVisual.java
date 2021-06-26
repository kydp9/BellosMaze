package visual;

import bellos.Cell;
import bellos.Direction;
import visual.graphics.MyImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;



public class MazeVisual {

    private int size;
    private int currentX;
    private int currentY;
    private GridLayout gridLayout;
    private Cell[][] field;
    private MyImagePanel[][] gField;
    private JButton buttonMove;
    private JButton buttonRestart;
    private MazeVisual mazeVisual;
    private  JPanel mainPanel;
    private JFrame frame;
    public static void main(String[] args) {
        new MazeVisual(10);
    }

    public MazeVisual(int size) {
        mazeVisual = this;
        this.size = size;
        field = new Cell[size][size];
        gField = new MyImagePanel[size][size];
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                buttonMove = new JButton("Start");
                buttonMove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startMaze(mazeVisual);
                    }
                } );
                buttonRestart = new JButton("RESTART");
                buttonRestart.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        restartMaze();
                    }
                } );

                frame = new JFrame("Maze");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                BorderLayout borderLayout = new BorderLayout();

                mainPanel = new JPanel(borderLayout);
                JPanel upPanel = new JPanel(new FlowLayout());

                upPanel.add(buttonMove);
                upPanel.add(buttonRestart);
                MazePanel mazePanel =  new MazePanel(size);
                mainPanel.add(upPanel,BorderLayout.NORTH);
                mainPanel.add(mazePanel,BorderLayout.CENTER);

                frame.add(mainPanel);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


    public class MazePanel extends JPanel {

        public MazePanel(int size) {
            gridLayout = new GridLayout(size, size, 0, 0);

            setLayout(gridLayout);
            Random random = new Random();
            Color[] colors = new Color[]{Color.GREEN, Color.RED};
            for (int col = 0; col < size; col++) {
                for (int row = 0; row < size; row++) {
                    int randomInt = random.nextInt(4);
                    field[col][row] = new Cell(0, Direction.class.getEnumConstants()[randomInt]);
                    MyImagePanel cell = new MyImagePanel(0, Direction.class.getEnumConstants()[randomInt]);

                    if (row == 0 && col == 0) {
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
        boolean isMove = false;
        switch (field[currentY][currentX].getDirection()) {
            case DIRECTION_UP_DESCRIPTION:
                direction = 2;
                if (currentY != size - 1) {
                    currentY++;
                    isMove = true;
                }
                break;
            case DIRECTION_RIGHT_DESCRIPTION:
                direction = 3;
                if (currentX != 0) {
                    currentX--;
                    isMove = true;
                }
                break;
            case DIRECTION_DOWN_DESCRIPTION:
                direction = 0;
                if (currentY != 0) {
                    currentY--;
                    isMove = true;
                }
                break;

            case DIRECTION_LEFT_DESCRIPTION:
                direction = 1;
                if (currentX != size - 1) {
                    currentX++;
                    isMove = true;
                }
                break;

        }

        prev.flip();
        jPrev.flip();

        gField[currentY][currentX].setState(Cell.STATE_OCCUPIED);

        jPrev.repaint();
        field[currentY][currentX].setState(Cell.STATE_OCCUPIED);
        gField[currentY][currentX].setBackground(Color.RED);
        if(isMove)
        jPrev.setBackground(Color.GREEN);
    }


    private void startMaze(MazeVisual mazeVisual){
        Thread thread = new Thread(){
            public void run(){
                int moves = 0;

                while (!mazeVisual.isSolved()) {
                    moves++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.in.read();
                    mazeVisual.move();
                }

                JOptionPane.showMessageDialog(frame,
                        "Maze solved in " + moves + ".");
            }
        };
        thread.start();
    }

    private void restartMaze(){
        MazePanel mazePanel =  new MazePanel(size);

        mainPanel.add(mazePanel,BorderLayout.CENTER);

        mazePanel.repaint();
    }
}