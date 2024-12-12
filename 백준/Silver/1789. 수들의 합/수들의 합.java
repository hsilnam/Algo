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

[오답노트]
- 메모리초과 (25%)
    - charArray로 단어를 받고, 철자들을 사전순으로 정렬한 후 시작한다
    - 중복없는 순열처럼 단어의 길이 만큼의 idx를 순열로 만들고
   만들어진 idx 순서대로 단어 매핑하여 단어 만듦
   => 중복인 애너그램 발생: 같은 알파벳이라도 다른 인덱스로 선택 처리
    ex) a(1)a(2)bc, a(2)a(1)bc
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