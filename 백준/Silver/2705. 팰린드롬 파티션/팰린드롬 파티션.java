import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


/*
1: 1
    = 1
2: 2, 11
    = 2
3: 3, 111
    = 2
4: 4, 1(2)1,            22
      (1(2)1, 1(11)1)
    = 4
5: 5, 1(3)1,            2(1)2
      (1(3)1,(1(111)1))
    = 4
6: 6, 1(4)1,                    2(2)2,                          33
      (1(4)1, 1(121)1, 1(111)1, 1(22)1(x))   (2(2)2, 2(11)2(x))
    = 6
7: 7, 1(5)1,                                2(3)2,              3(1)3
      1(5)1, 1(131)1, 1(11111)1, 1(212)1(x) 2(3)2, 2(111)2(x)
    = 6
8: 8, 1(6)1,                                    2(4)2,                              3(2)3,          44
      1(6)1,1(141)1,1(121)1,1(1111)1,1(22)1(x)  2(4)2,2(121)2(x),2(22)2,2(1111)2(x) 3(2)3, 3(11)3
    = 10
....

=> dp 이용
- 짝수: dp[n-1](이전값)+ dp[n/2]
- 홀수: dp[n-1]
- 미리 1000(max)에 대한 dp값을 전부 구한 후 답 출력
 */

public class Main {
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int MAX = 1000;
        int[] dp = new int[MAX + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < MAX + 1; i++) {
            if (i % 2 == 0) {// 짝수
                dp[i] = dp[i - 1] + dp[i / 2];
            } else { // 홀수
                dp[i] = dp[i - 1];
            }
        }

        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            answer.append(dp[N]).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}