import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[31];

        // 홀수는 무조건 0
        dp[0] = 1;
        dp[2] = 3;
        for (int i = 3; i <= N; i++) {
            if (i % 2 == 1) { // 홀수는 넘어가기
                continue;
            }
            // 점화식: dp[i] = (dp[i-2]*dp[2]) + (dp[i-4]*2) + ... + (dp[0]*2)
            dp[i] = dp[i - 2] * dp[2];
            for (int j = 0; j < i - 2; j++) { // 홀수는 0이니까 결과에 영향 주지 않음
                dp[i] += dp[j] * 2;
            }
        }
        System.out.println(dp[N]);
    }
}