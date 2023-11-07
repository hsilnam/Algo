import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int num;
        long weight;

        public Node(int num, long weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", weight=" + weight +
                    '}';
        }
    }

    static int N, P;
    static int X, Z;

    static ArrayList<Node>[] graph;
    static int[] arr;
    static boolean[] permVisited;
    static int[] permResult = new int[3];
    static long resultMin = Long.MAX_VALUE;
    static long[][] minDsts;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]) - 1;
            int v = Integer.parseInt(temp[1]) - 1;
            int w = Integer.parseInt(temp[2]);
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        temp = br.readLine().split(" ");
        X = Integer.parseInt(temp[0]) - 1; // 출발
        Z = Integer.parseInt(temp[1]) - 1; // 도착

        P = Integer.parseInt(br.readLine());
        arr = new int[P+1]; // 지나가는 점들
        arr[0] = X;
        temp = br.readLine().split(" ");
        for (int i = 1; i < P+1; i++) {
            arr[i] = Integer.parseInt(temp[i-1]) - 1;
        }

        minDsts = new long[P+1][N]; // start, end
        for (int i = 0; i < P+1; i++) { // 첫번쨰 포함
            Arrays.fill(minDsts[i], Long.MAX_VALUE);
            minDsts[i][arr[i]] = 0;
        }

        for (int i = 0; i < P+1; i++) {
            minDsts[i] = dijkstra(arr[i]);
        }

        permVisited = new boolean[P+1];
        perm(0);
        resultMin = (resultMin == Long.MAX_VALUE) ? -1 : resultMin;
        System.out.println(resultMin);
    }

    static public void perm(int idx) {
        if(idx == 3) {
//            System.out.println(Arrays.toString(permResult));


            long dst = minDsts[0][arr[permResult[0]]]
                        +minDsts[permResult[0]][arr[permResult[1]]]
                        +minDsts[permResult[1]][arr[permResult[2]]]
                        +minDsts[permResult[2]][Z];

            if(minDsts[0][arr[permResult[0]]] == Long.MAX_VALUE ||
                    minDsts[permResult[0]][arr[permResult[1]]] == Long.MAX_VALUE ||
                    minDsts[permResult[1]][arr[permResult[2]]] == Long.MAX_VALUE ||
                    minDsts[permResult[2]][Z] == Long.MAX_VALUE) {
                return;
            }
            resultMin = Math.min(dst, resultMin);
            return;
        }
        for (int i = 1; i < P+1; i++) {
            if(!permVisited[i]) {
                permVisited[i] = true;
                permResult[idx] = i;
                perm(idx+1);
                permVisited[i] = false;
            }
        }
    }
    static public long[] dijkstra(int start) {
        long[] mins = new long[N];
        Arrays.fill(mins, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        mins[start] = 0;

        long min = Long.MAX_VALUE;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (mins[cur.num] < cur.weight) { // ?
                continue;
            }
            for (Node nextNode : graph[cur.num]) {
                if (mins[nextNode.num] > nextNode.weight + mins[cur.num]) { // 원래 가지고 있던 nextNode weight보다, 현재의 위치를 통해 nextNode에 도착하는 경우를 비교
                    mins[nextNode.num] = nextNode.weight + mins[cur.num];
                    pq.offer(new Node(nextNode.num, mins[nextNode.num]));
                }
            }
        }
        return mins;
    }
}