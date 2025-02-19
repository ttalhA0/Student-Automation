package studentautomation;

public enum LessonType {
    FIZIK ("FİZİK", "PHYS121"),
    MATEMATIK ("MATEMATİK", "MATH101"),
    TURKCE ("TÜRKÇE", "TUR101");

    private String title;
    private String classCode;

    LessonType(String title, String classCode) {
        this.title = title;
        this.classCode = classCode;
    }

    public String getTitle() {
        return title;
    }

    public String getClassID() {
        return classCode;
    }
}


