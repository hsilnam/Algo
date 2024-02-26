import java.util.*;
import java.io.*;

/*
- dp를 활용하는 문제
    f(0) = 0 / 1,0
    f(1) = 1 / 0,1
    f(2) = f(0) + f(1) / 1,1
    f(3) = f(1) + f(2) / 1,2
    f(4) = f(2) + f(3) / 2,3
    =====
    => dp[n] = dp[n-2] + dp[n-1]
- 2차원 배열로 0의 개수, 1의 개수 같이 나타내기
- 최대 N은 40까지 올 수 있고, 테스트케이스를 여러개 받기떄문에
    40까지의 dp값은 미리 한번에 계산하여 바로 정답을 가져온다.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][2];
        dp[0] = new int[]{1, 0};
        dp[1] = new int[]{0, 1};
        for (int i = 2; i < 41; i++) {
            dp[i] = new int[]{dp[i - 2][0] + dp[i - 1][0], dp[i - 2][1] + dp[i - 1][1]};
        }

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            result.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}