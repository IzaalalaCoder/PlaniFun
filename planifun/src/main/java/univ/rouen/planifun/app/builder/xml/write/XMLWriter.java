package univ.rouen.planifun.app.builder.xml.write;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Manage writer of XML file for save todolist
 */
public interface XMLWriter {

    // CONSTANTS

    String PATH_XML = "src/main/resources/xml/new_save.xml";

    // REQUESTS

    /**
     * getGeneratedFile : return generate file
     * @return File
     * @throws IOException : error on open file
     */
    File getGeneratedFile() throws IOException;

    // COMMANDS

    /**
     * writeXMLFile : write a new file
     * @throws ParserConfigurationException : error on config parse
     * @throws TransformerException : error on file transform
     */
    void writeXMLFile() throws ParserConfigurationException, TransformerException;

}