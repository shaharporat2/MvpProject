package presenter;

import model.Model;
import view.View;

public class LoadMaze extends AbstractCommand {

	
	public LoadMaze(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
		Model model = getModel();
		model.loadMaze(param[1],param[2]);
		}catch(Exception e){
			getView().displayMessage("Please enter valid path\n");
		}
	}
}
