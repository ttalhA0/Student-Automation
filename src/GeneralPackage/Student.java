package GeneralPackage;

public class Student {

    private static int studentNum = 0;
    private int id;
    String name; // Todo: bunlar neden private değil
    String surname;
    double gpa;

    public Student() {

        studentNum++; // Todo: yeni bir nesne oluşturduğumuzda burada artırıyoruz, ayrıca main metodunda da switch içerisinde artırıyoruz gibi, sayılar doğru artıyor mu?
        id = 100 + studentNum;
    }

    public Student(String name, String surname, double gpa) {
        this.name = name;
        this.surname = surname;
        this.gpa = gpa;
        studentNum++;
        id = 100 + studentNum;
    }

    public static int getStudentNum() {
        return studentNum;
    }

    public static void deacreaseStudentNum() {
        studentNum--;
    }

    public int getId() {
        return id;
    }


}
