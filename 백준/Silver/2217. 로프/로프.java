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
- greedy 사용
- 로프가 버틸 수 있는 최대 중량을 오름차순으로 구하고
    큰 것(뒤)부터 하나 씩 개수를 추가해가면서 버틸 수 있는 최대 중량을 구한다
    - 내림차순으로 하는 이유는 자신보다 높은 로프를 건너뛰고 자신을 포함한다해도 최대가 될 수 없기 때문
    - 가능한 최대 중량: 가장 작은 중량을 가지는 로프 * k
        - 가장 작은 중량을 가지는 로프를 만족할 수 있도록

출력
- 로프들 이용해서 들어올릴 수 있는 무게의 최대 중량

[오답노트]
- 4% (시간초과) (O(N^2))
    - k를 늘려가며 검사할 때, w의 값을 하나씩 올려가며 찾음
        => 이분탐색 이용

- 성공 (O(nlogn))
    - 이분탐색으로 인해 시간 복잡도가 늘었다
        - 이분탐색
            - 이분탐색 이용
            - low: 사용하는 로프 중 최소 로프 * k
            - high: 사용하는 로프 중 최대 로프 *k
            - break 걸지 말고, low,high엇갈리때까지 검사하기

        - 1~N개의 로프를 사용했을 때의 결과에서, 그 중 최대값 출력
        => greedy를 사용하면 더 효율적인 O(nlogn)으로 만들 수 있다

[참고]
Arrays.sort: O(nlogn)
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes);

        int maxW = 0;
        for (int i = N - 1, k = 1; i >= 0; i--, k++) {
            int minRopeW = ropes[i];
            maxW = Math.max(maxW, minRopeW * k);
        }

        bw.write(String.valueOf(maxW));

        br.close();
        bw.flush();
        bw.close();

        /* // 이분탐색 사용
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
         */
    }
}
