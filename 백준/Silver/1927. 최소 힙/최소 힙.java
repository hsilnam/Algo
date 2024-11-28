import java.io.*;
import java.util.*;

/*
최소힙(우선순위큐 이용)
- 자연수가 나오면, 힙에 추가
- 0이나오면, 힙이 비어있으면 0, 아니면 최소값 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                answer.append((pq.isEmpty()) ? 0 : pq.poll()).append("\n");
                continue;
            }
            pq.offer(x);
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}