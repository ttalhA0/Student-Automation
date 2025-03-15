package studentautomation;

import lombok.Getter;

@Getter
public enum LessonType {
    FIZIK("FİZİK", "PHYS121"),
    MATEMATIK("MATEMATİK", "MATH101"),
    TURKCE("TÜRKÇE", "TUR101");

    // Todo (MK): neden field seviyesinde yazdık?
    private String title;
    private String classID;

    LessonType(String title, String classID) {
        this.title = title;
        this.classID = classID;
    }
}


