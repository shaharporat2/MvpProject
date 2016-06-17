package presenter;

import java.util.LinkedList;

import model.Model;
import view.View;

public class SaveConfiguration extends AbstractCommand {

	public SaveConfiguration(Model model, View view) {
		super(model, view);
	}

	@Override
	public void doCommand(String[] param) {

		StringBuilder sb = new StringBuilder();
		LinkedList<String> args = new LinkedList<>();
		int i = 1;
		 while (i < param.length-1)
			if(param[i].contains("-")){
				args.addLast(param[i]);
				i++;
			}else if(param[i].contains("\\")){
				sb.append(param[i]);
				i++;
				while(param[i].contains("\\")){
					sb.append(" " + param[i]);
					if((i >= param.length-1)){
						break;
					}
					i++;
				}
				args.addLast(sb.toString());
			}else{
				args.addLast(param[i]);
				i++;
			}
		 getModel().SaveConfiguration(args);
		}
	}
