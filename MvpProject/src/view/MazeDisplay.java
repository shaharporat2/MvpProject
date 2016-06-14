package view;

import java.util.ArrayList;

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

import algorithms.mazeGenerators.Direction;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MazeDisplay extends Canvas {
	
	Maze3d maze3d;
	
	int [][] mazeData;
	
	Position position;
	
	Image img = new Image(getDisplay(),"C:\\Program Files\\Maze\\player.jpg");
	
	
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
						if((position.getRows() == j) && (position.getCols() == i)){
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
	
	public boolean isGoolPosition(){
		return false;
	}
	
	public void moveDown(){
		Position newPosition = new Position(position.getFloor(),position.getRows(),position.getCols()+1);
		ArrayList<Direction> directions = maze3d.getPossibleDirections(newPosition);
		if(directions.contains(Direction.BACKWARD)){
			position.setPosition(newPosition);
			this.redraw();
		}
		//this.redraw();
	}
	
	public void moveUp(){
		Position newPosition = new Position(position.getFloor(),position.getRows(),position.getCols()-1);
		position.setPosition(newPosition);
		this.redraw();
	}
	
	public void moveRight(){
		Position newPosition = new Position(position.getFloor(),position.getRows() + 1,position.getCols());
		position.setPosition(newPosition);
		this.redraw();
	}
	
	public void moveLeft(){
		Position newPosition = new Position(position.getFloor(),position.getRows()-1,position.getCols());
		position.setPosition(newPosition);
		this.redraw();
	}
	public void moveFloorDown(){
		Position newPosition = new Position(position.getFloor() - 1 ,position.getRows(),position.getCols());
		position.setPosition(newPosition);
		this.redraw();
	}
	
	public void moveFloorUp(){
		Position newPosition = new Position(position.getFloor() + 1,position.getRows(),position.getCols());
		position.setPosition(newPosition);
		this.redraw();
	}		
}
