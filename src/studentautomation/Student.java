package studentautomation;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
public class Student implements Cloneable{
    private static int studentNum = 0;
    private int id;
    private String name;
    private String surname;
    private double gpa;
    @Setter
    private List<LessonType> lessons;

    public Student() {
        studentNum++;
        id = 100 + studentNum;
    }

    public Student(String name, String surname, int id, double gpa, List<LessonType> lessons) {
        this.name = name;
        this.surname = surname;
        this.gpa = gpa;
        studentNum++;
        this.id = id;
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return id == student.getId();
    }

    @Override
    public Student clone() {
        try {
            return (Student) super.clone();
        }catch(CloneNotSupportedException e) {
            System.out.println("Clone failed");
            return null;
        }
    }

    public static void createStudent(DatabaseOperations db) {
        Scanner scan = new Scanner(System.in);
        Scanner scanForLesson = new Scanner(System.in);
        scan.useLocale(Locale.US);
        Student newStudent = new Student();

        try {
            System.out.println("Enter the student name:");
            newStudent.setName(scan.nextLine());

            System.out.println("Enter the student surname: ");
            newStudent.setSurname(scan.nextLine());

            System.out.println("Enter the gpa:");
            newStudent.setGpa(scan.nextDouble());

            System.out.println("Enter the lesson");
            ArrayList<LessonType> lessonList = createLessonListForCreateStudent(scanForLesson.nextLine());
            newStudent.setLessons(lessonList);

            db.addStudent(newStudent);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            Student.deacreaseStudentNum();
        }
    }

    public static ArrayList<LessonType> createLessonListForCreateStudent(String lessonLine) {
        // Todo: (TT) Burada kullanıcı boşluk veya virgülle öğrencinin aldığı dersleri yazdığında onu LessonType tipli bir listeye eklemeye çalıştım.
        // Todo: (TT) Kullanıcıdan Türkçe karakter alarak işlem yapmak istediğim için LessonType enum'ında getTitle() metoduyla çalışmaya çalışıyorum.
        String regex = "[,\\.\\s]";
        String[] lessonsArray = lessonLine.split(regex);
        ArrayList<LessonType> lessonList = new ArrayList<LessonType>();

        for (String inputLesson : lessonsArray) {
            for (LessonType lessonType : LessonType.values()) {
                if (lessonType.getTitle().equals(inputLesson.toUpperCase())) {
                    lessonList.add(lessonType);
                }
            }
        }
        return lessonList;
    }

    public static void showAllStudents(DatabaseOperations db) {
        printShowStudentTitle();

        for (Student student : db.getStudents()) {
            printStudent(student);
        }
        addGap(3);
    }

    public static void showStudentList(ArrayList<Student> students) {
        printShowStudentTitle();
        for (Student student : students) {
            printStudent(student);
        }
    }

    public static void showStudent(Student student) {
        Student.printShowStudentTitle();
        printStudent(student);
    }

    public static void printStudent(Student student) {
        System.out.println(student.getName() + "   " + student.getSurname() + "   " + student.getId() + "   " + student.getGpa());
    }

    public static void printShowStudentTitle() {
        System.out.println("Name   Surname   ID   GPA");
    }

    public static void searchByGpaRange(DatabaseOperations db) {
        double maxGPA;
        double minGPA;

        System.out.println("Enter the max gpa: ");
        maxGPA = inputGpa();
        System.out.println("Enter the min gpa: ");
        minGPA = inputGpa();

        if (minGPA < 0 || maxGPA > 4) {
            System.out.println("The gpa cannot be less than 0 and greater than 4. Please try again.");
        } else if (maxGPA < minGPA) {
            System.out.println("The max gpa cannot be less than min gpa. Please try again.");
        } else {
            printShowStudentTitle();
            for (Student student : db.getStudents("GPA >= " + minGPA + " AND GPA <= " + maxGPA)) {
                printStudent(student);
            }
        }
    }

    public static double inputGpa() {
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        double gpa;
        try {
            gpa = scan.nextDouble();
            return gpa;
        }
        catch (Exception e) {
            System.out.println("Enter a double number. Please try again.");
            return inputGpa();
        }
    }

    public static void showMenu() {
        System.out.println("1) Add a student.");
        System.out.println("2) Search a student.");
        System.out.println("3) Show all students.");
        System.out.println("4) Search by gpa range.");
        System.out.println("5) Show statistics");
        System.out.println("6) Exit the program.");
        addGap(3);
    }

    public static int inputChoice() {
        System.out.println("Enter your choice: ");
        Scanner scan  = new Scanner(System.in);

        if (scan.hasNextInt()) {
            int input = scan.nextInt();
            return input;
        } else {
            System.out.println("Geçersiz giriş! Lütfen bir tamsayı girin.");
            scan.nextLine();  // Geçersiz girişi atlayın.
            return inputChoice();
        }
    }

    public static int selectMenuChoice(DatabaseOperations db, int choice) {
        switch (choice) {
            case 1:
                createStudent(db);
                break;
            case 2:
                int searchingChoice = inputSearchingChoice();
                selectSearchingChoice(db, searchingChoice);
                break;
            case 3:
                showAllStudents(db);
                break;
            case 4:
                searchByGpaRange(db);
                break;
            case 5:
                int statisticsChoice = inputStatisticsChoice();
                selectStatisticsChoice(db,statisticsChoice);
                break;
            case 6:
                return  1;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        return 0;
    }

    public static int inputSearchingChoice() {
        System.out.println("1) By name ");
        System.out.println("2) By surname ");
        System.out.println("3) By id ");
        System.out.println("4) By lesson ");

        return inputChoice();
    }

    public static int inputStatisticsChoice() {
        System.out.println("1) Total number of students ");
        System.out.println("2) Average gpa of all students ");
        System.out.println("3) The student has max gpa");
        System.out.println("4) The student has min gpa");

        return inputChoice();
    }
    public static void selectSearchingChoice(DatabaseOperations db,int searchChoice) {
        ArrayList<Student> searchedStudents = new ArrayList<Student>();
        switch (searchChoice) {
            case 1:
                searchedStudents = searchingByName(db);
                showStudentList(searchedStudents);
                break;
            case 2:
                searchedStudents = searchingBySurname(db);
                showStudentList(searchedStudents);
                break;
            case 3:
                Student student = searchingById(db);
                showStudent(student);
                break;
            case 4:
                searchedStudents = searchingByLesson(db);
                showStudentList(searchedStudents);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        addGap(2);
    }

    public static void selectStatisticsChoice(DatabaseOperations db, int staticticsChoice) {
        Student student;
        switch (staticticsChoice) {
            case 1:
                printTotalStudentNumber(db);
                break;
            case 2:
                printAverageGPA(db);
                break;
            case 3:
                student = findStudentHasMaxGPA(db);
                showStudent(student);
                break;
            case 4:
                student = findStudentHasMinGPA(db);
                showStudent(student);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static ArrayList<Student> searchingByName(DatabaseOperations db) {
        Scanner scan = new Scanner(System.in);
        String dbCondition;
        ArrayList<Student> searchedStudents = new ArrayList<Student>();

        System.out.println("Enter the student name: ");
        String searchName = scan.next();
        searchName = searchName.toUpperCase();
        dbCondition = "Firstname='" + searchName + "'";
        addGap(2);

        searchedStudents = db.getStudents(dbCondition);
        return searchedStudents;
    }

    public static ArrayList<Student> searchingBySurname(DatabaseOperations db) {
        Scanner scan = new Scanner(System.in);
        String dbCondition;
        ArrayList<Student> searchedStudents = new ArrayList<>();

        System.out.println("Enter the student surname: ");
        String searchSurname = scan.next();
        searchSurname = searchSurname.toUpperCase();
        dbCondition = "Surname='" + searchSurname + "'";
        addGap(2);
        
        searchedStudents = db.getStudents(dbCondition);
        return searchedStudents;
    }

    public static Student searchingById(DatabaseOperations db) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the student ID: ");
        try {
            int searchID = scan.nextInt();
            return db.getStudentByID(searchID);
        } catch (Exception e) {
            System.out.println("Enter a decimal number. Please try again.");
            return null;
        }
    }

    public static ArrayList<Student> searchingByLesson(DatabaseOperations db) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> searchedStudents = new ArrayList<Student>();
        String classID;

        System.out.println("Enter the lesson: ");
        String searchLesson = scan.next();
        // Todo: (TT) Şimdilik sadece tek ders için arama yapabiliyorum ama daha sonra atıyorum hem fizik hem matematik alan öğrencileri yazdırmayı deneyeceğim.
        searchLesson = searchLesson.toUpperCase();
        addGap(2);

        for (LessonType lessonType : LessonType.values()) {
            if (searchLesson.equals(lessonType.getTitle())) {
                searchedStudents = db.getStudentsByClassID(lessonType.getClassID());
            }
        }
        return searchedStudents;
    }

    public static void printTotalStudentNumber(DatabaseOperations db) {
        System.out.println("Total number of students: " + db.getTotalNumberOfStudents());
        addGap(1);
    }

    public static void printAverageGPA(DatabaseOperations db) {
        System.out.printf("The average gpa of all students: %.2f", db.getAverageGpaOfStudents());
        addGap(1);
    }

    public static Student findStudentHasMaxGPA(DatabaseOperations db) {
        return db.getMaxGpaStudent();
    }

    public static Student findStudentHasMinGPA(DatabaseOperations db) {
        return db.getMinGpaStudent();
    }

    public static void addGap(int numberOfGaps) {
        for (int i = 0; i < numberOfGaps; i++) {
            System.out.println("\n");
        }
    }

    public static void deacreaseStudentNum() {
        studentNum--;
    }

    public void setName(String newName) {
        name = newName.toUpperCase();
    }

    public void setSurname(String newSurname) {
        surname = newSurname.toUpperCase();
    }

    public void setGpa(double newGpa) {
        if (newGpa >= 0 && newGpa <= 4) {
            gpa = newGpa;
        }
        else {
            System.out.println("Invalid GPA");
        }
    }

    public static int getStudentNum() {
        return studentNum;
    }

}

