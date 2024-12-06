import java.io.*;
import java.util.*;

/*
입력
- N: 상자의 개수(1<=n<=1000)
- 상자의 크기 순서대로 (1<=x<=1_000)

조건
- 앞에 있는 상자 크기 < 뒤에있는 상자 크기:
    뒤에 있는 상자에 넣을 수 있다
- 어떤 상자들을 넣느냐에 따라 한 상자에 몇개의 상자가 들어가는지 바뀐다

풀이
- 가장 긴 증가하는 부분 수열과 동일한 문제
- 자기자신만 존재할때는 1이기 때문에 dp[i]는 모두 1로 초기화
- 자신보다 앞에 있는 숫자들과 비교했을 때, 다음과 같이 계산한다
    j<i, arr[j]<arr[i],
    dp[i] = Max(dp[j] + 1, dp[i])
    => 자기현재값보다 크면 +1해서 없데이트
- dp값중에서 가장 큰값이 max값이다

출력
- 한번에 넣을 수 있는 최대 상자 개수
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

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) { // update
                    dp[i] = Math.max(dp[i], dp[j] + 1);
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