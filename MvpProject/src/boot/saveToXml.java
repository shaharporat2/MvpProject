package boot;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import presenter.Properties;

public class saveToXml {
	
	
	
	public static void main(String[] args){
		File file = new File("C:\\Program Files\\Maze\\properties.xml");
	
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			PrintWriter writer = new PrintWriter("C:\\Program Files\\Maze\\properties.xml");
			Properties properties = new Properties();
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
			xmlStreamWriter.writeStartDocument();
			xmlStreamWriter.writeStartElement("maze");
			xmlStreamWriter.writeStartElement("SystemFiles");
			xmlStreamWriter.writeAttribute("solutionsFile",properties.getSolutionsFilePath());
			xmlStreamWriter.writeAttribute("LogFilePath",properties.getLogFilePath());
			xmlStreamWriter.writeEndElement();
			xmlStreamWriter.writeStartElement("Defaults");
			xmlStreamWriter.writeAttribute("GenerateMaze",properties.getMazeGenerate());
			xmlStreamWriter.writeAttribute("MaxThread",Integer.toString(properties.getMaxNumOfThread()));
			xmlStreamWriter.writeEndElement();
			xmlStreamWriter.writeEndDocument();
			xmlStreamWriter.flush();
			xmlStreamWriter.close();
			
			String xmlString = stringWriter.getBuffer().toString();
			stringWriter.close();
			writer.print(xmlString);
			writer.close();
			
		}catch(XMLStreamException e){
			System.out.println("Error in XML");
		}catch (IOException e) {
			System.out.println("Error opening file");
		}
	}
}
