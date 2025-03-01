package studentautomation;

import java.util.ArrayList;
// Todo (MK): Kullanılmayan import bırakmayalım (bir class'ta değişiklik yaptıktan sonra format çekmek faydalıdır)
import java.util.NoSuchElementException;

/* Todo (MK): Bu branch'i ana branch'e merge edebilirsin. Bundan sonra da farklı her iş için yeni
    branch açarak ilerleyelim, bu branch'te çok iş yapmışız. Yeni bir iş yapılacak diyelim:
    Önce ana branch'e geçmeli ve yeni branch'i onun üzerinden açmalısın. Ayrıca branch isimleri
    yapılan işi anlatır nitelikte olmalı (örnegin maven'a gecilecekse maven-usage falan diyebilirsin)
 */
public class Main {


    public static void main(String[] args) throws Exception {
        int control = 0;
        int choice;
        DatabaseOperations db = new DatabaseOperations();
        ListOperations studentList = new ListOperations();

        while (control != 1) {

            Student.showMenu();

            try {
                choice = Student.inputChoice();
                control = Student.selectMenuChoice(db, choice);
            } catch (NoSuchElementException e) {
                // Todo (MK): Buna neden bakmadın:)
                // Todo (MK): inputChoice metodunun içinde zaten try-catch var gibi, buraya girdiği oluyor mu? olmuyorsa kaldıralım.
                System.out.println("Line cannot be found.");
                throw new Exception("Hata var");
            }
        }
    }
}
