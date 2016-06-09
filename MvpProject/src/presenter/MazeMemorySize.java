package presenter;

import model.Model;
import view.View;

public class MazeMemorySize extends AbstractCommand {

	public MazeMemorySize(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
		getModel().mazeMemorySize(param[1]);
		}catch(Exception e){
			getView().displayMessage("Pleas enter valid name\n");
		}
	}
}
