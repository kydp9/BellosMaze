package bellos;

public class Solver {

    public static void main(String[] args) {
        int size = 8;

        Maze maze = Maze.generate(size);
        System.out.println(maze);

        int moves = 0;

        while (!maze.isSolved()) {
            moves++;
            //System.in.read();
            maze.move();
        }

        System.out.println(maze);
        System.out.println("Maze solved in " + moves + " moves!");
    }
}
