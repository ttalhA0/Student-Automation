package GeneralPackage;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Student {

    private static int studentNum = 0;
    private int id;
    private String name;
    private String surname;
    private double gpa;

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

    public static void createStudent(ArrayList<Student> students) {
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        Student newStudent = new Student();

        try {
            System.out.println("Enter the student name:");
            newStudent.setName(scan.nextLine());

            System.out.println("Enter the student surname: ");
            newStudent.setSurname(scan.nextLine());

            System.out.println("Enter the gpa:");
            newStudent.setGpa(scan.nextDouble());

            Student.addStudent(students, newStudent);

        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            Student.deacreaseStudentNum();
        }
    }

    public static void addStudent(ArrayList<Student> students, Student newStudent) {
        students.add(newStudent);
        System.out.println("Student is created and added successfully.\n\n\n");
    }

    public static void showAllStudents(ArrayList<Student> students) {
        printShowStudentTitle();

        for (Student student : students) {
            showStudent(student);
        }
        System.out.println("\n\n\n"); // Todo (MK): addGap gibi bir metoda taşınabilir (her yerde kullanılan bir yapı bu. Yarın desem ki 3 değil 2 satır boşluk olsun, sadece bir yerde değiştirmen yeterli olur bu sayede)
    }

    public static void showStudent(Student student) {
        // Todo (MK): printShowStudentTitle metodu buraya taşınabilir, yukarıdaki gibi (sonuçta aynı işin ayrılmaz bir parçası)
        System.out.println(student.getName() + "   " + student.getSurname() + "   " + student.getId() + "   " + student.getGpa());
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
        students.add(new Student("ALİ", "MUTLU", 3.50d));
        students.add(new Student("VELİ", "YILDIZ", 3.12d));
        students.add(new Student("SERCAN", "ŞEKER", 2.46d));
    }

    public static void showMenu() {
        System.out.println("1) Add a student.");
        System.out.println("2) Search a student.");
        System.out.println("3) Show student list.");
        System.out.println("4) Search by gpa range.");
        System.out.println("5) Exit the program.");
        System.out.println("\n\n\n");
    }

    public static int inputChoice() {
        System.out.println("Enter your choice: ");
        Scanner scan  = new Scanner(System.in);

        try {
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                return input;
            } else {
                System.out.println("Geçersiz giriş! Lütfen bir tamsayı girin.");
                scan.nextLine();  // Geçersiz girişi atlayın.
                return inputChoice();
            }
        } catch (Exception e) {
            // Todo (MK): Buraya girdiği oluyor mu? Eğer girmiyorsa kaldıralım, giriyorsa da yukarıyla aynı olduğundan kod tekrarı var (metoda çekelim)
            System.out.println("Geçersiz giriş! Lütfen tam sayı girin.");
            scan.nextLine();
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

        return inputChoice();
    }
    // Todo: searchStudent() metodunda switch case kısmında kaldım.
    // Todo (MK): yukarıdaki todoyu sen koymuşsun galiba, anlaşılması için Todo (TT): mesaj şeklinde gidelim
    public static void selectSearchingChoice(ArrayList<Student> students,int searchChoice) {
        int studentFind;
        switch (searchChoice) {
            case 1:
                studentFind = searchingByName(students);
                break;
            case 2:
                studentFind = searchingBySurname(students);
                break;
            case 3:
                studentFind = searchingById(students);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                studentFind = 1;
                break;
        }

        if (studentFind == 0) {
            System.out.println("The student cannot be found. Please try again.");
        }
        System.out.println("\n\n\n");
    }

    public static void printShowStudentTitle() {
        System.out.println("Name   Surname   ID   GPA");
    }

    // Todo (MK): search metotları Student dönsün
    public static int searchingByName(ArrayList<Student> students) {
        int studentFind = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the student name: ");
        String searchName = scan.next();
        searchName = searchName.toUpperCase();
        System.out.println("\n\n");

        Student.printShowStudentTitle();

        // Todo (MK): bulunan öğrenciyi ekrana bastırmak farklı bir iş
        for (Student student : students) {
            if (searchName.equals(student.name)) {
                Student.showStudent(student);
                studentFind = 1;
            }
        }
        return studentFind;
    }

    public static int searchingBySurname(ArrayList<Student> students) {
        int studentFind = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the student surname: ");
        String searchSurname = scan.next();
        searchSurname = searchSurname.toUpperCase();
        System.out.println("\n\n");

        Student.printShowStudentTitle();

        for (Student student : students) {
            if (searchSurname.equals(student.surname)) {
                Student.showStudent(student);
                studentFind = 1;
            }
        }
        return studentFind;
    }

    public static int searchingById(ArrayList<Student> students) {
        int studentFind = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the student ID: ");
        try {
            int searchID = scan.nextInt();

            System.out.println("\n");

            Student.printShowStudentTitle();
            for (Student student : students) {
                if (searchID == student.getId()) {
                    Student.showStudent(student);
                    studentFind = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Enter a decimal number. Please try again.");
            studentFind = 1;
        }
        return studentFind;
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


}

