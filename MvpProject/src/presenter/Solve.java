package presenter;

import model.Model;
import view.View;

public class Solve extends AbstractCommand {
	
	public Solve(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
			getModel().solve(param[1],param[2]);
		}catch(Exception e){
			getView().displayMessage("Please enter valid maze name or valid Algorithm\n");
		}
	}

}
