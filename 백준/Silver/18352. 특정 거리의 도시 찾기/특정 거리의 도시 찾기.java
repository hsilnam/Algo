import java.io.*;
import java.util.*;

/*
입력
N: 도시의 개수 (2 ≤ N ≤ 300_000)
- M: 도로의 개수 (1 ≤ M ≤ 1_000_000)
- K: 거리정보 (1 ≤ K ≤ 300_000)
- X: 출발 도시의 번호 (1 ≤ X ≤ N)
- A->B: 단방향 도로

조건
- 단방향, 모든 도로의 거리는 1

논리
- 다익스트라 이용

출력
- 최단 거리가 K인 모든 도시의 번호, 없으면 -1
 */

public class Main {
    public static int N;
    public static ArrayList<Integer>[] graph;
    public static ArrayList<Integer> dist = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);
        int X = Integer.parseInt(temp[3]);

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            graph[a].add(b);
        }

        bfs(X, K);
//        dijkstra(X);

        Collections.sort(dist);
        StringBuilder answer = new StringBuilder();
        for (int d : dist) {
            answer.append(d).append("\n");
        }

        if (answer.length() == 0) {
            bw.write(Integer.toString(-1));
        } else {
            bw.write(answer.toString());
        }


        br.close();
        bw.close();
    }

    public static void bfs(int start, int k) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int count = 0;
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            if (count == k) {
                while (!queue.isEmpty()) {
                    dist.add(queue.poll());
                }
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            count++;
        }
    }

//    public static void dijkstra(int start) {
//        dist = new int[N + 1];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//
//        PriorityQueue<Node> pq = new PriorityQueue<>();
//        pq.offer(new Node(start, 0));
//        dist[start] = 0;
//
//        while (!pq.isEmpty()) {
//            Node cur = pq.poll();
//            if (dist[cur.num] < cur.dist) {
//                continue;
//            }
//            for (int next : graph[cur.num]) {
//                if (dist[next] > dist[cur.num] + 1) {
//                    dist[next] = dist[cur.num] + 1;
//                    pq.offer(new Node(next, dist[next]));
//                }
//            }
//        }
//    }

    public static class Node implements Comparable<Node> {
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}