package model;

public interface Model {
	void dir(String path);
	void generateMaze3D(String name,int floor,int rows,int cols);
	void display(String name);
	void displayCrossSection(char c, int index, String name);
	void saveMaze(String name, String Path);
	void loadMaze(String path, String name);
	void mazeMemorySize(String name);
	void mazeFileSize(String Path);
	void solve(String name, String algorithm);
	void displaySolution(String name);
	void zipAndSaveSolutions(String path);
	void unzipAndLoadSolution(String path);
	void exit();
}
