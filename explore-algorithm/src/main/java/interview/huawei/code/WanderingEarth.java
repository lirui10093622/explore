package interview.huawei.code;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 流浪地球
 */
public class WanderingEarth {

    public static void main(String[] args) {

    }

    protected static List<Integer> wanderingEarth(List<Point> points) {
        int N = points.size();
        int[] status = new int[N];
        Arrays.fill(status, -1);
        List<Integer> result = new LinkedList<>();

        points.forEach(point -> status[point.index] = point.time);

        for (int t = 1; t < N; t++) {
            for (int i = 0; i < N; i++) {
                if (status[i] == t) {
                    int left = (i + status.length - 1) % status.length;
                    int right = (i + 1) % status.length;

                    if (status[left] == -1) {
                        status[left] = t;
                    }

                    if (status[right] == -1) {
                        status[right] = t;
                    }
                }
            }
        }

        int maxTime = Arrays.stream(status).max().getAsInt();
        for (int i = 0; i < status.length; i++) {
            if (status[i] == maxTime) {
                result.add(i);
            }
        }

        return result;
    }

    class Point {
        public int time;
        public int index;
    }
}