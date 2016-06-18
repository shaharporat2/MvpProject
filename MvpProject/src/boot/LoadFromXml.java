package boot;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import presenter.Properties;


public class LoadFromXml {
	public Properties load(){
		try{
			String path = "C:\\Program Files\\Maze\\properties1.xml";
			//String path = "src/properties/properties1.xml";
			XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
			Properties properties = (Properties)xmlDecoder.readObject();
			return properties;
		}catch(Exception e){
			
		}
		return null;
	}
}
