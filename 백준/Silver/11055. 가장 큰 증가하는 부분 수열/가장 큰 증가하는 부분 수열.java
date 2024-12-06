import java.io.*;
import java.util.*;

/*
입력
- 수열 A의 크기 (1<=N<=1_000)
- 수열 A를 이루고 있는 Ai (1<=Ai<=1_000)

조건
- 예)수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8}
       => 1, 2, 50, 60


풀이
- 자기자신만 존재할때는 1이기 때문에 dp[i]는 모두 자기자신으로 초기화
- 자신보다 앞에 있는 숫자들과 비교했을 때, 다음과 같이 계산한다
    j<i, arr[j]<arr[i],
    dp[i] = Max(dp[j] + arr[i], dp[i])
    => 자기현재값보다 작으면 +1해서 없데이트
- dp값중에서 가장 큰값이 max값이다

출력
- A의 가장 큰 증가하는 부분 수열의 합 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        int min = 1_000;
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = arr[i];
            min = Math.min(min, dp[i]);
        }

        int max = dp[0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) { // update
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);

                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        bw.write(Integer.toString(max));

        br.close();
        bw.flush();
        bw.close();
    }

}