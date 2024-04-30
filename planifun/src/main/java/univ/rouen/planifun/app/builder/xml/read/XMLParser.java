package univ.rouen.planifun.app.builder.xml.read;

import java.io.IOException;
import univ.rouen.planifun.app.editor.model.SetTask;

/**
 * Manage read of XML file for recreate todolist
 */
public interface XMLParser {

    // CONSTANTS

    String PATH_XML = "src/main/resources/xml/save.xml";

    // REQUESTS

    /**
     * getSetTaskInFile : return generated task list
     * @return SetTask
     */
    SetTask getSetTaskInFile();
    
    // COMMANDS

    /**
     * readFileXML : read current file for generated task list
     * @throws IOException : error on open file
     */
    void readFileXML() throws IOException;

}