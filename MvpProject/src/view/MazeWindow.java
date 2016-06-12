package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MazeWindow extends BasicWindows {

	public MazeWindow(Display display, Shell shell) {
		super(display, shell);
		// TODO Auto-generated constructor stub
	}

	@Override
	void initWidgets() {
		getShell().setText("Maze Window");
		getShell().setLayout(new GridLayout(2,true));
		
		//MazeDisplay maze = new MazeDisplay(getShell(),SWT.BORDER);
		//maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,true));
	}

}
