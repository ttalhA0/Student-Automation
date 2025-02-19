package studentautomation;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {


    public static void main(String[] args) throws Exception {
        int control = 0;
        int choice;
        DatabaseOperations db = new DatabaseOperations();
        while (control != 1) {

            Student.showMenu();

            try {
                choice = Student.inputChoice();
                control = Student.selectMenuChoice(db, choice);
            } catch (NoSuchElementException e) {
                // Todo (MK): inputChoice metodunun içinde zaten try-catch var gibi, buraya girdiği oluyor mu? olmuyorsa kaldıralım.
                System.out.println("Line cannot be found.");
                throw new Exception("Hata var");
            }
        }
    }
}
