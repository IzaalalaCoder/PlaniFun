package univ.rouen.planifun.app.builder.xml;

/**
 * Represent all elements found in XML file.
 */
public enum XMLElement {

    // VALUES

    TASK("task"),
    TASKS("tasks"),
    TIME("time"),
    YEAR("year"),
    MONTH ("month"),
    DAY("day"),
    HOUR("hour"),
    MINUTE("minute"),
    SECOND("second"),
    PROGRESS("progress"),
    COMPLETION("completion"),
    SUB("sub"),
    DESCRIPTION("description"),
    NAME("name"),
    DATA("data"),
    PRIORITY("priority"),
    MODE("mode");

    // ATTRIBUTES

    private final String tagName;

    // CONSTRUCTORS
 
    XMLElement(String tagName) {
        this.tagName = tagName;
    }

    // REQUESTS

    /**
     * getTagName : return tag name
     * @return String
     */
    public String getTagName() {
        return this.tagName;
    }
}