import java.io.*;
import java.util.*;

/*
입력
- N: 서로다른 자연수 개수
- S: 자연수의 합 (1<=S<=4_294_967_295)

조건

풀이
- S      N
  1: 1 = 1 !
  2: 2 = 1
  3: 1+2 = 2 !
  4: 1+3 = 2
  5: 2+3 = 2
  6: 1+2+3 = 3 !
  ...
  => 1~N의 합 = N(N+1)/2
- S값이 N(N+1)/2 임을 이용하여 이분탐색

출력
- N의 최댓값
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long S = Long.parseLong(br.readLine());
        long left = 0L, right = 100_000L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = mid * (mid + 1) / 2;
            if (sum > S) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(right));

        br.close();
        bw.flush();
        bw.close();
    }
}
