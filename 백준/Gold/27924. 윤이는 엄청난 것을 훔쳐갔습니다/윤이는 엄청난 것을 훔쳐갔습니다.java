import java.util.*;
import java.io.*;

/*
1. 입력값을 통해 그래프를 그린다
2. bfs로 a,b,c에서 각각 출발했을 때,
    각 정점들에 얼마만의 거리에 도착할 수 있는지 최단 거리 정보를 구한다
3. 리프 노드(d)인 정점들에 대하여,
    '(a와 d의 최단 거리) < (b와 d의 최단 거리) && (a와 d의 최단 거리) < (c와 d의 최단 거리)'를 만족하는 것이 하나라도 있으면,
    탈출이 가능하다
    - 문제 조건: 주어지는 a,b,c의 정점은 서로 다르다
        => a의 처음이 리프 노드라도 잡힐 일 없다
    - a가 가려는 탈출구노드에 대해,
        a의 최단 거리보다 b,c의 최단거리가 같거나 크면 반드시 잡히는 상황이 만들어진다
 */
public class Main {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[][] dists;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        dists = new int[3][N + 1];

        for (int i = 0; i < N - 1; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            graph[u].add(v);
            graph[v].add(u);
        }

        String[] temp = br.readLine().split(" ");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[1]);
        int c = Integer.parseInt(temp[2]);

        bfs(0, a);
        bfs(1, b);
        bfs(2, c);

        boolean isCatched = true;
        for (int i = 1; i < N + 1; i++) {
            if (isLeaf(i)) {
                int aDist = dists[0][i];
                int bDist = dists[1][i];
                int cDist = dists[2][i];

                if (aDist < bDist && aDist < cDist) {
                    isCatched = false;
                }
            }
        }

        bw.write((isCatched) ? "NO" : "YES");

        br.close();
        bw.close();
    }

    public static void bfs(int idx, int start) {
        Queue<int[]> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[N + 1];
        queue.offer(new int[]{start, 0});
        visited[0] = visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curNode = queue.poll();
            int cur = curNode[0];
            int dist = curNode[1];

            dists[idx][cur] = dist;

            int nDist = dist + 1;
            for (int next : graph[cur]) {
                if (visited[next]) {
                    continue;
                }
                queue.offer(new int[]{next, nDist});
                visited[next] = true;
            }

        }
    }

    public static boolean isLeaf(int tg) {
        return (graph[tg].size() <= 1);
    }
}