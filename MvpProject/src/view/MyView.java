package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;

public class MyView extends Observable implements View {

	BufferedReader in;
	PrintWriter out;
	String[] pharseCommand;
	
	public MyView(PrintWriter out, BufferedReader in) {
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void getUserCommand(){
		boolean running = true;
		String command = new String();
		System.out.println("Please enter command: ");
		while(running){
			try {
				command = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				pharseCommand = command.split("\\s+");
				if(command.equals("exit")){
					running = false;
				}
				this.setChanged();
				this.notifyObservers(pharseCommand);
			}
		System.out.println("Exiting CLI");	
	}

	@Override
	public void notifyObservers(Object arg) {
		super.notifyObservers(arg);
	}
	
	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}
	
	@Override
	public void displayMessage(String msg){
		out.write(msg);
		out.flush();
	}
	@Override
	public void displayMessage(Object object){
		
	}

}
