package GeneralPackage;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        int control = 0;
        int choice = 0;
        Student[] students = new Student[10];
        students[0] = new Student("Ali", "Mutlu", 3.50d);
        students[1] = new Student("Veli", "Yildiz", 3.12d);
        students[2] = new Student("Sercan", "Seker", 2.46d);
        int firstStudentNum = students[0].getStudentNum();
        Scanner scan = new Scanner(System.in);

        while (control != 1) {

            System.out.println("1) Add a student.");
            System.out.println("2) Search a student.");
            System.out.println("3) Show student list.");
            System.out.println("4) Search by gpa range.");
            System.out.println("5) Exit the program.");
            System.out.println("\n\n\n");
            System.out.println("Enter your choice: ");

            try {

                if (scan.hasNextInt()) {
                    choice = scan.nextInt();
                    System.out.println("Girilen sayı: " + choice);
                } else {
                    System.out.println("Geçersiz giriş! Lütfen bir tamsayı girin.");
                    scan.nextLine();  // Geçersiz girişi atlayın.
                }


                switch (choice) {

                    case 1:
                        students[firstStudentNum] = addStudent();
                        firstStudentNum++;
                        break;
                    case 2:
                        searchStudent(students);
                        break;

                    case 3:
                        showStudents(students[0].getStudentNum(), students);
                        break;

                    case 4:
                        searchByGpaRange(students, students[0].getStudentNum());
                        break;

                    case 5:
                        control = 1;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;

                }
                //scan.close();


            } catch (NoSuchElementException e) {
                System.out.println("Line cannot be found.");
                throw new Exception("Hata var");
            }
        }
    }

    private static Student addStudent() {
        //System.out.println();
        Scanner inputClass = new Scanner(System.in);
        //Scanner inputGPA = new Scanner(System.in);
        inputClass.useLocale(Locale.US);
        Student newStudent = new Student();

        try {
            System.out.println("Enter the student name:");
            newStudent.name = inputClass.nextLine();

            System.out.println("Enter the student surname: ");
            newStudent.surname = inputClass.nextLine();

            System.out.println("Enter the gpa:");
            newStudent.gpa = inputClass.nextDouble();

            inputClass.close();


            System.out.println("Student added successfully.");
            System.out.println("\n\n\n");
            return newStudent;
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            inputClass.close();
            // Todo:
            return null;
        }


    }

    private static void searchStudent(Student[] students) {
        Scanner inputSearch = new Scanner(System.in);
        int searchChoice;

        System.out.println("1) By name ");
        System.out.println("2) By surname ");
        System.out.println("3) By id ");

        //try {
        searchChoice = inputSearch.nextInt();


        switch (searchChoice) {

            case 1:
                Scanner inputName = new Scanner(System.in);
                System.out.println("Enter the student name: ");
                String searchName = inputName.nextLine();
                System.out.println("\n\n");

                System.out.println("Name   Surname   ID   GPA");
                for (int i = 0; i < students[0].getStudentNum(); i++) {
                    if (searchName.equals(students[i].name)) {
                        System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                    }
                }
                break;

            case 2:
                System.out.println("Enter the student surname: ");
                String searchSurname = inputSearch.nextLine();
                System.out.println("\n\n");

                System.out.println("Name   Surname   ID   GPA");
                for (int i = 0; i < students[0].getStudentNum(); i++) {
                    if (searchSurname.equals(students[i].surname)) {
                        System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                    }
                }
                break;

            case 3:
                System.out.println("Enter the student ID: ");
                int searchID = inputSearch.nextInt();
                System.out.println("\n\n");

                System.out.println("Name   Surname   ID   GPA");
                for (int i = 0; i < students[0].getStudentNum(); i++) {
                    if (searchID == students[i].getId()) {
                        System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                    }
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        System.out.println("\n\n\n");
        //}
        //catch () {
        //    System.out.println("Invalid input. Please enter an integer between 1 and 3.");
        //}
    }

    private static void showStudents(int studentNum, Student[] students) {
        System.out.println("Name   Surname   ID   GPA");

        for (int i = 0; i < studentNum; i++) {
            System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
        }

        System.out.println("\n\n\n");
    }

    private static void searchByGpaRange(Student[] students, int studentNum) {
        Scanner inputGpa = new Scanner(System.in);
        inputGpa.useLocale(Locale.US);
        double maxGPA;
        double minGPA;

        System.out.println("Enter the max gpa:");
        maxGPA = inputGpa.nextDouble();
        System.out.println("Enter the min gpa:");
        minGPA = inputGpa.nextDouble();

        System.out.println("Name   Surname   ID   GPA");
        for (int i = 0; i < studentNum; i++) {
            if (minGPA <= students[i].gpa && students[i].gpa <= maxGPA) {
                System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
            }
        }
    }


}
