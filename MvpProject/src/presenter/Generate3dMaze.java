package presenter;

import model.Model;
import view.View;

public class Generate3dMaze extends AbstractCommand {
	
	
	public Generate3dMaze(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {
		if(param[1].equals("useDe")){
				Properties properties = new Properties();
				param = properties.getDefaultMaze();
				long time=System.currentTimeMillis();
				param[1] = param[1] + String.valueOf(time); 
		}
		try{
			String name = param[1];
			int floor = Integer.parseInt(param[2]);
			int rows = Integer.parseInt(param[3]);
			int cols = Integer.parseInt(param[4]);
			Model model = this.getModel();
			model.generateMaze3D(name, floor, rows, cols);
		}catch(Exception e){
			getView().displayMessage("Maze generation failed\n");
		}
	}

}
