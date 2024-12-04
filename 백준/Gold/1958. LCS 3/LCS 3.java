import java.io.*;
import java.util.*;

/*
입력
- 3개의 문자열 주어짐
    - 알파벳 소문자, 길이 100 이하

풀이
- LCS: 최장 공통 부분 수열
    - 문자가 같은 경우: dp[i][j][k] = dp[i-1][j-1][k-1]
    - 문자가 다른 경우: dp[i][j][k] = Max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1])
    - dp 크기는 범위에 벗어나지 않게 앞에 1크기만큼 패딩을 준다
    
출력
- 3문자열의 LCS 길이 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars1 = br.readLine().toCharArray();
        char[] chars2 = br.readLine().toCharArray();
        char[] chars3 = br.readLine().toCharArray();

        int[][][] dp = new int[chars1.length + 1][chars2.length + 1][chars3.length + 1];
        for (int i = 1; i < chars1.length + 1; i++) {
            for (int j = 1; j < chars2.length + 1; j++) {
                for (int k = 1; k < chars3.length + 1; k++) {
                    if (chars1[i - 1] == chars2[j - 1] && chars2[j - 1] == chars3[k - 1]) { // a=b=c
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                        continue;
                    }
                    // 다른 경우
                    dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
                }
            }
        }

        bw.write(Integer.toString(dp[chars1.length][chars2.length][chars3.length]));

        br.close();
        bw.flush();
        bw.close();
    }
}