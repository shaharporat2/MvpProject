package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int maxNumOfThread = 20;
	
	private String solutionsFilePath = "C:\\Program Files\\Maze\\solutions.sol";
	
	private String LogFilePath = "C:\\Program Files\\Maze\\logs.txt";
	
	private String mazeGenerate = "MyMaze";
	
	private String ProgramPath = "C:\\Program Files\\Maze";
	
	private String[] defaultMaze = {"","userName" ,"5","5","5"};
	
	public String[] getDefaultMaze() {
		return defaultMaze;
	}

	public void setDefaultMaze(String[] defaultMaze) {
		this.defaultMaze = defaultMaze;
	}

	public String getProgramPath() {
		return ProgramPath;
	}

	public void setProgramPath(String programPath) {
		ProgramPath = programPath;
	}

	public Properties(int maxNumOfThread, String solutionsFilePath, String mazeGenerate, String LogFilePath, String[] defaultMaze) {
		super();
		this.maxNumOfThread = maxNumOfThread;
		this.solutionsFilePath = solutionsFilePath;
		this.mazeGenerate = mazeGenerate;
		this.LogFilePath = LogFilePath;
		this.defaultMaze = defaultMaze;
	}
	
	public Properties() {
		super();
	}
	

	public int getMaxNumOfThread() {
		return maxNumOfThread;
	}

	public void setMaxNumOfThread(int maxNumOfThread) {
		this.maxNumOfThread = maxNumOfThread;
	}

	public String getSolutionsFilePath() {
		return solutionsFilePath;
	}

	public void setSolutionsFilePath(String solutionsFilePath) {
		this.solutionsFilePath = solutionsFilePath;
	}

	public String getMazeGenerate() {
		return mazeGenerate;
	}

	public void setMazeGenerate(String mazeGenerate) {
		this.mazeGenerate = mazeGenerate;
	}

	public String getLogFilePath() {
		return LogFilePath;
	}

	public void setLogFilePath(String logFilePath) {
		LogFilePath = logFilePath;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("maxNumOfThread: " + getMaxNumOfThread() + "\n" );
		sb.append("solutionsFilePath: " + getSolutionsFilePath() + "\n");
		sb.append("LogFilePath: " + getLogFilePath() + "\n" );
		sb.append("mazeGenerate: " + getMazeGenerate() + "\n");
		return sb.toString();
	}	
}
