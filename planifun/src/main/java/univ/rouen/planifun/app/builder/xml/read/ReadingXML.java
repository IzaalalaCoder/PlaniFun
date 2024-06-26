package univ.rouen.planifun.app.builder.xml.read;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import univ.rouen.planifun.app.builder.ConcreteBuilderSetTask;
import univ.rouen.planifun.app.builder.xml.XMLElement;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

/**
 * Implements XMLParser
 */
public class ReadingXML implements XMLParser {

    // ATTRIBUTES

    private Document document;
    private final File file;
    private final ConcreteBuilderSetTask builderTask;

    // CONSTRUCTOR

    public ReadingXML(File f) throws IOException {
        this.document = null;
        this.file = Files.copy(f.toPath(), Paths.get(PATH_XML)).toFile();
        this.builderTask = new ConcreteBuilderSetTask();
    }

    // REQUESTS

    @Override
    public SetTask getSetTaskInFile() {
        return this.builderTask.getSetTask();
    }

    // COMMANDS

    @Override
    public void readXMLFile() throws IOException {
        try {
            this.openFile();
        } catch (ParserConfigurationException | SAXException e) {
            throw new AssertionError("Can not open");
        }
        
        this.browseFile();
        Files.delete(this.file.toPath());
    }

    // UTILS

    /**
     * openFile : open and validate current file by using XML parsing
     * @throws ParserConfigurationException : error on config parse
     * @throws SAXException : error on treatments file
     * @throws IOException : error on open file
     */
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

    /**
     * browseFile : read file while creating a tasks list
     */
    private void browseFile() {
        final Element dataElement = (Element) this.document.getElementsByTagName(
                XMLElement.DATA.getTagName()).item(0);
        final Element nameElement = (Element) dataElement.getElementsByTagName(
                XMLElement.NAME.getTagName()).item(0);


        this.builderTask.startSetTask();
        this.builderTask.setName(nameElement.getTextContent());
        this.builderTask.setCalendar(this.browseTime(dataElement));

        final Element tasksElement = (Element) this.document.getElementsByTagName(
                XMLElement.TASKS.getTagName()).item(0);
        this.browseAllTask(tasksElement, null, new ArrayList<>());
    }

    /**
     * browseAllTask : browse task list for add task in the model
     * @param element : current element being read
     * @param parentComplexTask : element parent
     * @param elementsVisited : elements list already visit
     */
    private void browseAllTask(Element element, ComplexTask parentComplexTask, List<Element> elementsVisited) {
        NodeList tasks = element.getElementsByTagName(XMLElement.TASK.getTagName());
    
        if (tasks.getLength() == 0) {
            return;
        }

        for (int i = 0; i < tasks.getLength(); i++) {
            Element taskElement = (Element) tasks.item(i);

            if (!elementsVisited.contains(taskElement)) {
                elementsVisited.add(taskElement);
                Element descElement = (Element) taskElement.getElementsByTagName(
                        XMLElement.DESCRIPTION.getTagName()
                ).item(0);
                String desc = descElement.getTextContent();
                Priority priority = getPriority(taskElement.getAttribute(XMLElement.PRIORITY.getTagName()));
                boolean isComplexTask = taskElement.getElementsByTagName(XMLElement.SUB.getTagName()).getLength() > 0;
        
                Calendar calendar = Calendar.getInstance();
                this.builderTask.setCalendar(calendar);

                Task newTask;
                if (isComplexTask) {
                    builderTask.createComplexTask(calendar);
                    this.builderTask.setDescription(desc);
                    this.builderTask.setPriority(priority);
                    ComplexTask subComplexTask = (ComplexTask) this.builderTask.getLastTask();
                    browseAllTask(taskElement, subComplexTask, elementsVisited);
                    newTask = subComplexTask;
                } else {
                    Element completionElement = (Element) taskElement.getElementsByTagName(
                            XMLElement.COMPLETION.getTagName()).item(0);
                    int completion = Integer.parseInt(completionElement.getTextContent());
                    Element progressElement = (Element) taskElement.getElementsByTagName(
                            XMLElement.PROGRESS.getTagName()).item(0);
                    double progress = Double.parseDouble(progressElement.getTextContent());
                    boolean isNormal = progressElement.getAttributes().getNamedItem(
                            XMLElement.MODE.getTagName()).getTextContent().equals("NORMAL");
        
                    if (isNormal) {
                        builderTask.createNormalTask(calendar);
                        builderTask.setProgress(progress);
                    } else {
                        builderTask.createBooleanTask(calendar);
                        builderTask.setIsDone(progress == 100.0);
                    }
                    this.builderTask.setDescription(desc);
                    this.builderTask.setPriority(priority);
                    this.builderTask.setCompletion(completion);
                    newTask = this.builderTask.getLastTask();
                }

                if (parentComplexTask != null) {
                    this.builderTask.addTaskInComplexTask(parentComplexTask, newTask);
                } else {
                    this.builderTask.addTaskInSetTask(newTask);
                }
            }
        }
    }

    /**
     * browseTime : generate calendar from read content
     * @param element : current element being read
     * @return Calendar
     */
    private Calendar browseTime(Element element) {
        Calendar calendar = new GregorianCalendar();
        final Element timeElement = (Element) element.getElementsByTagName(
                XMLElement.TIME.getTagName()).item(0);

        // set year
        final Element yearElement = (Element) timeElement.getElementsByTagName(
                XMLElement.YEAR.getTagName()).item(0);
        int year = Integer.parseInt(yearElement.getTextContent());
        calendar.set(Calendar.YEAR, year);

        // set month
        final Element monthElement = (Element) timeElement.getElementsByTagName(
                XMLElement.MONTH.getTagName()).item(0);
        int month = Integer.parseInt(monthElement.getTextContent());
        calendar.set(Calendar.MONTH, month);

        // set day
        final Element dayElement = (Element) timeElement.getElementsByTagName(
                XMLElement.DAY.getTagName()).item(0);
        int day = Integer.parseInt(dayElement.getTextContent());
        calendar.set(Calendar.DAY_OF_MONTH, day);

        // set hour
        final Element hourElement = (Element) timeElement.getElementsByTagName(
                XMLElement.HOUR.getTagName()).item(0);
        int hour = Integer.parseInt(hourElement.getTextContent());
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        // set minute
        final Element minuteElement = (Element) timeElement.getElementsByTagName(
                XMLElement.MINUTE.getTagName()).item(0);
        int minute = Integer.parseInt(minuteElement.getTextContent());
        calendar.set(Calendar.MINUTE, minute);

        // set second
        final Element secondElement = (Element) timeElement.getElementsByTagName(
                XMLElement.SECOND.getTagName()).item(0);
        int second = Integer.parseInt(secondElement.getTextContent());
        calendar.set(Calendar.SECOND, second);

        return calendar;
    }

    /**
     * getPriority : generate priority from string
     * @param p : represents priority
     * @return Priority
     */
    private Priority getPriority(String p) {
        for (Priority priority : Priority.values()) {
            if (priority.name().equals(p)) {
                return priority;
            }
        }
        return null;
    }
}