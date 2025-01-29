package GeneralPackage;

import java.util.Locale;
import java.util.Scanner;

// Todo: Student'a dair metotlar neden ayrı bir class'ta ? Student içerisinde olması gerekmez mi?
public class StudentOperations {

    public static Student addStudent() {
        Scanner inputClass = new Scanner(System.in);
        inputClass.useLocale(Locale.US);
        Student newStudent = new Student();

        try {
            System.out.println("Enter the student name:");
            newStudent.name = inputClass.nextLine();
            // Todo: E burda name alanına doğrudan erişmişiz. Encapsulation nerde:)

            System.out.println("Enter the student surname: ");
            newStudent.surname = inputClass.nextLine();

            System.out.println("Enter the gpa:");
            newStudent.gpa = inputClass.nextDouble();


            System.out.println("Student added successfully.");
            System.out.println("\n\n\n");
            return newStudent;
            // Todo: Bu metot bir öğrenci oluşturuyor ama onu bir yere eklediği yok gibi, isimlendirme yanlış yönlendiriyor, ne dersin?
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            Student.deacreaseStudentNum();

            return addStudent();
        }
    }


    public static void searchStudent(Student[] students) {
        Scanner inputSearch = new Scanner(System.in);
        int searchChoice;
        int studentFind = 0;

        // Todo: Bu kısım ayrı bir metoda alınamaz mı? kodun okunurluğunu artırır gibi geldi bana
        System.out.println("1) By name ");
        System.out.println("2) By surname ");
        System.out.println("3) By id ");


        searchChoice = inputSearch.nextInt();


        // Todo: Aynı şekilde burası da ayrılabilir
        switch (searchChoice) {

            case 1:
                // Todo: sanki ayrı metot olması okunurluğu artıracak gibi?
                System.out.println("Enter the student name: ");
                String searchName = inputSearch.next();
                System.out.println("\n\n");

                System.out.println("Name   Surname   ID   GPA");
                for (int i = 0; i < Student.getStudentNum(); i++) {
                    if (searchName.equals(students[i].name)) {
                        System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                        studentFind = 1;
                    }
                }
                break;

            case 2:
                // Todo: sanki ayrı metot olması okunurluğu artıracak gibi?
                System.out.println("Enter the student surname: ");
                String searchSurname = inputSearch.next();
                System.out.println("\n\n");

                System.out.println("Name   Surname   ID   GPA");
                for (int i = 0; i < Student.getStudentNum(); i++) {
                    if (searchSurname.equals(students[i].surname)) {
                        System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                        studentFind = 1;
                    }
                }
                break;

            case 3:
                // Todo: sanki ayrı metot olması okunurluğu artıracak gibi?
                System.out.println("Enter the student ID: ");
                try {
                    int searchID = inputSearch.nextInt();

                    System.out.println("\n\n");

                    System.out.println("Name   Surname   ID   GPA");
                    for (int i = 0; i < Student.getStudentNum(); i++) {
                        if (searchID == students[i].getId()) {
                            /* Todo: Bu kısmın showStudents() metodundan ne farkı var
                            Şöyle bir şey nasıl olurdu: showStudent() diye bir metodumuz olsa
                            showStudents da bunu kullansa
                             */
                            System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                            studentFind = 1;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Enter a decimal number. Please try again.");
                    studentFind = 1;
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        if (studentFind == 0) {
            System.out.println("The student cannot be found. Please try again.");
        }
        System.out.println("\n\n\n");

    }


    public static void showStudents(Student[] students, int studentNumber) {
        System.out.println("Name   Surname   ID   GPA");

        for (int i = 0; i < studentNumber; i++) {
            System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
        }

        System.out.println("\n\n\n");
    }

    public static void searchByGpaRange(Student[] students, int studentNumber) {
        Scanner inputGpa = new Scanner(System.in);
        inputGpa.useLocale(Locale.US);
        double maxGPA;
        double minGPA;

        try {
            // Todo: girdi alma işi ayrı metot olmalı gibi, ne dersin
            System.out.println("Enter the max gpa:");
            maxGPA = inputGpa.nextDouble();
            System.out.println("Enter the min gpa:");
            minGPA = inputGpa.nextDouble();

            if (minGPA < 0 || maxGPA > 4) {
                System.out.println("The gpa cannot be less than 0 and greater than 4. Please try again.");
            } else if (maxGPA < minGPA) {
                System.out.println("The max gpa cannot be less than min gpa. Please try again.");
            } else {
                // Todo: Bu kısmın showStudents() metodundan ne farkı var
                System.out.println("Name   Surname   ID   GPA");
                for (int i = 0; i < studentNumber; i++) {
                    if (minGPA <= students[i].gpa && students[i].gpa <= maxGPA) {
                        System.out.println("" + students[i].name + "   " + students[i].surname + "   " + students[i].getId() + "   " + students[i].gpa);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Enter a decimal number. Please try again.");
        }
    }


}


