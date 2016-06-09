package presenter;

import model.Model;
import view.View;

public class DisplayCrossSection extends AbstractCommand {
	
	public DisplayCrossSection(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		String s = "for";
		if(param[3].equals(s)){
			Model model = getModel();
			char c = param[1].charAt(0);
			int index = Integer.parseInt(param[2]);	
			model.displayCrossSection(c,index,param[4]);
		}else{
			View view = getView();
			view.displayMessage("Command dont exiest \n");
		}
	}
}
