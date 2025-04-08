package interview.huawei.code;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 日志采集系统
 */
public class LogSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] T = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = T.length;

        int[] dp = new int[n];
        dp[0] = T[0];

        // delay[i]表示：第i时刻被扣除的负向分
        int[] delay = new int[n];

        // 第i时刻最终得分 = 第i时刻正向分 - 第i时刻负向分, 保留最大得分
        int max_score = dp[0] - delay[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(100, dp[i - 1] + T[i]); // 最多上报100条
            delay[i] = delay[i - 1] + dp[i - 1];

            max_score = Math.max(max_score, dp[i] - delay[i]);

            // 达到100条时必须上报，此时完成首次上报，结束循环
            if (dp[i] >= 100) break;
        }

        System.out.println(max_score);
    }
}
