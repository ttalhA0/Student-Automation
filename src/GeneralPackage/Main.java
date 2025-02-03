package GeneralPackage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        int control = 0;
        int choice = 0;
        //Student[] students = new Student[10]; // Todo: Artık List kullansak daha iyi olmaz mı?
        ArrayList<Student> students = new ArrayList<Student>();
        // Todo: Ayrıca test kullanıcıları eklemek ayrı bir metoda alınamaz mı?
        Student.addTestStudent(students);

        //int firstStudentNum = Student.getStudentNum();
        //Scanner scan = new Scanner(System.in);

        while (control != 1) {

            // Todo: Menü göstermek ayrı bir metoda alınamaz mı?
            Student.showMenu();

            try {

                // Todo: Kullanıcıdan girdi almak ayrı bir metoda alınamaz mı?
                choice = Student.inputChoice();

                // Todo: sanki bu kısım da bir metoda ayrılabilir gibi, ne dersin? okumayı kolaylaştırmaz mı?
                control = Student.selectMenuChoice(students, choice);

            } catch (NoSuchElementException e) {
                System.out.println("Line cannot be found.");
                throw new Exception("Hata var");
            }
        }
    }


}
