import java.io.*;
import java.util.*;

/*
최소힙(우선순위큐) 사용
- 최소로 합치려면 합치는 순서가 최소여야한다
- 하지만 합치는 과정에서 이미 합쳐진 값들 중에서도 최소를 묶어야 그 결과값도 최소가 된다
 => 합쳐진 값도 다시 최소힙에 넣어두고 계산해야한다


[오답노트]
틀림(4%)
- 전체 최소값을 더할 때, 합친 결과값을 PQ에 넣어놓고 PQ의 최소값을 전체 최소값에 더함
    - 현재 들어간 합 결과값이 PQ에서 최소값일 거란 보장이없다. 다른 최소값이 나오면 전체 최소값에 영향을 준다
    => 전체 최소값을 더할 때,
        합친 결과값을 전체 최소값에 더해놓고 합친 결과값을 PQ에 넣어야한다
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> minPQ = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            minPQ.offer(Integer.parseInt(br.readLine()));
        }


        int minSum = 0;
        while (minPQ.size() > 1) {
            int sum = minPQ.poll() + minPQ.poll();
            minSum += sum;
            minPQ.offer(sum);

        }

        bw.write(Integer.toString(minSum));

        br.close();
        bw.close();
    }
}