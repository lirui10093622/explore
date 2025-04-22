package explore.java.singleton;

public class StaticInnerClassSingleton {

    private static class InnerClass {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.instance;
    }
}