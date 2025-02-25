package studentautomation;

import lombok.Getter;

public enum LessonType {
    FIZIK ("FİZİK", "PHYS121"),
    MATEMATIK ("MATEMATİK", "MATH101"),
    TURKCE ("TÜRKÇE", "TUR101");

    @Getter
    private String title;
    @Getter
    private String classID;

    LessonType(String title, String classID) {
        this.title = title;
        this.classID = classID;
    }
}


