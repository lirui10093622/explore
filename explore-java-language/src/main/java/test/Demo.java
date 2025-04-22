package test;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        for (int k = 3; k <= 4; k++) {
            int[][] data = new int[k][k];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    data[i][j] = i * data.length + j + 1;
                    System.out.print(data[i][j]);
                    if (data[i][j] < 10) {
                        System.out.print("  ");
                    } else {
                        System.out.print(" ");
                    }
                }

                System.out.println();
            }
            List<Integer> result = traverse(data);
            System.out.println(result);
        }
    }

    /**
     * 正方形正斜线遍历。
     * 比如输入 1,2,3 , 输出1,2,4,3,5,7,6,8,9
     *        4,5,6
     *        7,8,9
     */
    public static List<Integer> traverse(int[][] data) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            int x = i;
            for (int j = 0; j <= i; j++) {
                int y = j;
                result.add(data[x--][y++]);
            }
        }

        for (int i = data.length; i > 0; i--) {
            int x = data.length - 1;
            for (int j = 0; j < i; j++) {
                int y = j;
                result.add(data[x--][y++]);
            }
        }








































































































































































































































        return result;
    }
}
