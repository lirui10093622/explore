package interview.huawei.code;

import java.util.Scanner;

/**
 * TLV解码
 */
public class TLVDecode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String target = scanner.nextLine().trim();
        String line = scanner.nextLine().trim();
        String[] stream = line.split(" ");

        for (int i = 0; i < stream.length; ) {
            String tag = stream[i];
            int len = Integer.parseInt(stream[i + 2] + stream[i + 1], 16);

            i += 3;

            if (tag.equals(target)) {
                System.out.print(stream[i++]);
                for (int j = 0; j < len; j++) {
                    System.out.print(" " + stream[j]);
                }
            }

            i += len;
        }
    }
}