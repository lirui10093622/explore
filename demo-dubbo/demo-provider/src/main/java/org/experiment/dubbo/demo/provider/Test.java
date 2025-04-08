package org.experiment.dubbo.demo.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by neil on 2024/12/26.
 */
public class Test {

    public static void main(String[] args) {
        int[] nums = {1};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(toList(nums));
            for (int j = 0; j < nums.length - 1; j++) {
                int num = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = num;
                if (j < nums.length -2) {
                    result.add(toList(nums));
                }
            }
        }
        return result;
    }

    public static List<Integer> toList(int[] t) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < t.length; i++) {
            list.add(t[i]);
        }
        return list;
    }
}
