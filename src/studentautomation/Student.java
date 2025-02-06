package studentautomation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Student {

    private static int studentNum = 0;
    private int id;
    private String name;
    private String surname;
    private double gpa;
    private List<LessonType> lessons;

    public Student() {
        studentNum++;
        id = 100 + studentNum;
    }

    public Student(String name, String surname, double gpa, List<LessonType> lessons) {
        this.name = name;
        this.surname = surname;
        this.gpa = gpa;
        studentNum++;
        id = 100 + studentNum;
        this.lessons = lessons;
    }

    public static void createStudent(ArrayList<Student> students) {
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

            Student.addStudent(students, newStudent);

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

    public static void addStudent(ArrayList<Student> students, Student newStudent) {
        students.add(newStudent);
        System.out.println("Student is created and added successfully.");
        addGap(3);
    }

    public static void showAllStudents(ArrayList<Student> students) {
        printShowStudentTitle();

        for (Student student : students) {
            printStudent(student);
        }
        addGap(3); // Todo (MK): addGap gibi bir metoda taşınabilir (her yerde kullanılan bir yapı bu. Yarın desem ki 3 değil 2 satır boşluk olsun, sadece bir yerde değiştirmen yeterli olur bu sayede)
    }

    public static void showStudentList(ArrayList<Student> students) {
        printShowStudentTitle();
        for (Student student : students) {
            printStudent(student);
        }
    }

    public static void showStudent(Student student) {
        // Todo (MK): printShowStudentTitle metodu buraya taşınabilir, yukarıdaki gibi (sonuçta aynı işin ayrılmaz bir parçası)
        Student.printShowStudentTitle();
        printStudent(student);

    }

    public static void printStudent(Student student) {
        System.out.println(student.getName() + "   " + student.getSurname() + "   " + student.getId() + "   " + student.getGpa());
    }

    public static void printShowStudentTitle() {
        System.out.println("Name   Surname   ID   GPA");
    }

    public static void searchByGpaRange(ArrayList<Student> students) {
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
            for (Student student : students) {
                if (minGPA <= student.getGpa() && student.getGpa() <= maxGPA) {
                    showStudent(student);
                }
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

    public static void addTestStudents(ArrayList<Student> students) {
        students.add(new Student("ALİ", "MUTLU", 3.50d, List.of(LessonType.FIZIK, LessonType.TURKCE)));
        students.add(new Student("VELİ", "YILDIZ", 3.12d, List.of(LessonType.MATEMATIK, LessonType.TURKCE)));
        students.add(new Student("SERCAN", "ŞEKER", 2.46d, List.of(LessonType.FIZIK, LessonType.MATEMATIK)));
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

    public static int selectMenuChoice(ArrayList<Student> students, int choice) {
        switch (choice) {
            case 1:
                createStudent(students);
                break;
            case 2:
                int searchingChoice = inputSearchingChoice();
                selectSearchingChoice(students,searchingChoice);
                break;
            case 3:
                showAllStudents(students);
                break;
            case 4:
                searchByGpaRange(students);
                break;
            case 5:
                int statistisChoice = inputStatisticsChoice();
                selectStatisticsChoice(students,statistisChoice);
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
    public static void selectSearchingChoice(ArrayList<Student> students,int searchChoice) {
        ArrayList<Student> searchedStudents = new ArrayList<Student>();
        switch (searchChoice) {
            case 1:
                searchedStudents = searchingByName(students);
                showStudentList(searchedStudents);
                break;
            case 2:
                searchedStudents = searchingBySurname(students);
                showStudentList(searchedStudents);
                break;
            case 3:
                searchedStudents = searchingById(students);
                showStudentList(searchedStudents);
                break;
            case 4:
                searchedStudents = searchingByLesson(students);
                showStudentList(searchedStudents);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        if (searchedStudents.isEmpty()) {
            System.out.println("The student cannot be found. Please try again.");
        }
        addGap(3);
    }

    public static void selectStatisticsChoice(ArrayList<Student> students, int staticticsChoice) {
        Student student;
        switch (staticticsChoice) {
            case 1:
                printTotalStudentNumber();
                break;
            case 2:
                printAverageGPA(students);
                break;
            case 3:
                student = findStudentHasMaxGPA(students);
                showStudent(student);
                break;
            case 4:
                student = findStudentHasMinGPA(students);
                showStudent(student);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    // Todo (MK): search metotları Student dönsün
    public static ArrayList<Student> searchingByName(ArrayList<Student> students) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> searchedStudents = new ArrayList<Student>();

        System.out.println("Enter the student name: ");
        String searchName = scan.next();
        searchName = searchName.toUpperCase();
        addGap(2);

        for (Student student : students) {
            if (searchName.equals(student.getName())) {
                searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public static ArrayList<Student> searchingBySurname(ArrayList<Student> students) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> searchedStudents = new ArrayList<>();

        System.out.println("Enter the student surname: ");
        String searchSurname = scan.next();
        searchSurname = searchSurname.toUpperCase();
        addGap(2);
        
        for (Student student : students) {
            if (searchSurname.equals(student.getSurname())) {
                searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public static ArrayList<Student> searchingById(ArrayList<Student> students) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> searchedStudents = new ArrayList<>();
        System.out.println("Enter the student ID: ");
        try {
            int searchID = scan.nextInt();
            Student.printShowStudentTitle();
            for (Student student : students) {
                if (searchID == student.getId()) {
                    searchedStudents.add(student);
                }
            }
        } catch (Exception e) {
            System.out.println("Enter a decimal number. Please try again.");
        }
        return searchedStudents;
    }

    public static ArrayList<Student> searchingByLesson(ArrayList<Student> students) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> searchedStudents = new ArrayList<Student>();

        System.out.println("Enter the lesson: ");
        String searchLesson = scan.next();
        // Todo: (TT) Şimdilik sadece tek ders için arama yapabiliyorum ama daha sonra atıyorum hem fizik hem matematik alan öğrencileri yazdırmayı deneyeceğim.
        searchLesson = searchLesson.toUpperCase();
        addGap(2);

        printShowStudentTitle();

        for (Student student : students) {
            // Todo: (TT) Burada kaldım ve enumların içine title olarak Türkçe karakterlerle dersleri yazıp ona göre arama yapmak istiyorum.
            for (LessonType lesson : student.getLessons()) {
                if (lesson.getTitle().equals(searchLesson)) {
                    searchedStudents.add(student);
                }
            }
        }
        return searchedStudents;
    }

    public static void printTotalStudentNumber() {
        System.out.println("Total number of students: " + getStudentNum());
        addGap(1);
    }

    public static void printAverageGPA(ArrayList<Student> students) {
        System.out.printf("The average gpa of all students: %.2f", calculateAverageGPA(students));
        addGap(1);
    }

    public static double calculateAverageGPA(ArrayList<Student> students) {
        double totalGPA = 0.0;
        for (Student student : students) {
            totalGPA += student.getGpa();
        }
        return totalGPA / getStudentNum();
    }

    public static Student findStudentHasMaxGPA(ArrayList<Student> students) {
        Student studentMaxGPA = students.getFirst();
        for (Student student : students) {
            if (student.getGpa() > studentMaxGPA.getGpa()) {
                studentMaxGPA = student;
            }
        }
        return studentMaxGPA;
    }

    public static Student findStudentHasMinGPA(ArrayList<Student> students) {
        Student studentMinGPA = students.getFirst();
        for (Student student : students) {
            if (student.getGpa() < studentMinGPA.getGpa()) {
                studentMinGPA = student;
            }
        }
        return studentMinGPA;
    }

    public static void addGap(int numberOfGaps) {
        for (int i = 0; i < numberOfGaps; i++) {
            System.out.println("\n");
        }
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

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName.toUpperCase();
    }

    public String  getSurname() {
        return surname;
    }

    public void setSurname(String newSurname) {
        surname = newSurname.toUpperCase();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double newGpa) {
        if (newGpa >= 0 && newGpa <= 4) {
            gpa = newGpa;
        }
        else {
            System.out.println("Invalid GPA");
        }
    }

    public void setLessons(ArrayList<LessonType> lessonList) {
        this.lessons = lessonList;
    }

    public List<LessonType> getLessons() {
        return lessons;
    }

}

