import java.lang.reflect.*;

class Person {
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public int add(int a, int b) {
        return a + b;
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("Person");
        Object obj = clazz.getDeclaredConstructor().newInstance();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
            Class<?>[] params = method.getParameterTypes();
            for (Class<?> param : params) {
                System.out.println("  Param type: " + param.getName());
            }
        }

        Method greetMethod = clazz.getDeclaredMethod("greet", String.class);
        greetMethod.invoke(obj, "Alice");

        Method addMethod = clazz.getDeclaredMethod("add", int.class, int.class);
        Object result = addMethod.invoke(obj, 10, 20);
        System.out.println("Result of add(10, 20): " + result);
    }
}
