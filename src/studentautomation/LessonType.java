package studentautomation;

public enum LessonType {
    FIZIK ("FİZİK"),
    MATEMATIK ("MATEMATİK"),
    TURKCE ("TÜRKÇE");

    private String title;

    LessonType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}


