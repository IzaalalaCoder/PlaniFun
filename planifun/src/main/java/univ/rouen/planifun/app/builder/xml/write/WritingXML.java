package univ.rouen.planifun.app.builder.xml.write;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import univ.rouen.planifun.app.builder.xml.XMLElement;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BasicTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

public class WritingXML implements XMLWriter {

    // ATTRIBUTES

    private final SetTask model;
    private File save;

    // CONSTRUCTOR

    public WritingXML(SetTask tasks) {
        this.model = tasks;
        this.save = null;
    }

    // REQUESTS

    @Override
    public File getGeneratedFile() {
        return this.save;
    }

    // COMMANDS

    @Override
    public void writeXMLFile() throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        
        String qualifiedName = "todo";
        String systemId = "todolist.dtd";
        DocumentType doctype = document.getImplementation().createDocumentType(qualifiedName, null, systemId);
        document.appendChild(doctype);

        // Création de l'élément racine
        Element root = document.createElement("todo");
        document.appendChild(root);
        
        // Add data information
        final Element data = document.createElement(XMLElement.DATA.getTagName());

        final Element name = document.createElement(XMLElement.NAME.getTagName());
        name.setTextContent(model.getName());
        data.appendChild(name);

        // recup date
        Calendar c = new GregorianCalendar();
        c.setTime(this.model.getCreationDate());

        // Add default time -----------------------------------------
        
        final Element time = document.createElement(XMLElement.TIME.getTagName());
        this.addTime(time, document, c);
        data.appendChild(time);
        root.appendChild(data);

        // Add all tasks

        final Element tasks = document.createElement(XMLElement.TASKS.getTagName());
        this.addAllTasks(tasks, document);
        root.appendChild(tasks);

        // Écriture du contenu dans un fichier XML

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        this.save = new File(PATH_XML);
        StreamResult result = new StreamResult(save);

        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");      
        transformer.setOutputProperty("doctype-system", "todolist.dtd");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
    }

    private void addTime(Element parent, Document doc, Calendar c) {
        final Element year = doc.createElement(XMLElement.YEAR.getTagName());
        year.setTextContent(Integer.toString(c.get(Calendar.YEAR)));
        parent.appendChild(year);

        final Element month = doc.createElement(XMLElement.MONTH.getTagName());
        month.setTextContent(Integer.toString(c.get(Calendar.MONTH)));
        parent.appendChild(month);

        final Element day = doc.createElement(XMLElement.DAY.getTagName());
        day.setTextContent(Integer.toString(c.get(Calendar.DAY_OF_MONTH)));
        parent.appendChild(day);
        
        final Element hour = doc.createElement(XMLElement.HOUR.getTagName());
        hour.setTextContent(Integer.toString(c.get(Calendar.HOUR)));
        parent.appendChild(hour);
        
        final Element minute = doc.createElement(XMLElement.MINUTE.getTagName());
        minute.setTextContent(Integer.toString(c.get(Calendar.MINUTE)));
        parent.appendChild(minute);

        final Element second = doc.createElement(XMLElement.SECOND.getTagName());
        second.setTextContent(Integer.toString(c.get(Calendar.SECOND)));
        parent.appendChild(second);
    }

    private void addAllTasks(Element parent, Document doc) {
        for (Task t : this.model.getAllTask()) {
            final Element task = doc.createElement(XMLElement.TASK.getTagName());
            task.setAttribute(XMLElement.PRIORITY.getTagName(), t.getPriority().name());
            
            final Element description = doc.createElement(XMLElement.DESCRIPTION.getTagName());
            description.setTextContent(t.getDescription());
            task.appendChild(description);

            if (t instanceof BasicTask) {
                final Element completion = doc.createElement(XMLElement.COMPLETION.getTagName());
                completion.setTextContent(Integer.toString(t.getCompletionDate()));

                final Element progress = doc.createElement(XMLElement.PROGRESS.getTagName());
                progress.setTextContent(Double.toString(t.getProgressStatus()));
                progress.setAttribute(XMLElement.MODE.getTagName(), t instanceof NormalTask ? "NORMAL" : "BOOLEAN");

                task.appendChild(completion);
                task.appendChild(progress);
            } else {
                final Element sub = doc.createElement(XMLElement.SUB.getTagName());
                this.addSubAllTasks(sub, doc, (ComplexTask) t);
                task.appendChild(sub);
            }

            parent.appendChild(task);
        }
    }

    private void addSubAllTasks(Element parent, Document doc, ComplexTask complexTask) {
        for (Task t : complexTask.getAllSubTasks()) {
            final Element task = doc.createElement(XMLElement.TASK.getTagName());
            task.setAttribute(XMLElement.PRIORITY.getTagName(), t.getPriority().name());
            
            final Element description = doc.createElement(XMLElement.DESCRIPTION.getTagName());
            description.setTextContent(t.getDescription());
            task.appendChild(description);

            if (t instanceof BasicTask) {
                final Element completion = doc.createElement(XMLElement.COMPLETION.getTagName());
                completion.setTextContent(Integer.toString(t.getCompletionDate()));

                final Element progress = doc.createElement(XMLElement.PROGRESS.getTagName());
                progress.setTextContent(Double.toString(t.getProgressStatus()));
                progress.setAttribute(XMLElement.MODE.getTagName(), t instanceof NormalTask ? "NORMAL" : "BOOLEAN");

                task.appendChild(completion);
                task.appendChild(progress);
            } else {
                final Element sub = doc.createElement(XMLElement.SUB.getTagName());
                this.addSubAllTasks(sub, doc, (ComplexTask) t);
                task.appendChild(sub);
            }

            parent.appendChild(task);
        }
    }
}

