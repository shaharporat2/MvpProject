package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

abstract public class BasicWindows extends Observable implements Runnable {

	/**
	 * 
	 */
	
	private Display display;
	private Shell Shell;
	
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

}
