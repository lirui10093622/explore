package explore.java.singleton;

public class FullManSingleton {

    private static final FullManSingleton INSTANCE = new FullManSingleton();

    public static FullManSingleton getInstance() {
        return INSTANCE;
    }
}