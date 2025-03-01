package studentautomation;

import java.util.ArrayList;

public interface StorageOperations {
    // Todo (MK): public erişim belirtecini IDE neden silik gösteriyor olabilir, üstüne gelince de redundant diyor, neden?
    public void addStudent(Student student);
    public Student getStudentByID(int studentID);
    public ArrayList<Student> getStudents();
    public ArrayList<Student> getStudentsByClassID(String classID); // Todo (MK): isimlendirme kurallarımıza göre şöyle olması gerekmez mi: classID -> classId
    public int getTotalNumberOfStudents();
    public double getAverageGpaOfStudents();
    public Student getMinGpaStudent();
    public Student getMaxGpaStudent();
}
