package studentautomation;

import java.util.ArrayList;

public class ListOperations implements StorageOperations{
    private ArrayList<Student> students;

    // Todo: (TT) Bu class'ı diğer database var diye kullanmadım sadece main'e ekledim.

    public ListOperations() {
        this.students = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getId() == studentID) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudentByName(String name) {
        ArrayList<Student> searchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(name)) {
               searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public ArrayList<Student> getStudentBySurname(String surname) {
        ArrayList<Student> searchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getSurname().equals(surname)) {
                searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public ArrayList<Student> getStudentByGpaRange(double min, double max) {
        ArrayList<Student> searchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() >= min && student.getGpa() <= max) {
                searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Student> getStudentsByClassID(String classID) {
        ArrayList<Student> searchedStudents = new ArrayList<>();
        for (Student student : students) {
            for (LessonType lesson : student.getLessons()) {
                if (lesson.getClassID().equals(classID)) {
                    searchedStudents.add(student);
                }
            }
        }
        return searchedStudents;
    }

    public int getTotalNumberOfStudents() {
        return Student.getStudentNum();
    }

    public double getAverageGpaOfStudents() {
        double avgGPA = 0;
        avgGPA = calculateAverageGPA();
        return avgGPA;
    }

    public Student getMinGpaStudent() {
        Student minGpaStudent = students.getFirst();
        for (Student student : students) {
            if (student.getGpa() <= minGpaStudent.getGpa()) {
                minGpaStudent = student;
            }
        }
        return minGpaStudent;
    }

    public Student getMaxGpaStudent() {
        Student maxGpaStudent = students.getFirst();
        for (Student student : students) {
            if (student.getGpa() >= maxGpaStudent.getGpa()) {
                maxGpaStudent = student;
            }
        }
        return maxGpaStudent;
    }

    public double calculateAverageGPA() {
        double totalGPA = 0;
        for (Student student : students) {
            totalGPA += student.getGpa();
        }
        return totalGPA / Student.getStudentNum();
    }
}
