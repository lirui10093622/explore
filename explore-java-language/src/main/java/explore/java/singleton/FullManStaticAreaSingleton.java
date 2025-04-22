package explore.java.singleton;

public class FullManStaticAreaSingleton {
    private static final FullManStaticAreaSingleton INSTANCE;

    private String name;

    static {
        INSTANCE = new FullManStaticAreaSingleton();
        // 初始化逻辑:
        INSTANCE.name = "name";
    }

    public static FullManStaticAreaSingleton getInstance() {
        return INSTANCE;
    }
}
