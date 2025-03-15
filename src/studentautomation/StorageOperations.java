package studentautomation;

import java.util.ArrayList;

public interface StorageOperations {
    // Todo (MK): public erişim belirtecini IDE neden silik gösteriyor olabilir, üstüne gelince de redundant diyor, neden?
    void addStudent(Student student);

    Student getStudentByID(int studentID);

    ArrayList<Student> getAllStudents();

    ArrayList<Student> getStudentsByName(String studentName);

    ArrayList<Student> getStudentsBySurname(String surname);

    ArrayList<Student> getStudentsByGpaRange(double minGpa, double maxGpa);

    ArrayList<Student> getStudentsByClassId(String classID); // Todo (MK): isimlendirme kurallarımıza göre şöyle olması gerekmez mi: classID -> classId

    int getTotalNumberOfStudents();

    double getAverageGpaOfStudents();

    Student getMinGpaStudent();

    Student getMaxGpaStudent();
}
