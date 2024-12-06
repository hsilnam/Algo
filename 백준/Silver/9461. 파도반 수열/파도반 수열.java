import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/*
입력
- T: tc
- N: (1<=N<=100)

조건
- 정삼각형이 나선 모양으로 놓여져 있음
- 첫 삼각형의 변의 길이: 1
- 가장 긴변의 길이 K일 때, 그 변에 길이가 K인 정삼각형 추가
- P(N): 나선에 있는 정삼각형의 변의 길이
    - 예시) P(1) ~ P(10)
        = 1, 1, 1, 2, 2, 3, 4, 5, 7, 9


풀이
- P(1)~P(5)까지는 일정한 패턴이 없기 때문에 초기값으로 한다
   1, 1, 1, 2, 2
- P(6)부터는
    P(N) = P(N-5) + P(N-1) 로 길이가 정해진다
- 미리 P(100)을 만들어 놓은 후에 테스트케이스에 맞는 답을 바로 가져온다
- 이때 dp의 누적 합이 int 범위를 넘어서으로 long으로 한다

출력
- 각 테스트 케이스마다 P(N) 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] dp = new long[101]; // 맨 앞은 패딩
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for (int i = 5; i < 101; i++) {
            dp[i] = dp[i - 5] + dp[i - 1];
        }

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            answer.append(dp[N]).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}
