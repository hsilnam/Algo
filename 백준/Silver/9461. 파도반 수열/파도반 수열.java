import java.util.*;
import java.io.*;

/*
dp(0) = 0
dp(1) = 1
dp(2) = dp(1) = 1
dp(3) = dp(2) = 1
dp(4) = dp(1)+dp(3) = 2
dp(5) = dp(0)+dp(4) = 2
dp(6) = dp(1)+dp(5) = 3
dp(7) = dp(2)+dp(6) = 4
dp(8) = dp(3)+dp(7) = 5
dp(9) = dp(4)+dp(8) = 7
dp(10) = dp(5)+dp(9) = 9
...
----
dp(n) = dp(n-5) + dp(n-1)

- 주의: 자료형
 */
public class Main {
    static class Node {
        int num;
        int len;

        public Node(int num, int len) {
            this.num = num;
            this.len = len;
        }
    }

    public static void main(String[] args) throws Exception {
        final int maxN = 100;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        long dp[] = new long[maxN + 1];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = 2;
        for (int i = 5; i < maxN + 1; i++) {
            dp[i] = dp[i - 5] + dp[i - 1];
        }

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            result.append(dp[N]).append("\n");
        }

        System.out.println(result.toString());

        br.close();
        bw.close();
    }
}