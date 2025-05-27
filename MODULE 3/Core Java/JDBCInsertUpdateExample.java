import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

class StudentDAO {
    private static final String URL = "jdbc:sqlite:students.db";

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.executeUpdate();
            System.out.println("Inserted: " + student.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();
            System.out.println("Updated: " + student.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class JDBCInsertUpdateExample {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Student student1 = new Student(1, "Alice", 20);
        dao.insertStudent(student1);
        Student updatedStudent = new Student(1, "Alice Johnson", 21);
        dao.updateStudent(updatedStudent);
    }
}
