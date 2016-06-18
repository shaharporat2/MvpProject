package view;

import java.util.LinkedList;

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
		//new Label(group, SWT.NONE).setText("Default Maze generator: ");
		//Text MazeGenerate=new Text(group, SWT.BORDER);
		

		Button submit=new Button(group,SWT.PUSH);
		submit.setText("Submit change");
		
		
		submit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(getShell(),SWT.ICON_WARNING | SWT.YES | SWT.NO);
				messageBox.setText("Changing configuration warning");
				messageBox.setMessage("Changing system configuration may cause problem are you you want to continue?");
				int choose = messageBox.open();
				switch (choose) {
				case SWT.YES:
					
					String[] string=new String[7];
					string[0]=DefaultSolve.getText();
					string[1]=LogFilePath.getText();
					string[2]=ProgramPath.getText();
					string[3]=SolutionsFilePath.getText();
					string[4]=MaxNumOfThread.getText();
					string[5]=DefaultUserInterface.getText();
					
					String[] parameters=new String[13];
					int i=1;
					parameters[0]="save_configuration";	
					
						if(!string[0].isEmpty()){
							parameters[i]="-defaultSolve";
							i++;
							parameters[i]=string[0];
							i++;
						}
						if(!string[1].isEmpty()){
							parameters[i]="-LogFilePath";
							i++;
							parameters[i]=string[1];
							i++;
						}
						if(!string[2].isEmpty()){
							parameters[i]="-ProgramPath";
							i++;
							parameters[i]=string[2];
							i++;
						}
						if(!string[3].isEmpty()){
							parameters[i]="-solutionsFilePath";
							i++;
							parameters[i]=string[3];
							i++;
						}
						if(!string[4].isEmpty()){
							parameters[i]="-maxNumOfThread";
							i++;
							parameters[i]=string[4];
							i++;
						}						
						if(!string[5].isEmpty()){
							parameters[i]="-defaultUserInterface";
							i++;
							parameters[i]=string[5];
							i++;
						}
						
					String[] s=new String[i];
					for (int j = 0; j < i; j++) {
						s[j]=parameters[j];
					}

					setChanged();
					notifyObservers(s);
	
						
					case SWT.NO:
						break;
					default:
						break;
				}

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		
		
	}

	@Override
	public void displayMessage(String msg) {
		if(msg.contains("XML File has been change")){
			MessageBox messageBox = new MessageBox(getShell());
			messageBox.setMessage(msg);
			messageBox.setText("Configuration change");
			messageBox.open();
		}else{
			MessageBox messageBox = new MessageBox(getShell(),SWT.ICON_ERROR);
			messageBox.setMessage(msg);
			messageBox.setText("Configuration change");
			messageBox.open();
		}
	}
}
