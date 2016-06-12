package presenter;

import view.View;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;

public class Presenter implements Observer {

		/**
		 * 
		 */
	
		private Model model;
		private View view;
		
		
		private HashMap<String, Command> commands = new HashMap<>();
		
		public Presenter(Model model, View view) {
			super();
			this.model = model;
			this.view = view;
			
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

		/**
		 * 
		 */
		@Override
		public void update(Observable o, Object arg) {
			if(o instanceof View){
				view = (View)o;
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
					
				}
			}
		}		
}
