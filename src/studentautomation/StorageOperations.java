package studentautomation;

import java.util.ArrayList;

public interface StorageOperations {
    public void addStudent(Student student);
    public Student getStudentByID(int studentID);
    public ArrayList<Student> getStudents();
    public ArrayList<Student> getStudentsByClassID(String classID);
    public int getTotalNumberOfStudents();
    public double getAverageGpaOfStudents();
    public Student getMinGpaStudent();
    public Student getMaxGpaStudent();
}
