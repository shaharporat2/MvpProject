package view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import Utils.Utils;
import algorithms.mazeGenerators.Direction;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class MazeDisplay extends Canvas {
	
	
	Image fire;
	Maze3d maze3d;
	
	int [][] mazeData;
	
	Position position;
	
	Image img = new Image(getDisplay(),"C:\\Program Files\\Maze\\player.jpg");
	
	ArrayList<State> solpath;
	
	LinkedList<Position> pospath;
	
	Solution sol;
	
	
	
	public Maze3d getMaze3d() {
		return maze3d;
	}



	public void setMaze3d(Maze3d maze3d) {
		this.maze3d = maze3d;
	}



	public int[][] getMazeData() {
		return mazeData;
	}



	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
	}



	public MazeDisplay(Composite parent, int style, Maze3d maze){
		super(parent, style);
		setBackground(new Color(null,0,0,0));
		this.maze3d = maze;
		position = maze.getStartPosition();
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null,255,255,255));
				e.gc.setBackground(new Color(null,255,255,255));
				int width = getSize().x;
				int height=getSize().y;
				int w=width/mazeData[0].length;
				int h=height/mazeData.length;
				for(int i=0;i<mazeData.length;i++){
					for(int j=0;j<mazeData[i].length;j++){
						int x=j*w;
						int y=i*h;
						if((position.getRows() == maze3d.getRows()) && (position.getCols() == maze3d.getCols()) && (position.getFloor() == maze3d.getFloor())){
							isGoolPosition();
							loadImage(getShell());
						}
						else if((position.getRows() == i) && (position.getCols() == j)){
							e.gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, x, y, w, h);
						}else{
							if(mazeData[i][j]!=1){
								e.gc.fillRectangle(x,y,w,h);
							}
						}
					}
				}
			}
		});	
	}
	
	public boolean moveIsValid(){
		return false;
	}
	
	public void isGoolPosition(){
		if(position.equals(maze3d.getGoalPosition())){
			MessageBox messageBox = new MessageBox(getShell());
			messageBox.setText("Congratulations");
			messageBox.setMessage("Congratulations You won");
			messageBox.open();
			loadImage(getShell());
			
		}
	}
	
	public void moveZUp(){
		ArrayList<Direction> directions = maze3d.getPossibleDirections(position);
		if(directions.contains(Direction.FORWARD)){
			Position newPosition = new Position(position.getFloor(),position.getRows(),position.getCols()+1);
			position.setPosition(newPosition);
		}
		
			this.redraw();
			isGoolPosition();
	}
	
	public void moveZDown(){
		ArrayList<Direction> directions = maze3d.getPossibleDirections(position);
		if(directions.contains(Direction.BACKWARD)){
			Position newPosition = new Position(position.getFloor(),position.getRows(),position.getCols()-1);
			position.setPosition(newPosition);
		}
		this.redraw();
		isGoolPosition();
	}
	
	public void moveXUp(){
		ArrayList<Direction> directions = maze3d.getPossibleDirections(position);
		if(directions.contains(Direction.RIGHT)){
			Position newPosition = new Position(position.getFloor(),position.getRows() + 1,position.getCols());
			position.setPosition(newPosition);
		}
		this.redraw();
		isGoolPosition();
	}
	
	public void moveXDown(){
		ArrayList<Direction> directions = maze3d.getPossibleDirections(position);
		if(directions.contains(Direction.LEFT)){
			Position newPosition = new Position(position.getFloor(),position.getRows()-1,position.getCols());
			position.setPosition(newPosition);
		}
		this.redraw();
		isGoolPosition();

	}
	
	public void moveFloorDown(){
		ArrayList<Direction> directions = maze3d.getPossibleDirections(position);
		if(directions.contains(Direction.DOWN)){
			Position newPosition = new Position(position.getFloor() - 1 ,position.getRows(),position.getCols());
			position.setPosition(newPosition);
			setMazeData(maze3d.getCrossSectionByY(position.getFloor()));
		}
		this.redraw();
		isGoolPosition();

	}
	
	public void moveFloorUp(){
		ArrayList<Direction> directions = maze3d.getPossibleDirections(position);
		if(directions.contains(Direction.UP)){
			Position newPosition = new Position(position.getFloor() + 1,position.getRows(),position.getCols());
			position.setPosition(newPosition);
			setMazeData(maze3d.getCrossSectionByY(position.getFloor()));	
			}
		this.redraw();
		isGoolPosition();
	}	
	
	public void displaySolution(){
		maze3d.setStartPosition(position);
		
	}
	
	public boolean prepreSolution(Object o){
		sol = (Solution)o;
		String [] cord;
		ArrayList<State >solpath = new ArrayList<>();
		pospath = new LinkedList<>();
		solpath = sol.getStates();
		for(State state : solpath ){
			cord = state.getDescription().split("");
			String floor = cord[1];
			String rows = cord[3];
			String cols = cord[5];
			Position solvposition = new Position(Integer.parseInt(floor),Integer.parseInt(rows),Integer.parseInt(cols));
			pospath.addLast(solvposition);
		}
		return true;
	}
	public boolean displayHelp(){
		if(pospath.isEmpty()){
			return true;
		}else{
			position = pospath.getFirst();
			pospath.removeFirst();
			return false;
		}
	}
	
	private void loadImage(Shell shell){
		Utils util = new Utils();
		 String resource = util.getClass().getResource("firework03.gif").getPath();
		try{
			fire = new Image(getDisplay(), resource);
		}catch(Exception e){
			System.out.println("Error loading pic");
		}
	}
	
	
}
	
