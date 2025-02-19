package studentautomation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    private static Connection connection;
    private String url = "jdbc:mysql://localhost:3306/schooldatabase";
    private String user = "root";
    private String password = "sifre";

    public DatabaseOperations() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        }
        catch (SQLException e) {
            System.out.println("Database connection failed");
        }
    }

    public void addStudentToDatabase(Student student) {
        int studentID;
        studentID = addToStudentTable(student.getName(), student.getSurname(), student.getGpa());
        if (studentID != -1) {
            addToStudent_ClassTable(studentID, student.getLessons());
        }
        else {
            System.out.println("Student not found");
        }
    }

    public int addToStudentTable(String name, String surname, Double gpa) {
        String addingQuery = "INSERT INTO student (FirstName, Surname, GPA) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addingQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setDouble(3, gpa);
            preparedStatement.executeUpdate();
            try {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    System.out.println("Student ID cannot be found");
                    return -1;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void addToStudent_ClassTable(int studentID, List<LessonType> lessons) {
        String addingQuery = "INSERT INTO student_class (studentID, classID) VALUES (?,?)";
        try {
            for (LessonType lesson : lessons) {
                PreparedStatement preparedStatement = connection.prepareStatement(addingQuery);
                preparedStatement.setInt(1, studentID);
                preparedStatement.setString(2, lesson.getClassID());
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getOneStudentByID(int studentID) {
        String searchQuery = "SELECT * FROM student WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return convertResultSetToStudent(resultSet);
            }
            else {
                System.out.println("The student could not be found");
                return null;
            }
        }
        catch (SQLException e) {
            System.out.println("The student could not be found");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Student> getStudentsFromDatabase() {
        ArrayList<Student> searchedStudents = new ArrayList<>();
        String searchQuery = "SELECT * FROM student ";
        try {
            ResultSet resultSet = statementOperation(searchQuery);
            while (resultSet.next()) {
                searchedStudents.add(convertResultSetToStudent(resultSet));
            }
            return searchedStudents;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Todo:(TT) Yukarıdaki metot bütün öğrencileri çeviriyor, aşağıdaki metot bir koşulla öğrenci çevirirken kullanılıyor
    // Todo:(TT) Yukarıdaki metodu overload ettim.
    public ArrayList<Student> getStudentsFromDatabase(String condition) {
        ArrayList<Student> searchedStudents = new ArrayList<>();
        String searchQuery = "SELECT * FROM student WHERE " + condition;
        try {
            ResultSet resultSet = statementOperation(searchQuery);
            while (resultSet.next()) {
                searchedStudents.add(convertResultSetToStudent(resultSet));
            }
            return searchedStudents;
        }
        catch (SQLException e) {
            System.out.println("The student could not be found");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<LessonType> searchLessonsForAStudent(int studentID) {
        ArrayList<LessonType> lessons = new ArrayList<LessonType>();
        String searchQuery = "SELECT * FROM student_class WHERE StudentID = ?";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(searchQuery);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String classID = resultSet.getString("ClassID");
                for (LessonType lesson : LessonType.values()) {
                    if (lesson.getClassID().equals(classID)) {
                        lessons.add(lesson);
                    }
                }
            }
            return lessons;
        }
        catch (SQLException e) {
            System.out.println("Student ID cannot be found");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Student> getStudentsByClassID(String classID) {
        ArrayList<Student> students = new ArrayList<>();
        String searchQuery = "SELECT student.ID, student.FirstName, student.Surname, student.GPA " +
                             "FROM student " +
                             "INNER JOIN student_class ON student.ID = student_class.StudentID " +
                             "WHERE student_class.ClassID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, classID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(convertResultSetToStudent(resultSet));
            }
            return students;
        }
        catch (SQLException e) {
            System.out.println("Student in this class could not be found");
            e.printStackTrace();
            return null;
        }
    }

    public Student convertResultSetToStudent(ResultSet resultSet) {
        try {
            String name = resultSet.getString("FirstName");
            String surname = resultSet.getString("Surname");
            double gpa = resultSet.getDouble("GPA");
            int id = resultSet.getInt("ID");
            ArrayList<LessonType> lessons = searchLessonsForAStudent(id);
            return new Student(name, surname, id, gpa, lessons);
        }
        catch (SQLException e) {
            System.out.println("An error occurred while converting the ResultSet to Student");
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalNumberOfStudents() {
        String query = "SELECT COUNT(*) FROM student";
        try {
            ResultSet resultSet = statementOperation(query);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            else {
                System.out.println("Student could not be found");
                return 0;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getAverageGpaOfStudents() {
        String query = "SELECT AVG(GPA) FROM student";

        try {
            ResultSet resultSet = statementOperation(query);
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            } else {
                System.out.println("The average gpa of students could not be found");
                return 0;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Student getStudentHasMinGpa() {
        String query = "SELECT * FROM student ORDER BY GPA ASC LIMIT 1";
        return getOneStudentByQuery(query);
    }

    public Student getStudentHasMaxGpa() {
        String query = "SELECT * FROM student ORDER BY GPA DESC LIMIT 1";
        return getOneStudentByQuery(query);
    }

    public Student getOneStudentByQuery(String query) {
        try {
            ResultSet resultSet = statementOperation(query);
            if (resultSet.next()) {
                return convertResultSetToStudent(resultSet);
            }
            else {
                System.out.println("Student could not be found");
                return null;
            }
        }
        catch (SQLException e) {
            System.out.println("An error occurred while getting the Student by query");
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet statementOperation(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }
        catch (SQLException e) {
            System.out.println("An error occured while executing the statement");
            e.printStackTrace();
            return null;
        }
    }

}
