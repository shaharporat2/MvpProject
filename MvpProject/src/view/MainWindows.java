package view;

import java.lang.reflect.GenericArrayType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
public class MainWindows extends BasicWindows implements View, Runnable {


	public MainWindows(Display display, Shell shell) {
		super();
		setDisplay(display);
		setShell(shell);
		
	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMessage(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	void initWidgets() {
		getShell().setText("Maze Game");
		getShell().setLayout(new GridLayout(2,false));
		getShell().setBackgroundImage(new Image(getDisplay(),this.getClass().getResource("MazeRunner.jpg").getPath()));
		//getShell().setBackgroundMode(SWT.BACKGROUND);
		
	
		Button genreratemaze = new Button(getShell(),SWT.BORDER);
		genreratemaze.setText("GenerateMaze");
		//displayer = new MazeDisplay(getShell(), 0);
		
		Button exit = new Button(getShell(),SWT.PUSH);
		exit.setText("exit");

		
		
	
	}

}
