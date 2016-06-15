package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MazeWindow extends BasicWindows implements View{

	Shell shell;
	Display display;
	String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	MazeDisplay mazeDisplay;
	
	
	public MazeDisplay getMazeDisplay() {
		return mazeDisplay;
	}

	public void setMazeDisplay(MazeDisplay mazeDisplay) {
		this.mazeDisplay = mazeDisplay;
	}

	public MazeWindow(Display display, Shell shell, MazeDisplay mazeDisplay) {
		super(display, shell);
		this.display = display;
		this .shell = shell;
		this.mazeDisplay = mazeDisplay;
	}

	@Override
	void initWidgets() {
		getShell().setText("Maze Window");
		getShell().setLayout(new GridLayout(2,false));
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,true));
		Listner();
		Menu maMenu = new Menu(getShell(), SWT.BAR);
		MenuItem file = new MenuItem(maMenu, SWT.CASCADE);
		file.setText("&File");
		MenuItem help = new MenuItem(maMenu, SWT.CASCADE);
		help.setText("&help");
		Menu fileMenu = new Menu(getShell(),SWT.DROP_DOWN);
		file.setMenu(fileMenu);
		Menu helpMenu = new Menu(getShell(),SWT.DROP_DOWN);
		help.setMenu(helpMenu);
		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
		MenuItem hint = new MenuItem(helpMenu, SWT.PUSH);
		hint.setText("&hint");
		getShell().setMenuBar(maMenu);
		getShell().setFocus();
		
		SelectionListener hintListner = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				setChanged();
				BuildingCommand("solve " + name + " def");
				notifyObservers(getPharseCommand());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		};
		
		hint.addSelectionListener(hintListner);
				
		exitItem.addListener(SWT.Selection, event-> {
		    getShell().getDisplay().dispose();
		});
	}
	
	
	
	
	@Override
	public void displayMessage(String msg) {
		if(msg.contains("is ready")){
			setChanged();
			BuildingCommand("display_solution " + name);
			notifyObservers(getPharseCommand());
		}
	}

	@Override
	public void displayMessage(Object object) {
		String hintpath = mazeDisplay.displayHint(object);		
	}

	public void Listner(){
		getShell().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.keyCode) {
				case SWT.ARROW_DOWN:
					mazeDisplay.moveXUp();
					break;
				case SWT.ARROW_UP:
					mazeDisplay.moveXDown();
					break;
				case SWT.ARROW_LEFT:
					mazeDisplay.moveZDown();
					break;
				case SWT.ARROW_RIGHT:
					mazeDisplay.moveZUp();
					break;
				case SWT.PAGE_DOWN:
					mazeDisplay.moveFloorDown();
					break;
				case SWT.PAGE_UP:
					mazeDisplay.moveFloorUp();
					break;
				default:
					break;
				}
			}
		});
	}
}
