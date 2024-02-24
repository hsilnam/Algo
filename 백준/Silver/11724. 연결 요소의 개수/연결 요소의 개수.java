import java.util.*;
import java.io.*;

/*
연결요소: 분리되어있는 그래프
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int next : graph[cur]) {
                        if (visited[next]) {
                            continue;
                        }
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.close();
    }
}