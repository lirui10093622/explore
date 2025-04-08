package interview.huawei.code;

import java.util.Scanner;

/**
 * 靠谱的车
 */
public class TaxiFare {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String faultFare = scanner.nextLine();

        int rightFare = 0;
        int length = faultFare.length();
        for (int i = 0; i < length; i++) {
            String s = faultFare.substring(i, i + 1);
            int num = Integer.parseInt(s);
            rightFare = rightFare * 9 + num;
        }

        System.out.println(rightFare);
    }
}