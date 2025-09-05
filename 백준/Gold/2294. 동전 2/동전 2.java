import java.io.*;
import java.util.*;

/*
입력
- N: 동전의 종류, K: 목표 가치의 합 (1<=N<=100), 1<=K<=10_000)
- 동전의 가치

조건
- 조건 개수 최소
- 동전 개수 제한 없음

풀이
- DP 사용
- 1차원 배열을 만든다 (idx: 합, value: 최소 개수)
- 현재 검사하는 동전을 개수 상관없이 포함했을 때와 제했을 때 최소 개수를 검사하고 최소 개수를 기록한다.
- 동전을 여러번 사용할 수 있으니 오름차순으로 검사 (갱신한 값 사용)
- 해당 값에 대하여 현재 코인을 포함했을 때의 개수 vs 제외했을 때의 개수 중 최소를 선택

출력
- 동전 최소 개수
- 불가능한 경우는 -1

---
실패 (3%)
- greedy방식으로 접근
    - 높은 숫자를 많이 이용
-> greedy로는 만족하지 않는 경우가 있음

 */

public class Main {
    public static final int MAXCNT = 10_001;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] mins = new int[K + 1];
        Arrays.fill(mins, MAXCNT);
        mins[0] = 0;

        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = coin; j <= K; j++) {
                if (mins[j - coin] != MAXCNT) {
                    mins[j] = Math.min(mins[j], mins[j - coin] + 1);
                }
            }
        }

        bw.write(Integer.toString((mins[K] == MAXCNT) ? -1 : mins[K]));

        br.close();
        bw.flush();
        bw.close();
    }
}