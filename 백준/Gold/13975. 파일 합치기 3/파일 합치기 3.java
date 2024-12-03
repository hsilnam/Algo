import java.io.*;
import java.util.*;

/*
입력
- T: 테스트 데이터
- K: 소설을 구성하는 장의 수 (3<=K<=1_000_000)
- 1장부터 K장까지 수록한 파일의 크기 (10_000초과하지 않음)

조건
- 두개의 파일을 필요한 비용 = 두 파일 크기의 합

풀이
- 우선순위큐를 사용
    - 합쳐진 값도 우선순위 큐에 포함히켜 최소값 갱신
- 합친 값이 int 범위 넘어가서 long타입으로 계산

출력
- 각 테스트데이터의 모든 장을 합치는데 필요한 최소비용

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (String temp : br.readLine().split(" ")) {
                long size = Long.parseLong(temp);
                pq.offer(size);
            }

            long sum = 0;
            while (pq.size() > 1) {
                long tmp = pq.poll() + pq.poll();
                sum += tmp;
                pq.offer(tmp);
            }
            answer.append(sum).append("\n");
        }


        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}