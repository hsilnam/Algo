import java.util.*;
import java.io.*;

/*
A에서 B로 가는 최단거리: bfs
 */
public class Main {
    public static final int maxSize = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        int[] mins = new int[maxSize + 1];
        Arrays.fill(mins, Integer.MAX_VALUE);
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        mins[N] = 0;
        queue.offer(new int[]{N, 0}); // idx, weight

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cIdx = cur[0];
            int cWeight = cur[1];

            if (cIdx == K) {
                cnt++;
                continue;
            }

            if (isIn(cIdx * 2)
                    && mins[cIdx * 2] >= cWeight + 1) {
                mins[cIdx * 2] = cWeight + 1;
                queue.offer(new int[]{cIdx * 2, mins[cIdx * 2]});
            }

            if (isIn(cIdx + 1)
                    && mins[cIdx + 1] >= cWeight + 1) {
                mins[cIdx + 1] = cWeight + 1;
                queue.offer(new int[]{cIdx + 1, mins[cIdx + 1]});
            }
            if (isIn(cIdx - 1)
                    && mins[cIdx - 1] >= cWeight + 1) {
                mins[cIdx - 1] = cWeight + 1;
                queue.offer(new int[]{cIdx - 1, mins[cIdx - 1]});
            }
        }

        bw.write(mins[K] +"\n" + cnt);

        br.close();
        bw.close();
    }

    public static boolean isIn(int idx) {
        return (idx >= 0 && idx <= maxSize);
    }
}