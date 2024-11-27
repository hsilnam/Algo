import java.io.*;
import java.util.*;

/*
Queue 이용
- 프린트 조건
    1. 현재 문서 중요도 확인
    2. 현재 문서보다 중요도 높은 문서있으면, 현재 문서 뒤로 보냄,
        현재 문서의 중요도가 가장 높으면, 출력
- 구해야하는 것: 목표 문서의 출력 순서 (1~)
- 구현
    - Queue<int[]>으로 만들어 문서의 id(0~), 중요도를 저장
    - 현재 문서와 나머지 문서들을 비교했을 때, 큰게 있으면 현재 문서 정보 뒤로 추가(offer)
    - 현재 문서의 중요도가 가장 높으면, 출력 순서를 높인다.
         이때, 목표 문서라면, 멈추고 현재 출력 순서 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String[] temp = br.readLine().split(" ");
            int N = Integer.parseInt(temp[0]);
            int M = Integer.parseInt(temp[1]); // targetId

            Queue<int[]> queue = new ArrayDeque<>(); // id(0~), important
            temp = br.readLine().split(" "); // docs

            for (int i = 0; i < N; i++) {
                int important = Integer.parseInt(temp[i]);
                queue.offer(new int[]{i, important});
            }

            int order = 1;
            while (!queue.isEmpty()) {;
                int[] cur = queue.poll();

                boolean isOk = true;
                for (int[] q : queue) {
                    if (cur[1] < q[1]) {
                        isOk = false;
                        break;
                    }
                }
                if (!isOk) {
                    queue.offer(cur);
                    continue;
                }
                if (M == cur[0]) {
                    break;
                }
                order++;
            }
            result.append(order).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}