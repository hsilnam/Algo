import java.util.*;
import java.io.*;

/*
- 정렬
- Priority Queue에 Comparator 사용해서 정렬
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            pq.offer(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])});
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int[] cur = pq.poll();
            result.append(cur[0]).append(" ").append(cur[1]).append("\n");
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}