package algorithms.mazeGenerators;

public interface Maze3dGenerator {
	Maze3d generate(int floor,int rows,int cols);
	String measureAlgorithmTime(int floor,int rows,int cols);
}
