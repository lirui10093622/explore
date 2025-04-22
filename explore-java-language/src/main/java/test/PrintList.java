package test;

import java.util.ArrayList;
import java.util.List;

public class PrintList {

    public static List<Object> flattenList(List<Object> list){
        List<Object> result = new ArrayList<>();
        if (list != null || list.size() > 0) {
            for (Object o : list) {
                if (o instanceof List) {
                    result.addAll(flattenList((List<Object>) o));
                } else {
                    result.add(o);
                }
            }
        }
        return result;
    }
}