import java.util.*;
import java.io.*;

/*
- 트리의 부모: bfs를 돌렸을 때 바로 전단계가 부모
- bfs이기 때문에 각 레벨별로 순차적으로 검사할 수 있다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        // bfs
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        boolean[] visited = new boolean[N + 1];
        visited[0] = visited[1] = true;


        int[] roots = new int[N + 1];
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (visited[next]) {
                    continue;
                }
                roots[next] = cur;
                visited[next] = true;
                queue.offer(next);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < N + 1; i++) {
            result.append(roots[i]).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}