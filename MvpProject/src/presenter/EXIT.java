package presenter;

import model.Model;
import view.View;

public class EXIT extends AbstractCommand {
		
	public EXIT(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		getModel().exit();
	}

}
