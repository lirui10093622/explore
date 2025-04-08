package interview.huawei.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 数大雁
 */
public class GooseCount {

    static class QuackRange {
        int q;
        int c;

        public QuackRange(int q, int c) {
            this.q = q;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String quacks = scanner.next();
        System.out.println(getGooseCount(quacks));
    }

    static int getGooseCount(String quacks) {
        List<QuackRange> ranges = new LinkedList<>();

        List<Integer> q = new LinkedList<>();
        int u = 0;
        int a = 0;
        int c = 0;

        for (int i = 0; i < quacks.length(); i++) {
            char ch = quacks.charAt(i);
            switch (ch) {
                case 'q':
                    q.add(i);
                    break;
                case 'u':
                    if (u + 1 <= q.size()) {
                        u++;
                    }
                    break;
                case 'a':
                    if (a + 1 <= u) {
                        a++;
                    }
                    break;
                case 'c':
                    if (c + 1 <= a) {
                        c++;
                    }
                    break;
                case 'k':
                    if (c > 0) {
                        ranges.add(new QuackRange(q.remove(0), i));
                        u--;
                        a--;
                        c--;
                    }
                    break;
                default:
                    return -1;
            }
        }

        if (ranges.size() == 0) return 0;

        int ans = 1;
        for (int i = 0; i < ranges.size(); i++) {

            int count = 1;
            for (int j = i + 1; j < ranges.size(); j++) {
                if (ranges.get(i).c >= ranges.get(j).q) {
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }

        return ans;
    }
}
