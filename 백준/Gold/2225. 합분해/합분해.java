import java.util.*;
import java.io.*;

/*
dp[N][K] = dp[N-1][K] + dp[N][K-1]
dp[0][K], dp[N][0] = 0
dp[N][1] = 1
dp[1][K] = K
========
dp[1][1]: (1) = 1
dp[1][2]: (1, 0) = 2
dp[1][3]: (1, 0, 0) = 3
-----
dp[2][1]: (2) = 1
dp[2][2]: (1, 1) / (2, 0) = 3
dp[2][3]: (1, 1, 0) / (2, 0, 0) = 6
-----
dp[3][1]: (3) = 1
dp[3][2]: (2, 1) / (3, 0) = 4
dp[3][3]: (1, 1, 1) / (2, 1, 0) / (3, 0, 0) = 10

=> dp[3][3] = dp[3][2] + dp[2][3] = 4 + 6 = 10
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        int[][] dp = new int[N + 1][K + 1];
        for (int n = 1; n < N + 1; n++) {
            dp[n][1] = 1;
        }
        for (int k = 1; k < K + 1; k++) {
            dp[1][k] = k;
        }

        for (int n = 2; n < N + 1; n++) {
            for (int k = 2; k < K + 1; k++) {
                dp[n][k] = (dp[n - 1][k] + dp[n][k - 1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[N][K]);
    }
}