package univ.rouen.planifun.app.builder.xml.read;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import univ.rouen.planifun.app.builder.BuilderTask;
import univ.rouen.planifun.app.builder.ConcreteBuilderTask;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BasicTask;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

public class ReadingXML implements XMLScheme, XMLParser {

    // ATTRIBUTES

    private Document document;
    private SetTask model;
    private File file;
    private boolean flag = true;
    private BuilderTask builderTask;

    // CONSTRUCTOR

    public ReadingXML(File f) throws IOException {
        this.model = null;
        this.document = null;
        this.file = Files.copy(f.toPath(), Paths.get(PATH_XML)).toFile();
        this.flag = true;
        this.builderTask = null;
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
        final Element dataElement = (Element) this.document.getElementsByTagName("data").item(0);
        final Element nameElement = (Element) dataElement.getElementsByTagName("name").item(0);

        this.model = this.builderTask.createTask(nameElement.getTextContent());
        this.model.setCalendar(this.browseTime(dataElement));

        final Element tasksElement = (Element) this.document.getElementsByTagName("tasks").item(0);
        this.browseAllTask(tasksElement, null);
    }

    private void browseAllTask(Element element, ComplexTask complexTask) {
        NodeList tasks = element.getElementsByTagName("task");

        if (tasks.getLength() == 0) {
            return;
        }

        this.builderTask = new ConcreteBuilderTask();

        for (int i = 0; i < tasks.getLength(); i++) {
            Element task = (Element) tasks.item(i);

            // get description
            final Element descElement = (Element) task.getElementsByTagName("description").item(0);
            String desc = descElement.getTextContent();

            // get priority
            final Priority priority = this.getPriority(task.getAttributes().getNamedItem("mode").getTextContent());

            // is complex 
            boolean isComplexTask = task.getElementsByTagName("progress").getLength() == 0;
            
            int completion = -1;
            Double progress = -1.0;

            Task newTask = null;
            if (isComplexTask) {
                newTask = this.builderTask.createComplexTask();
                final Element subElement = (Element) task.getElementsByTagName("sub").item(0);
                this.browseAllTask(subElement, (ComplexTask) newTask);
            } else {
                final Element completionElement = (Element) task.getElementsByTagName("completion").item(0);
                completion = Integer.parseInt(completionElement.getTextContent());

                // set month
                final Element progressElement = (Element) task.getElementsByTagName("progress").item(0);
                progress = Double.parseDouble(progressElement.getTextContent());

                // is normal task
                boolean isNormal = progressElement.getAttributes().getNamedItem("mode")
                    .getTextContent().equals("NORMAL");
                
                if (isNormal) {
                    newTask = this.builderTask.createNormalTask();
                    ((NormalTask) newTask).setProgressStatus(progress);
                } else {
                    newTask = this.builderTask.createBooleanTask();
                    ((BooleanTask) newTask).setIsDone(progress == 100.0);
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(this.model.getCreationDate());
                ((BasicTask) newTask).setDefaultCalendar(calendar);
                ((BasicTask) newTask).setCompletionDate(completion);
            }

            newTask.setDescription(desc);
            newTask.setPriority(priority);

            if (complexTask == null) {
                this.model.addTaskInList(newTask);
            } else {
                complexTask.addTask(newTask);
            }
        }
            
    }

    private Calendar browseTime(Element element) {
        Calendar calendar = new GregorianCalendar();
        final Element timeElement = (Element) element.getElementsByTagName("time").item(0);

        // set year
        final Element yearElement = (Element) timeElement.getElementsByTagName("year").item(0);
        int year = Integer.parseInt(yearElement.getTextContent());
        calendar.set(Calendar.YEAR, year);

        // set month
        final Element monthElement = (Element) timeElement.getElementsByTagName("month").item(0);
        int month = Integer.parseInt(monthElement.getTextContent());
        calendar.set(Calendar.MONTH, month);

        // set day
        final Element dayElement = (Element) timeElement.getElementsByTagName("day").item(0);
        int day = Integer.parseInt(dayElement.getTextContent());
        calendar.set(Calendar.DAY_OF_MONTH, day);

        // set hour
        final Element hourElement = (Element) timeElement.getElementsByTagName("hour").item(0);
        int hour = Integer.parseInt(hourElement.getTextContent());
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        // set minute
        final Element minuteElement = (Element) timeElement.getElementsByTagName("minute").item(0);
        int minute = Integer.parseInt(minuteElement.getTextContent());
        calendar.set(Calendar.MINUTE, minute);

        // set second
        final Element secondElement = (Element) timeElement.getElementsByTagName("second").item(0);
        int second = Integer.parseInt(secondElement.getTextContent());
        calendar.set(Calendar.SECOND, second);

        return calendar;
    }

    private Priority getPriority(String p) {
        for (Priority priority : Priority.values()) {
            if (priority.name().equals(p)) {
                return priority;
            }
        }
        return null;
    }
}
