package studentautomation;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {


    public static void main(String[] args) throws Exception {
        int control = 0;
        int choice;
        ArrayList<Student> students = new ArrayList<>();
        Student.addTestStudents(students);

        while (control != 1) {

            Student.showMenu();

            try {
                choice = Student.inputChoice();
                control = Student.selectMenuChoice(students, choice);
            } catch (NoSuchElementException e) {
                // Todo (MK): inputChoice metodunun içinde zaten try-catch var gibi, buraya girdiği oluyor mu? olmuyorsa kaldıralım.
                System.out.println("Line cannot be found.");
                throw new Exception("Hata var");
            }
        }
    }


}
