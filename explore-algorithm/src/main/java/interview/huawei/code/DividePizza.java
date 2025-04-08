package interview.huawei.code;

import java.util.Scanner;

/**
 * 分披萨
 */
public class DividePizza {

    private static int[] pizza;

    private static long[][] cache;

    public static void main(String[] args) {
        callCachedRecursive();
    }

    // region 普通递归
    protected static void callSimpleRecursive() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = scanner.nextInt();
        }

        long ans = 0;
        for (int i = 0; i < pizza.length; i++) {
            ans = Math.max(ans, pizza[i] + simpleRecursive(check(i - 1), check(i + 1)));
        }

        System.out.println(ans);
    }

    private static long simpleRecursive(int left, int right) {
        if (pizza[left] > pizza[right]) {
            left = check(left - 1);
        } else {
            right = check(right + 1);
        }

        if (left == right) {
            return pizza[left];
        }
        return Math.max(pizza[left] + simpleRecursive(check(left - 1), right), pizza[right] + simpleRecursive(left, check(right + 1)));
    }
    // endregion

    // region 缓存递归
    protected static void callCachedRecursive() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = scanner.nextInt();
        }

        cache = new long[n][n];

        long ans = 0;
        for (int i = 0; i < pizza.length; i++) {
            ans = Math.max(ans, pizza[i] + cahcedRecursive(check(i - 1), check(i + 1)));
        }

        System.out.println(ans);
    }

    private static long cahcedRecursive(int left, int right) {
        if (pizza[left] > pizza[right]) {
            left = check(left - 1);
        } else {
            right = check(right + 1);
        }

        if (left == right) {
            cache[left][right] = pizza[left];
        } else {
            cache[left][right] = Math.max(pizza[left] + cahcedRecursive(check(left - 1), right), pizza[right] + cahcedRecursive(left, check(right + 1)));
        }
        return cache[left][right];
    }
    // endregion

    private static int check(int i) {
        if (i < 0) {
            return (pizza.length + i) % pizza.length;
        }
        if (i >= pizza.length) {
            return i % pizza.length;
        }
        return i;
    }
}
