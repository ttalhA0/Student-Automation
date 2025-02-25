package studentautomation;

public enum LessonType {
    FIZIK ("FİZİK", "PHYS121"),
    MATEMATIK ("MATEMATİK", "MATH101"),
    TURKCE ("TÜRKÇE", "TUR101");

    private String title;
    private String classID;

    LessonType(String title, String classID) {
        this.title = title;
        this.classID = classID;
    }

    public String getTitle() {
        return title;
    }

    public String getClassID() {
        return classID;
    }
}


