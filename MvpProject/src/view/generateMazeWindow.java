package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

	


public class generateMazeWindow extends BasicWindows {
	
	Shell shell;
	Display display;
	String name;

	public generateMazeWindow(Display display, Shell shell) {
		super(display, shell);
		this.shell = shell;
		this.display = display;
	}

	@Override
	void initWidgets() {
		getShell().setLayout(new GridLayout(2,false));
		getShell().setText("generate maze");
		getShell().setSize(300,200);
		new Label(getShell(), SWT.NONE).setText("name: ");
		Text name= new Text(getShell(), SWT.BORDER);
		new Label(getShell(), SWT.NONE).setText("floor: ");
		Text floor=new Text(getShell(), SWT.BORDER);
		new Label(getShell(), SWT.NONE).setText("rows: ");
		Text cols=new Text(getShell(), SWT.BORDER);
		new Label(getShell(), SWT.NONE).setText("cols: ");
		Text rows=new Text(getShell(), SWT.BORDER);
		Button defaulti =new Button(getShell(), SWT.CHECK);
		defaulti.setText("use default");
		Button generateMaze=new Button(getShell(),SWT.PUSH);
		generateMaze.setText("create maze");
		
		
		defaulti.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(defaulti.getSelection() == true){
					name.setBackground(new Color(null, 255,105,180));
					name.setEnabled(false);
					
					floor.setBackground(new Color(null,255,105,180));
					floor.setEnabled(false);
					
					rows.setBackground(new Color(null, 255,105,180));
					rows.setEnabled(false);
					
					cols.setBackground(new Color(null, 255,105,180));
					cols.setEnabled(false);
				}
				else{
					name.setBackground(new Color(null, 255,255,255));
					name.setEnabled(true);
					
					floor.setBackground(new Color(null, 255,255,255));
					floor.setEnabled(true);
					
					rows.setBackground(new Color(null, 255,255,255));
					rows.setEnabled(true);
					
					cols.setBackground(new Color(null, 255,255,255));
					cols.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		
		generateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(defaulti.getSelection() == true)
				{
					String[] parameter = new String[2];
					parameter[0] = "generate_maze_3d";
					parameter[1] = "useDe";
					setChanged();
					notifyObservers(parameter);
					
				}
				else{
					String [] parameter = new String[5];
					parameter[0] = "generate_maze_3d";
					parameter[1] = name.getText();
					parameter[2] = floor.getText();
					parameter[3] = rows.getText();
					parameter[4] = cols.getText();
					setChanged();
					notifyObservers(parameter);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});
		
		
	}

	@Override
	public void displayMessage(String msg) {
		if( msg.contains("is ready")){
			setPharseCommand(msg.split("\\s+"));
			String[] mazeName = getPharseCommand();
			setChanged();
			String [] command = new String[2];
			command[0] ="display";
			command[1] = mazeName[1];
			name = mazeName[1];
			notifyObservers(command);
		}
		if(msg.contains("Maze generation failed")){
			MessageBox messageBox = new MessageBox(getShell());
			messageBox.setText("You has enterd invalid values");
			messageBox.open();
		}
		
	}

	@Override
	public void displayMessage(Object object) {
		
		Maze3d maze = (Maze3d)object;
		Position start = maze.getStartPosition();
		int [][] currentfloor = maze.getCrossSectionByY(start.getFloor());
		Display mazeDisplay = new Display();
		Shell mazeShell = new Shell(mazeDisplay);
		MazeDisplay mazeDisplay2 = new MazeDisplay(mazeShell, SWT.BORDER,maze);
		mazeDisplay2.setMazeData(currentfloor);
		MazeWindow mazeWindow = new MazeWindow(mazeDisplay, mazeShell,mazeDisplay2);
		mazeWindow.addObserver(getObserver());
		mazeWindow.setObserver(getObserver());
		mazeWindow.setName(name);
		mazeWindow.run();
		
	}
	
}
