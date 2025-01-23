import java.io.*;
import java.util.*;

/*
입력
- N: 정수 (1 <= N <= 10^6)

조건
- 연산
    1. X%3 == 0 => X/3
    2. X%2 == 0 => X/2
    3. X-1
- 목표: 1만들기

풀이
- 최소 연산 횟수를 최댓값으로 저장한다
- 재귀로 연산을 하나씩 적용하며 탐색한다.
    - 현재까지의 최소연산 횟수가 현재 연산 횟수보다 작으면 재귀를 종료한다
    - 1에 도달했을 떄 재귀를 종료하고 최소값으로 업데이트한다


출력
- 연산 횟수의 최소값
 */

public class Main {
    public static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        min = N;

        explore(N, 0);

        bw.write(String.valueOf(min));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void explore(int num, int cnt) {
        if (min <= cnt) {
            return;
        }

        if (num == 1) {
            min = cnt;
            return;
        }

        if (num % 3 == 0) {
            explore(num / 3, cnt + 1);
        }
        if (num % 2 == 0) {
            explore(num / 2, cnt + 1);
        }
        explore(num - 1, cnt + 1);
    }

}