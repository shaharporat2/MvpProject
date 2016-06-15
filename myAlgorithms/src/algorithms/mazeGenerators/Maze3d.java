package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.omg.CORBA.FREE_MEM;

/**
 * @version 1.0
 * @param non
 * @throws non
 * Description: This is Class that holds a Maze3d parameters  
 */
public class Maze3d {
	private int rows;
	private int cols;
	private int floor;
	private int maze [][][];
	
	
	private Position startPosition;
	private Position goalPosition;

	private Random rand = new Random();
	
	public static final int WALL = 1;
	public static final int FREE = 0;
	
	public Maze3d(int floor, int rows,int cols) {
		
		int i,j,k;
		this.floor = floor;
		this.rows = rows;
		this.cols = cols;
		maze=new int [floor][rows][cols];
		
		for (i = 0; i < floor; i++) {
			for(j = 0; j < rows ; j++){
				for (k = 0; k < cols; k++){
				 maze[i][j][k]= WALL;
				}
			}
		}
		
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int[][][] getMaze() {
		return maze;
	}

	public void setMaze(int[][][] maze) {
		this.maze = maze;
	}

	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(Position goolPosition) {
		this.goalPosition = goolPosition;
	}


	
	public void print(){
		int i,j,k;
		int [][][] m= new int [this.floor][this.rows][this.cols];
		m = this.getMaze();
		for (i = 0; i < floor; i++) {
			for(j = 0; j < rows ; j++){
				for (k = 0; k < cols; k++){
				 System.out.print(m[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public int[][] getCrossSectionByY(int cfloor){
		int [][] maze2d;
		maze2d = new int [getRows()][getCols()];
		if((cfloor < 0 ) || (cfloor > getFloor())){
			throw new IndexOutOfBoundsException("You Enterd Invalid number of floor ");
		}
		else{
		int j,k = 0;				
		for( j = 0 ; j< rows; j++){
			for( k = 0; k <cols; k++){
				maze2d[j][k] = maze[cfloor][j][k];
				//System.out.print(maze2d[j][k]);
				}
			//System.out.println();
			}
		}
		return maze2d;
	}

	public int[][] getCrossSectionByX(int crows){
		int [][] maze2d;
		maze2d = new int [getFloor()][getCols()];
		if((crows < 0 ) || (crows > getRows())){
			throw new IndexOutOfBoundsException("You Enterd Invalid number of floor ");
		}
		else{
		int i,k = 0;				
		for( i = 0 ; i< floor; i++){
			for( k = 0; k <cols; k++){
				maze2d[i][k] = maze[i][crows][k];
				//System.out.print(maze2d[i][k]);
				}
			//System.out.println();
			}
		}
		return maze2d;
	}

	public int[][] getCrossSectionByZ(int ccols){
		int [][] maze2d;
		maze2d = new int [getFloor()][getRows()];
		if((ccols < 0 ) || (ccols > getCols())){
			throw new IndexOutOfBoundsException("You Enterd Invalid number of floor ");
		}
		else{
		int i,j = 0;				
		for( i = 0 ; i< floor; i++){
			for( j = 0; j <rows; j++){
				maze2d[i][j] = maze[i][j][ccols];
				//System.out.print(maze2d[i][j]);
				}
			//System.out.println();
			}
		}
		return maze2d;
	}
	
	public String[] getPossibleMoves(Position p){
		int i = 0 ;
		ArrayList<Direction> moves;
		moves = getPossibleDirections(p);
		String [] posmoves = new String[moves.size()];
		for (Direction direction : moves) {
			posmoves[i] = direction.toString();
			i++;
			}
		return posmoves;
	}
	
	public ArrayList<Direction> getPossibleDirections(Position pos){
		ArrayList<Direction> dirs = new ArrayList<Direction>();
		int [][][] m = getMaze();
		
		int x=pos.getRows();
		int y=pos.getFloor();
		int z=pos.getCols();
		/*Check left neighbor*/
		if(x - 1 >= 0 && m[y][x-1][z] == FREE){
			dirs.add(Direction.LEFT); 
		}
		/*Check right neighbor*/
		if(x + 1 < getRows() && m[y][x+1][z] == FREE){
			dirs.add(Direction.RIGHT);
		}
		/*Check BackWord neighbor*/
		if(z -1  >= 0 && m[y][x][z-1] == FREE){
			dirs.add(Direction.BACKWARD);
		}
		if(z + 1 < getCols() && m[y][x][z+1] == FREE){
			dirs.add(Direction.FORWARD);
		}
		if(y - 1 >= 0 && m[y -1][x][z] == FREE){
			dirs.add(Direction.DOWN);
		}
		if(y + 1 < getFloor() && m[y +1][x][z] == FREE){
			dirs.add(Direction.UP);
		}
		return dirs;
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start: " + startPosition.toString() + "\n");
		sb.append("Goal: " + goalPosition.toString() + "\n");
		int i,j,k;
		int [][][] m= new int [this.floor][this.rows][this.cols];
		m = this.getMaze();
		for (i = 0; i < floor; i++) {
			for(j = 0; j < rows ; j++){
				for (k = 0; k < cols; k++){
					sb.append(m[i][j][k]);
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public byte[] toByteArray(){
		int iter = 0;
		byte [] byteMaze = new byte[floor*rows*cols+10];
		byte[] sPos = new byte [3];
		byte[] gPos = new byte [3];
		sPos = startPosition.toBytePosition();
		gPos = goalPosition.toBytePosition();
		for(int i = 0; i < 3; i++){
			byteMaze[iter] = sPos[i];
			iter++;
		}
		for(int i = 0; i < 3; i++){
			byteMaze[iter] = gPos[i];
			iter++;
		}

		byteMaze[iter] = (byte)floor;
		iter++;
		byteMaze[iter] = (byte)rows;
		iter++;
		byteMaze[iter] = (byte)cols;

		iter++;
		for (int i = 0; i < floor; i++) {
			for(int j = 0; j < rows ; j++){
				for (int k = 0; k < cols; k++){
				 byteMaze[iter] = (byte) maze[i][j][k];
				 iter++;
				}
			}
		}
		return byteMaze;
	}
	
	public Maze3d(byte[] mazeByte) {
	
	startPosition = new Position((int)mazeByte[0],(int)mazeByte[1],(int)mazeByte[2]);
	goalPosition = new Position((int)mazeByte[3],(int)mazeByte[4],(int)mazeByte[5]);
	floor = (int)mazeByte[6];
	rows = (int)mazeByte[7];
	cols = (int)mazeByte[8];
	
	int iter = 9;

	maze=new int [floor][rows][cols];

	for (int i = 0; i < floor; i++) {
		for(int j = 0; j < rows ; j++){
			for (int k = 0; k < cols; k++){
			 maze[i][j][k] = (int)mazeByte[iter];
			 iter++;
			}
		}
	}
	}
}
	
	


