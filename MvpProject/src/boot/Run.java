package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import model.Model;
import model.Mymodel;
import presenter.Presenter;
import presenter.Properties;
import view.BasicWindows;
import view.MainWindows;
import view.MazeDisplay;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
		
		/**
		 * 
		 */
		LoadFromXml loadFromXml = new LoadFromXml();
		Properties properties = loadFromXml.load();
		
		
		if(properties.getDefaultUserInterface().equals("CLI"))
		{
			PrintWriter out= new PrintWriter(new OutputStreamWriter(System.out));
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			MyView view = new MyView(out,in);
			Mymodel model = new Mymodel(properties);
			Presenter presenter = new Presenter(model,view,properties);
			view.addObserver(presenter);
			model.addObserver(presenter);
			view.getUserCommand();
	}else{
			String s = new String();
			properties =  loadFromXml.load();
			System.out.println(properties.toString());
			Mymodel model = new Mymodel(properties);
			Display display = new Display();
			Shell shell = new Shell(display);
			BasicWindows mainWindows = new MainWindows(display, shell);
			Presenter presenter = new Presenter(model,mainWindows, properties);
			mainWindows.addObserver(presenter);
			model.addObserver(presenter);
			mainWindows.setObserver(presenter);
			mainWindows.run();
		}
	}
}
