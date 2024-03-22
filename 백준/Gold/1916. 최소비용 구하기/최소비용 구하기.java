import java.util.*;
import java.io.*;

/*
- 다익스트라 (Priority Queue 이용)
- 단방향그래프
 */
public class Main {

    public static class Node {
        int num;
        long weight;

        public Node(int num, long weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]) - 1;
            int to = Integer.parseInt(temp[1]) - 1;
            long weight = Long.parseLong(temp[2]);
            graph[from].add(new Node(to, weight));
        }

        String[] temp = br.readLine().split(" ");
        int start = Integer.parseInt(temp[0]) - 1;
        int end = Integer.parseInt(temp[1]) - 1;

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Long.compare(o1.weight, o2.weight);
            }
        });
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (dist[cur.num] < cur.weight) { // 이미 처리된 노드
                continue;
            }
            for (Node next : graph[cur.num]) {
                if (dist[next.num] > dist[cur.num] + next.weight) { // 업데이트
                    dist[next.num] = dist[cur.num] + next.weight;
                    queue.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        bw.write(Long.toString(dist[end]));

        br.close();
        bw.close();
    }

}