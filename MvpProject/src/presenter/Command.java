package presenter;

import view.View;

public interface Command {

	void doCommand(String [] param);
	void setView(View view);
}
