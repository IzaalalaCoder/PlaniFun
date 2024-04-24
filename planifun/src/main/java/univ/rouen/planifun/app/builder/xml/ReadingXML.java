package univ.rouen.planifun.app.builder.xml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import univ.rouen.planifun.app.builder.BuilderTask;
import univ.rouen.planifun.app.builder.ConcreteBuilderTask;
import univ.rouen.planifun.app.editor.model.SetTask;

public class ReadingXML implements XMLScheme, XMLParser {

    // ATTRIBUTES

    private Document document;
    private SetTask model;
    private File file;
    private boolean flag = true;

    // CONSTRUCTOR

    public ReadingXML(File f) throws IOException {
        this.model = null;
        this.document = null;
        this.file = Files.copy(f.toPath(), Paths.get(PATH_XML)).toFile();
        this.flag = true;
        try {
            this.openFile();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.flag = false;
        }
    }

    // REQUESTS

    @Override
    public SetTask getSetTaskInFile() {
        if (!this.flag) {
            throw new AssertionError("Can not create game");
        }
        return model;
    }

    @Override
    public boolean checkXMLFile() {
        return this.flag;
    }

    // COMMANDS

    @Override
    public void readFileXML() throws IOException {
        if (!this.flag) {
            throw new AssertionError("Can not open");
        }
        
        this.browseFile(file);
        Files.delete(this.file.toPath());
    }

    // UTILS

    private void openFile() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw exception;
            }
            
        });
        this.document = builder.parse(file);
    }

    private void browseFile(File file) {
        BuilderTask builder = new ConcreteBuilderTask();
        
        final Element dataElement = (Element) this.document.getElementsByTagName("data").item(0);
        
    }
}
