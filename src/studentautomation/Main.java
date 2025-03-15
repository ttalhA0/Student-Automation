package studentautomation;

// Todo (MK): Kullanılmayan import bırakmayalım (bir class'ta değişiklik yaptıktan sonra format çekmek faydalıdır)


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
            choice = Student.inputChoice();
            control = Student.selectMenuChoice(db, choice);
        }
    }
}
