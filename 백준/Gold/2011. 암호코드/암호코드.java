import java.util.*;
import java.io.*;

/*

-
[예세로 도출해내는 dp 공식]
1. 25114
    2: 2 => 1
    25: 2,5/25 => 2
    251: 2,5,1/25,1 => 2
    2511: 2,5,1,1/25,1,1/2,5,11/25,11 => 4
    25114:2,5,1,1,4/25,1,1,4/2,5,11,4/2,5,1,14/25,11,4/25,1,14 => 6
2. 2910026
    2: 2 => 1
    29: 2,9 => 1
    291: 2,9,1 => 1
    2910: 2,9,10 => 1
    29100: x
3. 211062
    2: 2 => 1
    21: 2,1/21 => 2
    211: 2,1,1/2,11/21,1 => 3
    2110: 2,1,10/21,10 => 2
    21106: 2,1,10,6/21,10,6 => 2
    211062: 2,1,10,6,2/21,10,6,2 => 2
4. 2023
    2: 2 => 1
    20: 20 => 1
    202: 20,2 => 1
    2023: 20,2,3/20,23 => 2
5. 11302
    1: 1 => 1
    11: 1,1/11 => 2
    113: 1,1,3/11,3/1,13 =>3
    1130: X

====
- dp[n+1]로 하여 계산이 용이하게한다
- dp[0] = 0
- 첫번째 자리수일 때,
    0인 경우: 0(불가능한 경우로 처리)
    1이상인 경우: dp[1] = 1
- 둘번째 자리수일 때,
    두자리를 비교했을때,
    - 1~26을 만족하는 경우: dp[2] = 2
    - 27이상인 경우: dp[2] = 1
    +) 10의 배수 일 때: dp[2] -= 1;
- 세번쨰 자리 이상인 경우,
    끝의 두자리를 비교했을때,
    - 00인 경우: 0(불가능한 경우로 처리)
    - 앞자리만 0인 경우: dp[n] = dp[n-1]
    - 끝자리만 0인 경우:
        - 30이상 인 경우: 0(불가능한 경우로 처리)
        - 30미만 인 경우: dp[n] = dp[n-2]
    - 1~26를 만족하는 경우: dp[n] = dp[n-1] + dp[n-2]
    - 만족하지 않는 경우: dp[n] = dp[n-1]
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split("");

        int[] inputs = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            inputs[i] = Integer.parseInt(temp[i]);
        }

        bw.write(Integer.toString(getCnt(inputs)));

        br.close();
        bw.close();
    }

    public static int getCnt(int[] inputs) {
        int[] dp = new int[inputs.length + 1];

        // 첫번째 자리수
        if (inputs[0] == 0) {
            return 0;
        }
        dp[1] = 1;
        if (inputs.length == 1) {
            return dp[1];
        }

        // 두번째 자리수
        int num = inputs[0] * 10 + inputs[1];
        dp[2] = (num >= 27) ? 1 : 2;
        if (num % 10 == 0) {
            dp[2] -= 1;
        }
        if (inputs.length == 2) {
            return dp[2];
        }

        // 세번째 자리수 이상
        for (int i = 2; i < inputs.length; i++) {
            int cnt = 0;
            if (inputs[i - 1] == 0 && inputs[i] == 0) {
                return 0;
            } else if (inputs[i - 1] == 0 && inputs[i] >= 1) {
                cnt = dp[i];
            } else if (inputs[i - 1] >= 1 && inputs[i] == 0) {
                if (inputs[i - 1] >= 3) {
                    return 0;
                }
                cnt = dp[i - 1];
            } else {
                num = inputs[i - 1] * 10 + inputs[i];
                if (num >= 27) {
                    cnt = dp[i];
                } else {
                    cnt = dp[i] + dp[i - 1];
                }
            }
            dp[i + 1] = cnt % 1_000_000;
        }
        return dp[inputs.length];
    }

}