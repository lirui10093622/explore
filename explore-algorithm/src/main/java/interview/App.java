package interview;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static int getMinSubArrayLength(int[] nums, int target) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == target) {
                    ans = Math.min(ans, (j - i + 1));
                    break;
                }
            }

            if (sum == 1) {
                break;
            }
        }

        return ans == Integer.MAX_VALUE ? 0: ans;
    }


}
