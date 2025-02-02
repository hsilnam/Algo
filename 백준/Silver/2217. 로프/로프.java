import java.io.*;
import java.util.*;

/*
입력
- N: 로프 개수 (1<=N<=100_000)
- 로프가 버틸 수 있는 최대 중량 (0<N<10_000, 자연수)

조건
- 여러 개 로프 병렬로 연결 시 각각 로프에 걸리는 중량 나눌 수 있음: w/k
- 모든 로프 사용할 필요 없음

풀이
- 최대 버틸 수 있는 중량: 100_000 * 10_000 = 1000_000_000
- 로프가 버틸 수 있는 최대 중량을 내림차순으로 정렬하고,
    하나 씩 개수를 추가해가면서 몸든 버틸 수 있는 최대 중량을 구한다
    - 내림차순으로 하는 이유는 자신보다 높은 로프를 건너뛰고 자신을 포함한다해도 최대가 될 수 없기 때문
- 이분탐색 이용
    - low: 사용하는 로프 중 최소 로프 * k
    - high: 사용하는 로프 중 최대 로프 *k
    - break 걸지 말고, low,high엇갈리때까지 검사하기

- 1~N개의 로프를 사용했을 때의 결과에서, 그 중 최대값 출력

출력
- 로프들 이용해서 들어올릴 수 있는 무게의 최대 중량

[오답노트]
4% (시간초과)
- 검사하는 최대 무게 중량은 사용하는 로프의 합으로 시작
    -> 이분탐색 이용
    - low는 사용하는 로프 중 한 로프가 버틸 수 있는 최소 중량, high는 모든 로프의 버틸수있는 중량의 합
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N + 1]; // rope order: 1~N
        for (int i = 1; i < N + 1; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);
        // 내림차순으로 만들기
        for (int i = 1; i <= N / 2; i++) { // 반만 swap // 맨앞은 패딩
            int temp = ropes[i];
            ropes[i] = ropes[N - i + 1];
            ropes[N - i + 1] = temp;
        }

        int maxW = 0;
        for (int k = 1; k < N + 1; k++) { // 1~N
            int minRopeW = ropes[k]; // k == minRopeId

            int low = minRopeW * k;
            int high = ropes[1] * k;
            while (low <= high) {
                int mid = (low + high) / 2; // w

                if (mid / k >= minRopeW) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            maxW = Math.max(maxW, low);
        }

        bw.write(String.valueOf(maxW));

        br.close();
        bw.flush();
        bw.close();
    }
}