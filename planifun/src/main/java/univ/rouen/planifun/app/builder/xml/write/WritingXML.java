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
        final Element data = document.createElement("data");

        final Element name = document.createElement("name");
        name.setTextContent(model.getName());
        data.appendChild(name);

        // recup date
        Calendar c = new GregorianCalendar();
        c.setTime(this.model.getCreationDate());

        // Add default time -----------------------------------------
        final Element time = document.createElement("time");

        final Element year = document.createElement("year");
        year.setTextContent(Integer.toString(c.get(Calendar.YEAR)));
        time.appendChild(year);

        final Element month = document.createElement("month");
        month.setTextContent(Integer.toString(c.get(Calendar.MONTH)));
        time.appendChild(month);

        final Element day = document.createElement("day");
        day.setTextContent(Integer.toString(c.get(Calendar.DAY_OF_MONTH)));
        time.appendChild(day);
        
        final Element hour = document.createElement("hour");
        hour.setTextContent(Integer.toString(c.get(Calendar.HOUR)));
        time.appendChild(hour);
        
        final Element minute = document.createElement("minute");
        minute.setTextContent(Integer.toString(c.get(Calendar.MINUTE)));
        time.appendChild(minute);

        final Element second = document.createElement("second");
        second.setTextContent(Integer.toString(c.get(Calendar.SECOND)));
        time.appendChild(second);
        data.appendChild(time);
        // Add default time -----------------------------------------
        
        root.appendChild(data);

        // Add all tasks

        final Element tasks = document.createElement("tasks");

        this.addAllTasks(tasks, document);
        
        // Add all tasks

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

    private void addAllTasks(Element parent, Document doc) {
        for (Task t : this.model.getAllTask()) {
            final Element task = doc.createElement("task");
            task.setAttribute("priority", t.getPriority().name());
            
            final Element description = doc.createElement("description");
            description.setTextContent(t.getDescription());
            task.appendChild(description);

            if (t instanceof BasicTask) {
                final Element completion = doc.createElement("completion");
                completion.setTextContent(Integer.toString(t.getCompletionDate()));

                final Element progress = doc.createElement("progress");
                progress.setTextContent(Double.toString(t.getProgressStatus()));
                progress.setAttribute("mode", t instanceof NormalTask ? "NORMAL" : "BOOLEAN");

                task.appendChild(completion);
                task.appendChild(progress);
            } else {
                final Element sub = doc.createElement("sub");
                this.addSubAllTasks(sub, doc, (ComplexTask) t);
                task.appendChild(sub);
            }

            parent.appendChild(task);
        }
    }

    private void addSubAllTasks(Element parent, Document doc, ComplexTask complexTask) {
        for (Task t : complexTask.getAllSubTasks()) {
            final Element task = doc.createElement("task");
            task.setAttribute("priority", t.getPriority().name());
            
            final Element description = doc.createElement("description");
            description.setTextContent(t.getDescription());
            task.appendChild(description);

            if (t instanceof BasicTask) {
                final Element completion = doc.createElement("completion");
                completion.setTextContent(Integer.toString(t.getCompletionDate()));

                final Element progress = doc.createElement("progress");
                progress.setTextContent(Double.toString(t.getProgressStatus()));
                progress.setAttribute("mode", t instanceof NormalTask ? "NORMAL" : "BOOLEAN");

                task.appendChild(completion);
                task.appendChild(progress);
            } else {
                final Element sub = doc.createElement("sub");
                this.addSubAllTasks(sub, doc, (ComplexTask) t);
                task.appendChild(sub);
            }

            parent.appendChild(task);
        }
    }
}

