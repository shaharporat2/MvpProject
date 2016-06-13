package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;

public class MazeDisplay extends Canvas {
	
	Maze3d maze3d;
	
	int [][] mazeData;
	
	
	
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



	public MazeDisplay(Composite parent, int style){
		super(parent, style);
		setBackground(new Color(null,0,0,0));
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null,255,255,255));
				e.gc.setBackground(new Color(null,255,255,255));
				int width = getSize().x;
				int height=getSize().y;
				int w=width/mazeData[0].length;
				int h=height/mazeData.length;
				for(int i=0;i<mazeData.length;i++)
				for(int j=0;j<mazeData[i].length;j++){
				int x=j*w;
				int y=i*h;
				if(mazeData[i][j]!=0)
				e.gc.fillRectangle(x,y,w,h);
				}
			}
		});
		
		
	}
}
