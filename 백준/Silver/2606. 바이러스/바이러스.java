import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]) - 1;
            int b = Integer.parseInt(temp[1]) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = true;
        queue.offer(0);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next:graph[cur]) {
                if(visited[next]) {
                    continue;
                }
                queue.offer(next);
                visited[next] = true;
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.close();
    }
}