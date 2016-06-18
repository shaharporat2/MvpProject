package view;

import java.lang.reflect.GenericArrayType;
import java.net.URL;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import Utils.Utils;
import algorithms.mazeGenerators.Maze3d;
import boot.LoadFromXml;
import presenter.Properties;

public class MainWindows extends BasicWindows implements View, Runnable {
	
	private Image mazeImg;
	
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
	public void displayMessage(Object object) {
		
	}
	
	private void loadImage(Shell shell){
		
		mazeImg = new Image(getDisplay(),"src/imges/MazeRunner.jpg");
		
		//mazeImg = new Image(getDisplay(),"C:\\Program Files\\Maze\\MazeRunner.jpg");
		/*
		Utils util = new Utils();
		 String resource = util.getClass().getResource("MazeRunner.jpg").getPath();
		try{
			mazeImg = new Image(getDisplay(), resource);
		}catch(Exception e){
			System.out.println("Error loading pic");
		}
		*/
	}
	
	

	@Override
	void initWidgets() {
		getShell().setText("Maze Game");
		getShell().setLayout(new GridLayout(2,false));
		loadImage(getShell());
		getShell().setBackgroundImage(mazeImg);
		Rectangle rect = mazeImg.getBounds();
        getShell().setSize(rect.width,rect.height);
		
		
		
		/*Generate Maze Section*/
		
		Button genreratemaze = new Button(getShell(),SWT.BORDER);
		genreratemaze.setText("GenerateMaze");
		
		// add an event handler for pushing the generate button
		genreratemaze.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
				getDisplay().dispose();
				Display generatedisplay = new Display();
				Shell generateshell = new Shell(generatedisplay);
				generateMazeWindow generateMazeWindow = new generateMazeWindow(generatedisplay, generateshell);
				generateMazeWindow.addObserver(getObserver());
				generateMazeWindow.setObserver(getObserver());
				generateMazeWindow.run();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});

		
		/*Change Configuration*/
		Button changeConfig = new Button(getShell(),SWT.PUSH);
		changeConfig.setText("Change Configuration");
		
		changeConfig.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
				getDisplay().dispose();
				Display configurationDisplay = new Display();
				Shell configurationshell = new Shell(configurationDisplay);
				ConfigurationWindow configurationWindow = new ConfigurationWindow(configurationDisplay, configurationshell);
				configurationWindow.addObserver(getObserver());
				configurationWindow.setObserver(getObserver());
				configurationWindow.run();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
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
