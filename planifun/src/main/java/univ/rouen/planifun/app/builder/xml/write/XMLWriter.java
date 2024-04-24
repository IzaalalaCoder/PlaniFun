package univ.rouen.planifun.app.builder.xml.write;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface XMLWriter {

    // CONSTANTS

    public String PATH_XML = "planifun/src/main/resources/xml/new_save.xml";

    // REQUESTS

    public File getGeneratedFile() throws IOException;

    // COMMANDS

    public void writeXMLFile() throws ParserConfigurationException, TransformerException ;
}
