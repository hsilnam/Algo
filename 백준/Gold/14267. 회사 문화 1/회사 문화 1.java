import java.io.*;
import java.util.*;

/*
- graph, dp
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        long M = Long.parseLong(temp[1]);

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        int root = 0;
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int num = i + 1;
            int parent = Integer.parseInt(temp[i]);
            if (parent == -1) {
                root = num;
                continue;
            }
            graph[parent].add(num);
        }

        int[] values = new int[N + 1];
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            values[Integer.parseInt(temp[0])] += Integer.parseInt(temp[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                values[next] += values[cur];
                queue.offer(next);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            result.append(values[i]).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}