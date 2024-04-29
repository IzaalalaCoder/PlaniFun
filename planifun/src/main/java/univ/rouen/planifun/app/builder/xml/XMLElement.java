package univ.rouen.planifun.app.builder.xml;

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

    private String tagName;

    // CONSTRUCTORS
 
    private XMLElement(String tagName) {
        this.tagName = tagName;
    }

    // REQUESTS
 
    public String getTagName() {
        return this.tagName;
    }
}
