package model;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Utils.*;
import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerato;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import model.Model;
import presenter.Properties;

import java.util.zip.GZIPOutputStream;

import org.eclipse.swt.internal.win32.MSG;

import java.util.zip.GZIPInputStream;



public class Mymodel extends Observable implements Model {
	
	private HashMap<String, Maze3d> mazeHash = new HashMap<>();
	private HashMap<String, Solution> mazeSol = new HashMap<>();
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private HashMap<String,Object> files = new HashMap<>();
	private String output;
	private Properties properties = new Properties();
	private HashMap<Maze3d, Solution> caching = new HashMap<>(); 
	
	public Mymodel(Properties properties) {
		super();
		this.properties = properties;
		unzipAndLoadSolution(properties.getSolutionsFilePath());
		
	}
	
	public void dir(String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null) {
			output = "Path does not found\n";
		}else{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < listOfFiles.length; i++) {
				sb.append(listOfFiles[i] + "\n");
			}
			output = sb.toString();
		}
		setChanged();
		notifyObservers(output);
	}
	
	public void generateMaze3D(String name, int floor, int rows, int cols) {
		if((floor == -1) && (cols == -1) && ( rows == -1))
		{
			String [] param = properties.getDefaultMaze();
			name = param[1];
			floor = Integer.parseInt(param[2]);
			rows = Integer.parseInt(param[3]);
			cols = Integer.parseInt(param[4]);
			
		}
		
		String newName = name;
		int newFloor = floor;
		int newRows = rows;
		int newCols = cols;
		
		if (mazeHash.get(newName) != null) {
			output = "Maze " + name + " already exists please choose other name \n";
			setChanged();
			notifyObservers(output);
		} else {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					MyMaze3dGenerator generate = new MyMaze3dGenerator();
					Maze3d maze = generate.generate(newFloor, newRows, newCols);
					mazeHash.put(newName, maze);
					output =  "Maze " + newName + " is ready \n";
					setChanged();
					notifyObservers(output);
				}
			});
			executor.submit(thread);
		}
	}
	
	@Override
	public void display(String name) {
		Maze3d maze = mazeHash.get(name);
		if (maze != null) {
			output = maze.toString();
		} else {
			output = "Maze does not exists \n";
		}
		setChanged();
		if(output.contains("not exists")){
			notifyObservers(output);
		}else{
			notifyObservers(maze);
		}
	}
	
	@Override
	public void displayCrossSection(char c, int index, String name) {
		if (mazeHash.get(name) == null) {
			output = "Maze " + name + " does not exists please choose other name \n";
		} else {
			Maze3d maze3d = mazeHash.get(name);
			int[][] maze2d;
			String Stringmaze2d = new String();
			switch (c) {
			case 'y':
				maze2d = new int[maze3d.getRows()][maze3d.getCols()];
				try {
					maze2d = maze3d.getCrossSectionByY(index);
				} catch (Exception e) {
					maze2d = null;
				}
				if (maze2d == null) {
					output = "you enter an Invalid index \n";
				} else {
					Stringmaze2d = Utils.MatrixToString(maze2d, maze3d.getRows(), maze3d.getCols());
					output = Stringmaze2d;
				}
				break;

			case 'x':
				maze2d = new int[maze3d.getFloor()][maze3d.getCols()];
				try {
					maze2d = maze3d.getCrossSectionByX(index);
				} catch (Exception e) {
					maze2d = null;
				}
				if (maze2d == null) {
					output = "you enter an Invalid index";
				} else {
					Stringmaze2d = Utils.MatrixToString(maze2d, maze3d.getFloor(), maze3d.getCols());
					output = Stringmaze2d;
				}
				break;

			case 'z':
				maze2d = new int[maze3d.getFloor()][maze3d.getRows()];
				try {
					maze2d = maze3d.getCrossSectionByZ(index);
				} catch (Exception e) {
					maze2d = null;
				}
				if (maze2d == null) {
					output = "you enter an Invalid index";
				} else {
					Stringmaze2d = Utils.MatrixToString(maze2d, maze3d.getFloor(), maze3d.getRows());
					output = Stringmaze2d;
				}
				break;
			default:
				output = "Section " + c + " does not exists, you can enter {x,y,z} \n";
				break;
			}
			setChanged();
			notifyObservers(output);
		}
	}
	
	@Override
	public void saveMaze(String name, String Path) {
		if (this.Maze3dExist(name) == null) {
			output = "Maze does not exists \n";
		} else {
			File file = new File(Path);
			if(!file.exists()){
				file = new File("C:\\Program Files\\Maze\\ " + name + ".maz");
				Path = "C:\\Program Files\\Maze\\ " + name + ".maz";
				try{
				file.createNewFile();
				}catch(Exception e){
					
				}
			}
			try {
				OutputStream out;
				out = new MyCompressorOutputStream(new FileOutputStream(file));
				files.put(Path,out);
				out.write(this.Maze3dExist(name).toByteArray());
				out.flush();
				out.close();
				files.remove(Path);
			} catch (IOException e) {
				output = "Saving maze to file failed \n";
			}
			output = "Maze " + name + " was saved in " + Path + "\n";
		}
		setChanged();
		notifyObservers(output);
	}
	
	public Maze3d Maze3dExist(String name) {
		return (mazeHash.get(name));
	}
	
	@Override
	public void loadMaze(String path, String name) {
		if (this.Maze3dExist(name) != null) {
			output = "maze with the speceify name is already exists\n";
		} else {
			File file = new File(path);
			if (file.exists() == false) {
				output = "File does not exists \n";
			} else {
				try {
					InputStream in = new MyDecompressorInputStream(new FileInputStream(file));
					files.put(path, in);
					int size = ((int)file.getTotalSpace())*8;
					byte b[] = new byte[size];
					in.read(b);
					in.close();
					files.remove(path);
					Maze3d loaded = new Maze3d(b);
					mazeHash.put(name, loaded);
					output = "Maze was loaded \n";
				} catch (IOException e) {
					output = "Could not open file";
				}
			}
		}
		setChanged();
		notifyObservers(output);
	}
	
	@Override
	public void mazeMemorySize(String name) {
		if (this.Maze3dExist(name) == null) {
			output = "Maze does not exists";
		} else {
			Maze3d maze = mazeHash.get(name);
			byte[] size = maze.toByteArray();
			output = "The size of the maze3d Object is: " + size.length + "\n";
		}
		setChanged();
		notifyObservers(output);
	}
	
	@Override
	public void mazeFileSize(String Path) {
		File file = new File(Path);
		if (file.exists() == false) {
			output = "File does not exists \n";
		} else {
			long size = file.length();
			output = "The size of the file is: " + size + "\n";
		}
		setChanged();
		notifyObservers(output);
	}
	
	@Override
	public void solve(String name, String algorithm) {
		if (this.Maze3dExist(name) == null) {
			output = "maze does not exists";
			setChanged();
			notifyObservers(output);
		} else {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					String alg;
					if(algorithm.equals("def")){
						alg = properties.getDefaultSolve();
					}
					else{
						alg = algorithm;
					}

					Maze3d maze = mazeHash.get(name);
					MazeAdapter adapter = new MazeAdapter(maze);

					switch (alg) {
					case "DFS":
						DFS DFS = new DFS();
						Solution DFSSolution = DFS.search(adapter);
						caching.put(maze, DFSSolution);
						mazeSol.put(name, DFSSolution);
						output = "Solution " + name + " with algorithm DFS is ready \n";
						break;
					case "BestFS":
						BestFirstSearch BestFS = new BestFirstSearch();
						Solution BestFSSolution = BestFS.search(adapter);
						caching.put(maze, BestFSSolution);
						mazeSol.put(name, BestFSSolution);
						output = "Solution " + name + " with algorithm Best First Search is ready \n";
						break;
					case "BreadthFS":
						BreadthFirstSearch BreathFS = new BreadthFirstSearch();
						Solution BreadthFSSolution = BreathFS.search(adapter);
						caching.put(maze, BreadthFSSolution);
						mazeSol.put(name, BreadthFSSolution);
						output = "Solution " + name + " with algorithm Breadth First Search is ready \n";
						break;
					default:
						output = "Algorithm does not exists \n";
						break;
					}
					setChanged();
					notifyObservers(output);
				}
			});
			executor.submit(thread);
		}
	}
	
	@Override
	public void displaySolution(String name) {
		if (mazeSol.get(name) == null) {
			output = "Solution does not exists \n";
			setChanged();
			notifyObservers(output);
		} else {
			Solution sol = new Solution();
			sol = mazeSol.get(name);
			setChanged();
			notifyObservers(sol);
		}	
	}
	
	@Override
	public void exit() {
		StringBuilder sb = new StringBuilder();
		sb.append("Saving solutions...\n");
		zipAndSaveSolutions(properties.getSolutionsFilePath());
		sb.append("Closing Running threads...\n");
		executor.shutdown();
		sb.append("Closing open Files...\n");
		for (Entry<String, Object> e : files.entrySet()) {
			if(e.getValue().getClass().equals("InputStream")){
				
			}
		}
		sb.append("exit\n");
		setChanged();
		notifyObservers(sb.toString());
	}

	
	public void setMazeSol(HashMap<String, Solution> sol){
		this.mazeSol = sol;
	}
	
	@Override
	public void zipAndSaveSolutions(String path){
		if(mazeSol.isEmpty() == true){
			output = "There is no solutions to save\n";
		}
		else{
			MazeSolution mazeSolution = new MazeSolution(mazeSol);
			try{
				GZIPOutputStream fileOut = new GZIPOutputStream(new FileOutputStream(path));
				files.put(path,fileOut);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(mazeSolution);
				out.close();
				files.remove(path);
				output = "The Hash Map of the solutions has been saved\n";
			}catch(Exception e){
				output = "Error while saving the hash map\n";
			}
		}
	}

	@Override
	public void unzipAndLoadSolution(String path) {
		MazeSolution mazeSolution = null;
		try{
			GZIPInputStream fileIn = new GZIPInputStream(new FileInputStream(path));
			ObjectInputStream in = new ObjectInputStream(fileIn);
			mazeSolution = (MazeSolution) in.readObject();
			in.close();
			fileIn.close();
			setMazeSol(mazeSolution.getMazeSol());
		}catch(Exception e){
			output = "Error While loading maze solutions\n";
		}
	}

	@Override
	public void SaveConfiguration(LinkedList<String> args) {
		boolean success = true;
		String output;
		try{
			while(!args.isEmpty()){
				switch (args.getFirst()) {
				case "-maxNumOfThread":
					args.removeFirst();
					int maxNumOfThread = Integer.parseInt(args.getFirst());
					properties.setMaxNumOfThread(maxNumOfThread);
					args.removeFirst();
					break;
				case "-solutionsFilePath":
					
					break;
				case "-LogFilePath":
					break;
				case "-mazeGenerate":
					args.removeFirst();
					if(args.getFirst().equals("MyMaze")){
						properties.setMazeGenerate("MyMaze");
					}else if (args.getFirst().equals("SimpleMAze")){
						properties.setMazeGenerate("SimpleMaze");
					}else{
						success = false;
						throw new Exception();
					}
					args.removeFirst();
					break;
				case "-ProgramPath":
					args.removeFirst();
					File file = new File(args.getFirst());
					if(file.exists()){
						System.out.println(args.getFirst());
					}
					else{
						success = false;
						throw new Exception();
					}
					args.removeFirst();
					break;
				case "-defaultSolve":
					args.removeFirst();
					if(args.getFirst().equals("DFS")){
						properties.setDefaultSolve("DFS");
					}else if(args.getFirst().equals("BestFS")){
						properties.setDefaultSolve("BestFS");
					}else if(args.getFirst().equals("BreathFS")){
						properties.setDefaultSolve("BreathFS");
					}else{
						success = false;
						throw new Exception();
					}
					args.removeFirst();
					break;
				case "-defaultUserInterface":
					args.removeFirst();
					if(args.getFirst().equals("CLI")){
						properties.setDefaultUserInterface("CLI");
					}else if(args.getFirst().equals("GUI")){
						properties.setDefaultUserInterface("GUI");
					}else{
						success = false;
						throw new Exception();
					}
					args.removeFirst();
					break;
				default:
					args.clear();
					success = false;
					break;
				}
			}
		}catch (Exception e){
			args.clear();
			success = false;
			output = "You entered invalid arguments\n";
			setChanged();
			notifyObservers(output);
		}
		try{
			if(success){
				String path = "C:\\Program Files\\Maze\\properties1.xml";
				XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
				xmlEncoder.writeObject(properties);
				xmlEncoder.close();
			}
		}catch(Exception e){
			 success = false;
			 output = "Error saving to file\n";
			 setChanged();
			 notifyObservers(output);
		}
		
		if(success){
			output = "XML File has been change please restart the program\n";
			setChanged();
			notifyObservers(output);
		}
	}
}



