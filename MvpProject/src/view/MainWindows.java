package view;

import java.lang.reflect.GenericArrayType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import algorithms.mazeGenerators.Maze3d;

public class MainWindows extends BasicWindows implements View, Runnable {
	
	public MainWindows(Display display, Shell shell) {
		super(display,shell);
	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMessage(String msg) {
		super.displayMessage(msg);
	}
	
	@Override
	public void displayMessage(Maze3d maze) {
	}
	
	

	@Override
	void initWidgets() {
		getShell().setSize(250, 300);
		getShell().setText("Maze Game");
		getShell().setLayout(new GridLayout(2,false));
		//getShell().setBackgroundImage(new Image(getDisplay(),this.getClass().getResource("MazeRunner.jpg").getPath()));
		//getShell().setBackgroundMode(SWT.BACKGROUND);		
		//Generate maze section
		
		
		
		/*Generate Maze Section*/
		
		Button genreratemaze = new Button(getShell(),SWT.BORDER);
		genreratemaze.setText("GenerateMaze");
		
		// add an event handler for pushing the generate button
		genreratemaze.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});

		
		
		/* Start Exit Section*/
		Button exit = new Button(getShell(),SWT.PUSH);
		exit.setText("exit");
		
		exit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				BuildingCommand("exit");
				setChanged();
				notifyObservers(getPharseCommand());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		/*End Exit Section*/

		
		
	
	}

}
