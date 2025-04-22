package explore.java.create_object;

import java.io.Serializable;
import java.util.List;

public class ExampleObject implements Serializable, Cloneable {

    public ExampleObject() {
    }

    public ExampleObject(String name, List<String> list) {
        this.name = name;
        this.list = list;
    }

    private String name;
    private List<String> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ExampleObject{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }

    public ExampleObject clone() throws CloneNotSupportedException {
        return (ExampleObject) super.clone();
    }
}