package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cols;
	private int floor;
	private int rows;

	 
	 public Position(int floor, int rows, int cols) {
		this.floor = floor;
		this.rows = rows;
		this.cols = cols;
		
		 
	}

	@Override
	public String toString() {
		return "{"+floor+","+rows+","+cols+"}";
	}

	public int getCols() {
		return cols;
	}

	public int getFloor() {
		return floor;
	}

	public int getRows() {
		return rows;
	}
	
	public byte [] toBytePosition(){
		
		byte floor = (byte)this.getFloor();
		byte rows = (byte)this.getRows();
		byte cols = (byte)this.getCols();
		
		byte [] pos = new byte[3];
		
		pos[0] = floor;
		pos[1] = rows;
		pos[2] = cols;
		
		return pos;
	}
}
 
