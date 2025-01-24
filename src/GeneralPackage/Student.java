package GeneralPackage;

public class Student {
    private static int studentNum = 0;
    private int id;
    String name;
    String surname;
    double gpa;

    public Student() {

        studentNum++;
        id = 100 + studentNum;
    }

    public Student(String name, String surname, double gpa) {
        this.name = name;
        this.surname = surname;
        this.gpa = gpa;
        studentNum++;
        id = 100 + studentNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public static void deacreaseStudentNum() {
        studentNum--;
    }

    public int getId() {
        return id;
    }


}
