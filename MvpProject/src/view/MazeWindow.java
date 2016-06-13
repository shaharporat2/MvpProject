package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MazeWindow extends BasicWindows {

	MazeDisplay mazeDisplay;
	
	
	public MazeDisplay getMazeDisplay() {
		return mazeDisplay;
	}

	public void setMazeDisplay(MazeDisplay mazeDisplay) {
		this.mazeDisplay = mazeDisplay;
	}

	public MazeWindow(Display display, Shell shell, MazeDisplay mazeDisplay) {
		super(display, shell);
		this.mazeDisplay = mazeDisplay;
	}

	@Override
	void initWidgets() {
		getShell().setText("Maze Window");
		getShell().setLayout(new GridLayout(2,true));
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,true));
	}

}
