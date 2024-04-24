package univ.rouen.planifun.app.builder.xml;

import java.io.IOException;
import univ.rouen.planifun.app.editor.model.SetTask;

public interface XMLParser {

    // CONSTANTS

    public String PATH_DTD = "planifun/src/main/resources/xml/todolist.dtd";
    public final String PATH_XML = "planifun/src/main/resources/xml/save.xml";

    // REQUESTS

    public SetTask getSetTaskInFile();

    // COMMANDS

    public void readFileXML() throws IOException;
}
