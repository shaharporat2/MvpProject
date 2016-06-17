package presenter;

import view.MyView;
import view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import model.Model;

public class Presenter implements Observer {

		/**
		 * 
		 */
	
		private Model model;
		private View view;
		private Properties properties;
		
		
		private HashMap<String, Command> commands = new HashMap<>();
		
		public Presenter(Model model, View view, Properties properties) {
			super();
			this.model = model;
			this.view = view;
			this.properties = properties;
			
			Dir dir = new Dir(model, view);
			Generate3dMaze generate3dMaze = new Generate3dMaze(model, view);
			Display display = new Display(model, view);
			DisplayCrossSection displayCrossSection = new DisplayCrossSection(model, view);
			SaveMaze savemaze = new SaveMaze(model, view);
			LoadMaze loadMaze = new LoadMaze(model, view);
			MazeMemorySize mazeMemorySize = new MazeMemorySize(model, view);
			MazeFileSize mazeFileSize = new MazeFileSize(model, view);
			Solve solve = new Solve(model, view);
			DisplaySolution displaySolution = new DisplaySolution(model, view);
			EXIT exit = new EXIT(model, view);
			SaveConfiguration saveConfiguration = new SaveConfiguration(model, view);
			commands.put("save_configuration",saveConfiguration);
			commands.put("exit",exit);
			commands.put("display_solution",displaySolution);
			commands.put("solve",solve);
			commands.put("file_size",mazeFileSize);
			commands.put("maze_size",mazeMemorySize);
			commands.put("load_maze",loadMaze);
			commands.put("save_maze",savemaze);
			commands.put("display_cross_section_by",displayCrossSection);
			commands.put("display",display);
			commands.put("generate_maze_3d",generate3dMaze);
			commands.put("dir",dir);
		}

		public Properties getProperties() {
			return properties;
		}

		public void setProperties(Properties properties) {
			this.properties = properties;
		}

		/**
		 * 
		 */
		@Override
		public void update(Observable o, Object arg) {
			if(o instanceof View){
				view = (View)o;
				setNewView(view);
				String [] userCommand;
				userCommand = (String[])arg;
				if(commands.get(userCommand[0]) == null ){
					view.displayMessage("Command not found\n");
				}else{
					commands.get(userCommand[0]).doCommand(userCommand);
				}
			}else{
				if(arg instanceof String){
					view.displayMessage((String)arg);
				}
				else{
					if(view instanceof MyView){
						if(arg instanceof Maze3d){
							Maze3d maze = (Maze3d)arg;
							view.displayMessage(maze.toString());
						}else{
							Solution sol = (Solution)arg;
							view.displayMessage(sol.toString());
						}
					}
					else{
						view.displayMessage(arg);
					}
				}
			}
		}
		
		public void setNewView(View view){
			
			for (Entry<String, Command> e : commands.entrySet()) {
				e.getValue().setView(view);
			}
		}
	}		
