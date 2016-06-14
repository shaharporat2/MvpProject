package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class MazeWindow extends BasicWindows{

	MazeDisplay mazeDisplay;
	
	
	public MazeDisplay getMazeDisplay() {
		return mazeDisplay;
	}

	public void setMazeDisplay(MazeDisplay mazeDisplay) {
		this.mazeDisplay = mazeDisplay;
	}

	public MazeWindow(Display display, Shell shell, MazeDisplay mazeDisplay) {
		super(display, shell);
		this.mazeDisplay = mazeDisplay;
	}

	@Override
	void initWidgets() {
		getShell().setText("Maze Window");
		getShell().setLayout(new GridLayout(2,true));
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,true));	

		getShell().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.keyCode) {
				case SWT.ARROW_DOWN:
					mazeDisplay.moveDown();
					break;
				case SWT.ARROW_UP:
					break;
				case SWT.ARROW_LEFT:
					break;
				case SWT.ARROW_RIGHT:
					break;
				case SWT.PAGE_DOWN:
					break;
				case SWT.PAGE_UP:
					break;
				default:
					break;
				}
			}
		});
	}
}
