import java.io.*;
import java.util.*;

/*
최대힙(우선순위큐 이용)
- 자연수가 나오면, 그 자연수만큼 선물 추가
- 0이나오면, 힙이 비어있으면 -1, 아니면 최대값 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            if (Integer.parseInt(temp[0]) == 0) {
                answer.append((pq.isEmpty()) ? -1 : pq.poll()).append("\n");
                continue;
            }
            for (int j = 1; j <= Integer.parseInt(temp[0]); j++) {
                pq.offer(Integer.parseInt(temp[j]));
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}