package view;

import javax.xml.bind.Marshaller.Listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class ConfigurationWindow extends BasicWindows implements View{

	public ConfigurationWindow(Display display, org.eclipse.swt.widgets.Shell shell) {
		super(display, shell);
	}

	@Override
	void initWidgets() {
		getShell().setLayout(new FillLayout());
		Group group = new Group(getShell(),SWT.FILL);
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		group.setLayout(new GridLayout(2,true));
		Rectangle rec = group.getBounds();
		getShell().setText("Configuration window");
		new Label(group, SWT.NONE).setText("Default Solve Algorithm: ");
		Text DefaultSolve= new Text(group, SWT.BORDER);
		new Label(group, SWT.NONE).setText("Default log file path: ");
		Text LogFilePath=new Text(group, SWT.BORDER);
		new Label(group, SWT.NONE).setText("Default program path: ");
		Text ProgramPath=new Text(group, SWT.BORDER);
		new Label(group, SWT.NONE).setText("Default solutions file path: ");
		Text SolutionsFilePath=new Text(group, SWT.BORDER);
		new Label(group, SWT.NONE).setText("Default maximum number of threads: ");
		Text MaxNumOfThread=new Text(group, SWT.BORDER);
		new Label(group, SWT.NONE).setText("Default User interface: ");
		Text DefaultUserInterface=new Text(group, SWT.BORDER);
		new Label(group, SWT.NONE).setText("Default Maze generator: ");
		Text MazeGenerate=new Text(group, SWT.BORDER);
		
		Button defaulti =new Button(group, SWT.CHECK);
		defaulti.setText("use default");
		Button submit=new Button(group,SWT.PUSH);
		submit.setText("Submit change");
		
		
		submit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(getShell(),SWT.ICON_WARNING | SWT.YES | SWT.CANCEL);
				messageBox.setText("Changing configuration warning");
				messageBox.setMessage("Changing system configuration may cause problem are you you want to continue?");
				int choose = messageBox.open();
				switch (choose) {
				case SWT.YES:
				case SWT.NO:
					break;
				default:
					break;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
