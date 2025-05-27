interface Playable {
    void play();
}

class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Playing guitar with strings");
    }
}

class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Playing piano with keys");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Guitar guitar = new Guitar();
        Piano piano = new Piano();
        
        guitar.play();
        piano.play();
    }
}