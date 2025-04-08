package explore.java.gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import explore.java.gc.data.BigObject;
import explore.java.jmx.VMTool;

// -Xmx50M -XX:MaxNewSize=40M -XX:PretenureSizeThreshold=3M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps
public class GCMain {

    private static final List<Object> list = new LinkedList<Object>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        loop:
        while (true) {
            String line = reader.readLine();
            switch (line) {
                case "gc":
                    System.gc();
                    break;

                case "vm":
                    System.out.println(VMTool.getVMMemoryInfo().toJSONString());
                    break;

                case "cl":
                    list.clear();
                    break;

                case "exit":
                    break loop;

                default:
                    if (line.matches("[0-9]+")) {
                        int size = Integer.parseInt(line);
                        BigObject bo = new BigObject(size * 1024 * 1024);
                        list.add(bo);
                        System.out.println(VMTool.getVMMemoryInfo());
                    }
            }
        }
        reader.close();
    }
}