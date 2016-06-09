package presenter;

import model.Model;
import view.View;

public class Dir extends AbstractCommand {

	
	
	public Dir(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(param[1]);
			Model model = this.getModel();
			for(int i =2; i < param.length; i++){
				sb.append(" " + param[i]);
			}
			model.dir(sb.toString());
		}catch(Exception e){
			getView().displayMessage("Please enter valid path\n");
		}
	}

}
