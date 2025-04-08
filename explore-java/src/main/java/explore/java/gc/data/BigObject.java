package explore.java.gc.data;

public class BigObject {

    private byte[] data;

    public BigObject(int size) {
        data = new byte[size];
    }

    public byte[] getData() {
        return data;
    }

    public int getSize() {
        return data.length;
    }
}