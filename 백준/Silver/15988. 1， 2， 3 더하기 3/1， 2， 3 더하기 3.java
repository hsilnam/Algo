import java.util.*;
import java.io.*;

/*
1,2,3으로만 조합!!

[1]: 1 = 1
- 1

[2]: 1 + 1 = 2
- 2
- (1) + 1

[3]: 1 + 2 + 1 = 4
- 3
- (2) + 1
- (1 + 1) + 1
- (1) + 2

[4]: 4 + 2 + 1 = 7
- (3) + 1
- (2 + 1) + 1
- (1 + 1 + 1) + 1
- (1 + 2) + 1
---
- (2) + 2
- (1 + 1) + 2
---
- (1) + 3

[5] 7 + 4 + 2 = 13
- (3 + 1) + 1
- (2 + 1 + 1) + 1
- (1 + 1 + 1 + 1) + 1
- (1 + 2 + 1) + 1
- (2 + 2) + 1
- (1 + 1 + 2) + 1
- (1 + 3) + 1
---
- (3) + 2
- (2 + 1) + 2
- (1 + 1 + 1) + 2
- (1 + 2) + 2
---
- (2) + 3
- (1 + 1) + 3

=> dp[N] = dp[N-1] + dp[N-2] + dp[N-3] // 1, 2, 3으로 만들 수 있는 경우의 수 더하기
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 3;
        int N = Integer.parseInt(br.readLine());

        int[] inputs = new int[N];
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            inputs[i] = input;
            max = Math.max(input, max);
        }

        long[] dp = new long[max + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= max; i++) {
            for (int j = i - 3; j < i; j++) {
                dp[i] += dp[j];
            }
            dp[i] %= 1_000_000_009;
        }

        StringBuilder result = new StringBuilder();
        for (int input : inputs) {
            result.append(dp[input]).append("\n");
        }
        System.out.println(result.toString());
    }
}