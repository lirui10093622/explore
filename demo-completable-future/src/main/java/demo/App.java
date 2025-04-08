package demo;

public class App {

    public static void main(String[] args) {
        long r1 = App.getSubArraySum(new long[] {-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(r1);

        long r2 = App.getSubArraySum(new long[] {5,4,-1,7,8});
        System.out.println(r2);
    }

    public static long getSubArraySum(long[] array) {
        if (array.length == 1) {
            return array[0];
        }
        long[] left = new long[array.length];
        left[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            left[i] = array[i] + left[i - 1];
        }
        long[] max = new long[array.length];
        max[array.length - 1] = left[array.length - 1];
        for (int i = array.length - 2; i >= 0; i--) {
            max[i] = Math.max(max[i+1], left[i]);
        }
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            ans = Math.max(max[i] - left[i] + array[i], ans);
        }
        return ans;
    }
}
