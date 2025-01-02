import java.io.*;
import java.util.*;

/*
입력
- n, xi n은 10만 이하, xi는 절대값 100이하

조건
- x1*x2 + x1*x3 + ... x1*xn + x2*x3 ..... + xn-1*xn

풀이
- 식을 묶어보면 다음과같이 나온다
    x1*(x2 + ... + xn) + x2(x3 + ... + xn) +.... + xn-1(xn)
    -> xn부터 x2까지의 누적합을 저장해서 곱해나가면 된다

출력
정답 출력

 */

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        long prefixSum = 0;
        for (int i = N - 1; i >= 1; i--) {
            prefixSum += nums[i];
            answer += nums[i - 1] * prefixSum;
        }

        bw.write(Long.toString(answer));


        br.close();
        bw.flush();
        bw.close();
    }

}