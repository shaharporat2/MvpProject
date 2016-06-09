package boot;

import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import presenter.Properties;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;

public class LoadFromXml {
	public Properties load(){
		String propertiespath = "C:\\Program Files\\Maze\\properties.xml";
		Properties properties = new Properties();
		try{
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(propertiespath));
			while (eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String value = startElement.getName().getLocalPart();
	                if (value.equalsIgnoreCase("SystemFiles")) {
	                     Iterator<Attribute> attributes = startElement.getAttributes();
	                     while(attributes.hasNext()){
	                    	String path = attributes.next().getValue();
	                    	if(attributes.next().toString().startsWith("solutionsFile")){
	                    		properties.setLogFilePath(path);
	                    	}else{
	                    	properties.setSolutionsFilePath(path);
	                    	}
	                     }
	                }
	                if(value.equalsIgnoreCase("Defaults")){
	                	Iterator<Attribute> attributes = startElement.getAttributes();
	                	while(attributes.hasNext()){
	                    	String attr = attributes.next().getValue();
	                    	if(attributes.next().toString().startsWith("GenerateMaze")){
	                    		properties.setMazeGenerate(attr);
	                    	}else{
	                    		properties.setMaxNumOfThread(Integer.parseInt(attr));
	                    	}
	                     }
	                }
					break;
				default:
					break;
				}
			}
		}catch(Exception e){
			
		}
		return properties;
	}
}
