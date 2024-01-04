import java.util.*;
import java.io.*;

/*
f(1): 1 =>> 1
f(2): 2 / (1) + 1 => 1 + f(1) =>> 2
f(3): 3 / (2) + 1 / (1 + 1) + 1 / (1) + 2 => 1 + f(2) + f(3) =>> 4
f(4): (3) + 1 / (2 + 1) + 1 / (1 + 1 + 1) + 1 / (1 + 2) + 1 /
        (2) + 2 / (1 + 1) + 2
        (1) + 3
        => f(3) + f(2) + f(1)
        =>> 7
-------
f(n): f(n-3) + f(n-2) + f(n-1)

 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dp = new int[11]; // 10이 max
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            result.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}