package explore.java.create_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class CreateObjectDemo {

    public static void main(String[] args) throws Exception {

        // 方法一：通过new关键字创建对象：
        ExampleObject example1 = new ExampleObject();
        example1.setName("ExampleObject");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        example1.setList(list);
        System.out.println("example1 = " + example1);

        // 方法二：通过Class.newInstance(), 底层调用Constructor
        Class<?> clazz = Class.forName("explore.java.create_object.ExampleObject");
        ExampleObject example2 = (ExampleObject) clazz.newInstance();
        System.out.println("example2 = " + example2);

        // 方法三：通过Constructor
        Constructor<ExampleObject> constructor = ExampleObject.class.getConstructor();
        ExampleObject example3 = constructor.newInstance();
        System.out.println("example3 = " + example3);

        // 方法四：克隆
        ExampleObject example4 = example1.clone();
        System.out.println("example4 = " + example4);

        // 方法五：反序列化
        byte[] object_data;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(example1);
        oos.flush();
        oos.close();
        object_data = os.toByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(object_data);
        ObjectInputStream ois = new ObjectInputStream(is);
        ExampleObject example5 = (ExampleObject) ois.readObject();
        System.out.println("example5 = " + example5);

        // 方法六：工厂方法

        // 方法七：构建器模式

        // 方法八：容器
    }
}
