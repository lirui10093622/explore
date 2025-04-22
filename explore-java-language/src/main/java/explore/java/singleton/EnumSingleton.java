package explore.java.singleton;

public enum EnumSingleton {

    SINGLETON;

    public static EnumSingleton getInstance() {
        return SINGLETON;
    }
}