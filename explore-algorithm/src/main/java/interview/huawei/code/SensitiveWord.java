package interview.huawei.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 敏感字段
 */
public class SensitiveWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine().trim());
        String command = scanner.nextLine().trim();

        List<String> words = new LinkedList<>();

        boolean quoteOpened = false;

        int count = 0;
        char[] buffer = new char[127];
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            if ('"' == c) {
                buffer[count++] = c;
                quoteOpened = !quoteOpened;
            } else if ('_' == c) {
                if (quoteOpened) {
                    buffer[count++] = c;
                } else if (count > 0) {
                    words.add(new String(buffer, 0, count));
                    count = 0;
                }
            } else { // ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')
                buffer[count++] = c;
            }
        }

        if (count > 0) {
            words.add(new String(buffer, 0, count));
        }

        if (index >= words.size()) {
            System.out.printf("ERROR");
        } else {
            StringBuilder newCommand = new StringBuilder();
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                if (i == index) {
                    word = "******";
                }

                if (newCommand.length() > 0) {
                    newCommand.append("_");
                }
                newCommand.append(word);
            }
            System.out.print(newCommand);
        }
    }
}
