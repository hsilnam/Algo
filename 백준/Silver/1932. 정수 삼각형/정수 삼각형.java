import java.util.*;
import java.io.*;

/*
               (0,0)  // depth, idx
            (1,0) (1,1)
          (2,0) (2,1) (2,2)
        (3,0) (3,1) (3,2) (3,3)
     (4,0) (4,1) (4,2) (4,3) (4,4)
 => 밑에서부터 Max값을 더해가면 값이 나온다
 ----
 dp[d][idx] = arr[d][idx] + Max(dp[d+1][idx], dp[d+1][idx+1])

 [실패]
 - 3% (시간초과)

 */
public class Main {
    public static int N;
    public static int[][] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
            Arrays.fill(dp[i], -1);
        }

        bw.write(Integer.toString(find(0, 0)));

        br.close();
        bw.close();
    }

    public static int find(int depth, int idx) {
        if (depth == N - 1) {
            return arr[depth][idx];
        }
        if (dp[depth][idx] == -1) {
            dp[depth][idx] = arr[depth][idx] + Math.max(find(depth + 1, idx), find(depth + 1, idx + 1));
        }
        return dp[depth][idx];
    }

}