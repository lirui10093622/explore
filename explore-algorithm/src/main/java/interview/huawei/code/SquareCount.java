package interview.huawei.code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 构成正方形的数量
 */
public class SquareCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] coordinates = new String[n];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = scanner.nextLine();
        }

        int count = getSquareCount(coordinates);
        System.out.println(count);
    }

    public static int getSquareCount(String[] coordinates) {
        Set<String> set = new HashSet<>(Arrays.asList(coordinates));
        int N = coordinates.length;

        int count = 0;
        for (int i = 0; i < N; i++) {
            String coordinate1 = coordinates[i];
            String[] arr1 = coordinate1.split(" ");
            int x1 = Integer.parseInt(arr1[0]);
            int y1 = Integer.parseInt(arr1[1]);

            for (int j = i + 1; j < N; j++) {
                String[] arr2 = coordinates[j].split(" ");
                int x2 = Integer.parseInt(arr2[0]);
                int y2 = Integer.parseInt(arr2[1]);

                int dx = (x2 - x1);
                int dy = (y2 - y1);

                int x3 = x1 + dy;
                int y3 = y1 - dx;
                int x4 = x2 + dy;
                int y4 = y2 - dx;

                if (set.contains(x3 + " " + y3) && set.contains(x4 + " " + y4)) count++;


                int x5 = x1 - dy;
                int y5 = y1 + dx;
                int x6 = x2 - dy;
                int y6 = y2 + dx;
                if (set.contains(x5 + " " + y5) && set.contains(x6 + " " + y6)) count++;
            }
        }
        return count / 4;
    }
}

