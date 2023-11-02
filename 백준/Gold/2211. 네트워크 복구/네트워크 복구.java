import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int num;
        int w;

        public Node(int num, int w) {
            this.num = num;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int A = Integer.parseInt(temp[0]) - 1;
            int B = Integer.parseInt(temp[1]) - 1;
            int C = Integer.parseInt(temp[2]);
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        Queue<Node> queue = new ArrayDeque<>();
        Node[] mins = new Node[N];
        for (int i = 1; i < N; i++) {
            mins[i] = new Node(-1, Integer.MAX_VALUE); // 바로 연결되는 노드, 전체 weight값
        }

        queue.offer(new Node(0, 0));
        mins[0] = new Node(0, 0);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

//            System.out.println(cur);
            for (Node next : graph[cur.num]) {
//                System.out.println((cur.num+1)+" "+(next.num+1)+" : "+mins[next.num].w + " vs " +mins[cur.num].w +" " +next.w);

                if (mins[next.num].w > mins[cur.num].w + next.w) {
                    mins[next.num].num = cur.num;
                    mins[next.num].w = mins[cur.num].w + next.w;
                    queue.offer(next);
                }
            }
        }


//        int cnt = 0;
        StringBuilder nodeInfo = new StringBuilder();
        for (int i = 1; i < N; i++) { // root 제외하고 (첫번째)
//            if (mins[i].w != Integer.MAX_VALUE) {
//                cnt += 1;
                nodeInfo.append(i + 1).append(" ").append(mins[i].num + 1).append("\n");
//            }
        }
        StringBuilder result = new StringBuilder();
        System.out.println(result.append(N-1).append("\n").append(nodeInfo).toString());
/*        int cnt = 0;
        StringBuilder nodeInfo = new StringBuilder();
        for (int i = 1; i < N; i++) { // root 제외하고 (첫번째)
            if (mins[i].w != Integer.MAX_VALUE) {
                cnt += 1;
                nodeInfo.append(i + 1).append(" ").append(mins[i].num + 1).append("\n");
            }
        }
        StringBuilder result = new StringBuilder();
        System.out.println(result.append(cnt).append("\n").append(nodeInfo).toString());*/
//        System.out.println(Arrays.toString(mins));
    }
}