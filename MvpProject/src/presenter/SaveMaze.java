package presenter;

import java.io.File;

import model.Model;
import view.View;

public class SaveMaze extends AbstractCommand {
	
	public SaveMaze(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
			File file = new File(param[2]);
			if(file.exists() == false){
				View view = getView();
				view.displayMessage("File does not exists \n");
			}else{
				Model model = getModel();
				model.saveMaze(param[1],param[2]);
			}
		}catch(Exception e){
			getView().displayMessage("Please enter valid path\n");
		}
	}
}
