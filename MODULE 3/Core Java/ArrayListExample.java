import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        
        System.out.println("Enter student names (type 'done' to finish):");
        
        while (true) {
            System.out.print("Enter name: ");
            input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            
            studentNames.add(input);
        }
        
        System.out.println("\nStudent Names:");
        for (String name : studentNames) {
            System.out.println(name);
        }
        
        scanner.close();
    }
}