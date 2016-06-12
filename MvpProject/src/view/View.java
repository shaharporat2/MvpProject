package view;

import algorithms.mazeGenerators.Maze3d;

public interface View {
	void getUserCommand();
	void displayMessage(String msg);
	void displayMessage(Object object);

}
