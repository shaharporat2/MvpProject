package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;

public class generateMazeWindow extends BasicWindows {

	public generateMazeWindow(Display display, Shell shell) {
		super(display, shell);
		// TODO Auto-generated constructor stub
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
					
				}
				getShell().dispose();
				getDisplay().dispose();
				Display mazeDisplay = new Display();
				Shell mazeShell = new Shell(mazeDisplay);
				MazeWindow mazeWindow = new MazeWindow(mazeDisplay, mazeShell);
				
				mazeWindow.addObserver(getObserver());
				mazeWindow.run();
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
			notifyObservers(command);
		}
		
	}

	@Override
	public void displayMessage(Object object) {
		System.out.println("hello");
	}
	
}
