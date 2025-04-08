package interview.huawei.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * App防沉迷
 */
public class AppRegister {

    static class App {
        String name;
        int priority;
        int start;
        int end;

        public App(String name, int priority, int start, int end) {
            this.name = name;
            this.priority = priority;
            this.start = start;
            this.end = end;
        }
    }

    static List<App> apps = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int priority = scanner.nextInt();
            String start = scanner.next();
            String end = scanner.next();

            int startTime = convert(start);
            int endTime = convert(end);
            if (startTime < endTime) {
                apps.add(new App(name, priority, startTime, endTime));
            }
        }

        String time = scanner.next();

        int t = convert(time);

        ArrayList<App> avaliables = new ArrayList<>();
        for (App app : apps) {
            if (app.start <= t && t < app.end) {

            }
        }


        App registered = null;
        for (App app : apps) {
            if (app.start <= t && t < app.end) {
                if (registered == null) {
                    registered = app;
                } else if (app.priority > registered.priority) {
                    registered = app;
                }
            }
        }

        if (registered != null) {
            System.out.println(registered.name);
        } else {
            System.out.println("NA");
        }
    }

    protected static int convert(String time) {
        String[] ss = time.split(":");
        return Integer.parseInt(ss[1]) * 60 + Integer.parseInt(ss[0]);
    }
}
