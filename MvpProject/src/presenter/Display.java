package presenter;

import model.Model;
import view.View;

public class Display extends AbstractCommand {
		
	public Display(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
		Model model = getModel();
		model.display(param[1]);
		}catch(Exception e){
			getView().displayMessage("Please enter maze name\n");
		}
	}

}
