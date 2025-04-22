package test;

public class LazyObject {

    private static LazyObject instance;

    public static LazyObject getInstance() {
        if (instance == null) {
            synchronized (LazyObject.class) {
                if (instance == null) {
                    instance = new LazyObject();
                }
            }
        }
        return instance;
    }
}
