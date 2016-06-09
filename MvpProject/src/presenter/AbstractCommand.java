package presenter;

import model.Model;
import view.View;

public abstract class AbstractCommand implements Command {

	private Model model;
	private View view;
	
	public AbstractCommand(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	
	
	
	
}
