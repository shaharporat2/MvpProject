package boot;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import presenter.Properties;

public class saveToXml {
	
	public static void main(String[] args){
		
		String path = "C:\\Program Files\\Maze\\properties1.xml";
		
		try{
			Properties properties = new Properties();
			String [] defaultMaze = {"","userName","5","5","5"};
			properties.setDefaultMaze(defaultMaze);
			properties.setDefaultSolve("BestFS");
			properties.setLogFilePath("C:\\Program Files\\Maze\\logs.txt");
			properties.setProgramPath("C:\\Program Files\\Maze");
			properties.setMaxNumOfThread(20);
			properties.setSolutionsFilePath("C:\\Program Files\\Maze\\solutions.sol");
			properties.setMazeGenerate("MyMaze");
			properties.setDefaultUserInterface("CLI");
			XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
			xmlEncoder.writeObject(properties);
			xmlEncoder.close();
		}catch(Exception e){
			
		}
	}
}
