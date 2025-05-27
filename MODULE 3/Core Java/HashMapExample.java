import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter student ID and name pairs (enter -1 for ID to stop):");
        
        while (true) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            
            if (id == -1) {
                break;
            }
            
            scanner.nextLine();
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            
            students.put(id, name);
        }
        
        System.out.print("Enter a student ID to retrieve name: ");
        int searchId = scanner.nextInt();
        
        if (students.containsKey(searchId)) {
            System.out.println("Student name: " + students.get(searchId));
        } else {
            System.out.println("Student ID not found.");
        }
        
        scanner.close();
    }
}