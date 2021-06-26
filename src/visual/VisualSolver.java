package visual;

import bellos.Maze;

import javax.swing.*;

public class VisualSolver {

    public static void main(String[] args) {
        int size = 8;

        MazeVisual maze= new MazeVisual(10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int moves = 0;

        while (!maze.isSolved()) {
            moves++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.in.read();
            maze.move();
        }

        System.out.println(maze);
        System.out.println("Maze solved in " + moves + " moves!");
    }

}
