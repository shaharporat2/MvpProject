package presenter;

import model.Model;
import view.View;

public class MazeFileSize extends AbstractCommand {

	
	public MazeFileSize(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
		getModel().mazeFileSize(param[1]);
		}catch(Exception e){
			getView().displayMessage("Please enter valid path\n");
		}
	}

}
