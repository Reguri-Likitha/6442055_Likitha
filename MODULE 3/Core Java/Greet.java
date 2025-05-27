public class Greet {
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        Greet g = new Greet();
        System.out.println(g.sayHello("World"));
    }
}
