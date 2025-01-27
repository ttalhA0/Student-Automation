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
        int firstStudentNum = Student.getStudentNum();
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
                        students[firstStudentNum] = StudentOperations.addStudent();
                        firstStudentNum++;
                        break;
                    case 2:
                        StudentOperations.searchStudent(students);
                        break;

                    case 3:
                        StudentOperations.showStudents(students, Student.getStudentNum());
                        break;

                    case 4:
                        StudentOperations.searchByGpaRange(students, Student.getStudentNum());
                        break;

                    case 5:
                        control = 1;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;

                }


            } catch (NoSuchElementException e) {
                System.out.println("Line cannot be found.");
                throw new Exception("Hata var");
            }
        }
    }


}
