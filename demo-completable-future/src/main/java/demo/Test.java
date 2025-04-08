package demo;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws IOException {

        List<Result> resultList = mockInterfaceResult();

        Map<String, Boolean> ans = new HashMap<>();
        for (Result result : resultList) {
            ans.put(result.type, ans.getOrDefault(result.type, true) && "正常".equals(result.status));
        }

        if (ans.containsValue(false)) {
            System.out.printf("有异常情况");
        } else {
            System.out.printf("全部正常");
        }
    }

    static String text = "";

    public static List<Result> mockInterfaceResult() {
        String[] lines = text.split("\n");
        List<Result> resultList = new ArrayList<>();
        for (String line : lines) {
            resultList.add(parse(line));
        }
        return resultList;
    }

    public static Result parse(String line) {
        String[] strs = line.split(" ");
        Result result = new Result();
        result.type = strs[1].trim();
        result.status = strs[2].trim();
        return result;
    }
}


class Result {
    public String type;
    public String status;
}