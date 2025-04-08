package interview.huawei.code;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 喊7的次数重排
 */
public class SevenCount {

    public static void main(String[] args) {
        int totalGo = 0; // 总次数
        int person = 0; // 人数
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            totalGo += scanner.nextInt();
            person++;
        }

        int[] times = new int[person];
        int index = 0;
        int num = 1;
        while (totalGo > 0) {
            if (num % 7 == 0 || (num + "").contains("7")) {
                totalGo--;
                times[index]++;
            }

            num++;
            if (++index >= person) {
                index = 0;
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int time : times) {
            sj.add(time + "");
        }

        System.out.println(sj.toString());
    }
}
