package explore.java.singleton;

public class HungryManSingleton {

    private static volatile HungryManSingleton instance;

    public static HungryManSingleton getInstance_unsafe() {
        if (instance == null) {
            instance = new HungryManSingleton();
        }
        return instance;
    }

    public static synchronized HungryManSingleton getInstance_synchronized() {
        if (instance == null) {
            instance = new HungryManSingleton();
        }
        return instance;
    }

    public static HungryManSingleton getInstance_double_check() {
        if (instance == null) {
            synchronized (HungryManSingleton.class) {
                if (instance == null) {
                    instance = new HungryManSingleton();
                }
            }
        }
        return instance;
    }
}