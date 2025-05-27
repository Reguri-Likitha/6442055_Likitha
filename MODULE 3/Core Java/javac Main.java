class MessageUtils {
    public static String getGreeting() {
        return "Hello from MessageUtils (simulating com.utils)!";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(MessageUtils.getGreeting());
    }
}
