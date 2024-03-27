import java.util.*;
import java.io.*;

/*
- 우선순위큐 활용
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int abs1 = Math.abs(o1.intValue());
                int abs2 = Math.abs(o2.intValue());
                if (abs1 == abs2) {
                    return Integer.compare(o1.intValue(), o2.intValue());
                }
                return Integer.compare(abs1, abs2);
            }
        });

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                result.append((!pq.isEmpty()) ? pq.poll() : 0).append("\n");
            } else {
                pq.offer(num);
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}