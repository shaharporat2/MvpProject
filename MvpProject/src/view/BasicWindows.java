package view;

import java.util.Observable;
import java.util.Observer;

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
	private Observer observer;
	
	
	
	public BasicWindows(Display display, Shell shell) {
		super();
		this.display = display;
		this.Shell = shell;
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
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
		System.out.println(msg);
	}

	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	@Override
	public void displayMessage(Object object) {
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
