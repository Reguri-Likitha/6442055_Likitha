public class Hello {
    public int square(int x) {
        return x * x;
    }

    public static void main(String[] args) {
        Hello h = new Hello();
        System.out.println(h.square(5));
    }
}
