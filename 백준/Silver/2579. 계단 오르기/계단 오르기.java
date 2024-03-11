import java.util.*;
import java.io.*;

/*
해당 계단까지 올 수 있게 조합할 수 있는 조합 2가지
- '2칸+1칸'으로 도달하기
- '2칸'으로 도달하기
그리고 도달했을 때 어떤 것이 더 값이 높은지 비교하면 된다
=> dp[N] = max(dp[N-3] + arr[N-1], dp[N-2]) + arr[N]
- 계산하기 쉽게 맨 앞에 0을 추가한다(start지점)

[실패]
- 3%(메모리초과): bfs를 이용한 방법
    => 중간값을 저장하지 않아 목표지점까지 가야 Math값을 비교할 수 있고, 중복되는 계산도 많이생긴다
- 96%(IndexOutOfRange): 계단의 개수가 1일떄를 고려하지 않음
    => 계단의 개수가 1일때를 대비하여 조건문을 추가한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        int[] dp = new int[N + 1];
        dp[1] = arr[1];
        if (N > 1) {
            dp[2] = arr[1] + arr[2]; // arr[2]로 두칸오는 것보다 무조건 하나 밟고 오는게 더 점수가 크다
            for (int i = 3; i < N + 1; i++) {
                dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
            }
        }

        bw.write(Integer.toString(dp[N]));

        br.close();
        bw.close();
    }
}