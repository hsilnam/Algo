import java.io.*;
import java.util.*;

/*
최대힙(우선순위큐)를 사용
- 뿅망치를 사용횟수만큼 반복하여 최대키를 절반값으로 만들어 다시 힙에 넣는다
    - 이때 최대값이 자신의 키보다 작으면 멈춘다
    - 단 키가 1이면 절반으로 나누지 않는다
- 최대값이 자신의 키보다 작으면 "YES"아니면 "NO"를 출력하고
    - YES인경우, 뿅망치 횟수를 출력한다
    - NO인 경우, 가장 큰키를 출력한다
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int H = Integer.parseInt(temp[1]);
        int T = Integer.parseInt(temp[2]);

        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int cnt;
        for (cnt = 0; cnt < T; cnt++) {
            if (H > pq.peek()) {
                break;
            }
            pq.offer((pq.peek() != 1) ? pq.poll() / 2 : 1);
        }

        StringBuilder answer = new StringBuilder();

        answer.append((H > pq.peek()) ? "YES" : "NO").append("\n")
                .append((H > pq.peek() ? cnt : pq.peek()));

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}