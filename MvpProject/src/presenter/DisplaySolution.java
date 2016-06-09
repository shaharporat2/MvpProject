package presenter;

import model.Model;
import view.View;

public class DisplaySolution extends AbstractCommand {

	
	
	public DisplaySolution(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
			getModel().displaySolution(param[1]);
		}catch(Exception e){
			getView().displayMessage("Please enter valid mze name");
		}
		
	}

}
