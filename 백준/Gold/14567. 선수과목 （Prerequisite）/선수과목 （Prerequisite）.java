import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]) - 1;
            int to = Integer.parseInt(temp[1]) - 1;
            graph[from].add(to);
        }

        int[] cnts = new int[N];

        for (int i = 0; i < N; i++) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{i, 1});
            cnts[i] = Math.max(cnts[i], 1);
            while (!queue.isEmpty()) {
                int[] curInfo = queue.poll();
                int cur = curInfo[0];
                int cnt = curInfo[1];

                int nCnt = cnt + 1;
                for (int next : graph[cur]) {
                    if (cnts[next] < nCnt) {
                        cnts[next] = nCnt;
                        queue.offer(new int[]{next, nCnt});
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int cnt : cnts) {
            result.append(cnt).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}