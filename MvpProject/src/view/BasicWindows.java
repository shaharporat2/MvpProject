package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;

abstract public class BasicWindows extends Observable implements Runnable, View {

	/**
	 * 
	 */
	
	
	private String[] pharseCommand;
	private Display display;
	private Shell Shell;
	
	
	public BasicWindows() {
		super();
		display = new Display();
		Shell = new Shell(display);
	}
	
	public BasicWindows(Display display, Shell shell) {
		super();
		this.display = display;
		this.Shell = shell;
	}

	abstract void initWidgets();
	
	@Override
	public void run() {
		this.initWidgets();
		Shell.open();
		while(!Shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Shell getShell() {
		return Shell;
	}

	public void setShell(Shell shell) {
		Shell = shell;
	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMessage(String msg) {
		if(msg.contains("exit")){
			Shell.dispose();
		}
	}

	@Override
	public void displayMessage(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}
	
	public void BuildingCommand(String commands){
		pharseCommand = commands.split("\\s+");
		
	}

	public String[] getPharseCommand() {
		return pharseCommand;
	}

	public void setPharseCommand(String[] pharseCommand) {
		this.pharseCommand = pharseCommand;
	}
	
	
	

}
