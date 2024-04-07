import java.util.*;
import java.io.*;

/*
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            pq.offer(new int[]{x, y});
        }

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            result.append(cur[0]).append(" ").append(cur[1]).append("\n");
        }

        bw.write(result.toString());
        
        br.close();
        bw.close();
    }
}