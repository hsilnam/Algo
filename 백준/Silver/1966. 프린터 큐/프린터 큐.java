import java.util.*;
import java.io.*;

/*
- queue 이용
- 몇번째 원소의 숫자였는지 기억하기 위해 idx 번호 같이 저장해주기
- 첫번째 값과 뒤의 값들을 한바퀴 돌려서 중요도를 비교한다
    - 하나라도 첫번째 값보다 높은게 있으면, 검사를 멈추고 뒤에 재배치하기
    - 첫번째 값이 같거나 가장 높으면 인쇄하기
        - 이때, 몇번째 인쇄인지 개수를 세는 cnt값 1 올려주기
        - 만약, 현재 값이 몇번째로 인쇄되었는지 궁금한 문서라면 멈추고 cnt값 프린트하기
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
            int M = Integer.parseInt(temp[1]);

            temp = br.readLine().split(" ");
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                queue.offer(new int[]{Integer.parseInt(temp[i]), i}); // priority, idx
            }

            int cnt = 1;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                boolean isLargest = true;
                for (int[] other : queue) {
                    if (cur[0] < other[0]) {
                        isLargest = false;
                        break;
                    }
                }
                if (!isLargest) {
                    queue.offer(cur);
                } else {
                    if (cur[1] == M) {
                        break;
                    }
                    cnt++;
                }
            }
            result.append(cnt).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}