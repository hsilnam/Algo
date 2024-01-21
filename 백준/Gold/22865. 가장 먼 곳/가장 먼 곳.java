import java.util.*;
import java.io.*;

/*
1. 멤버 A,B,C의 사는 곳의 번호를 저장한다
2. ArrayList[]를 이용하여 양방향 그래프 정보를 저장한다
3. A,B,C를 지점으로 하여 다익스트라를 이용해 검사한다
4. 다익스트라로 구한 결과를 이용하여 가장 가까운 거리(Min)를 구한다
4. 구해진 가장 가까운 거리(Min)를 이용하여 가장 먼거리(Max)의 땅번호를 구한다
    - 거리가 같으면 번호는 작은 걸로 선택하기
(O(N))

---
[실패들]
1. 1%(시간초과)
    - 원인: '모든 장소를 시작 지점으로 하여 다익스트라를 이용해 검사' (O(N^2))
 */
public class Main {
    static int N;
    static ArrayList<Node>[] graph;
    static class Node {
        int num;
        int len;

        public Node(int num, int len) {
            this.num = num;
            this.len = len;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] members = new int[N];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) {
            members[i] = Integer.parseInt(temp[i]) - 1; // 기준 0으로 맞추기
        }

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]) - 1; // 기준 0으로 맞추기
            int b = Integer.parseInt(temp[1]) - 1; // 기준 0으로 맞추기
            int l = Integer.parseInt(temp[2]);

            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }


        int[][] dsts = new int[3][N];
        for (int i = 0; i < 3; i++) {
            dsts[i] = dijkstra(members[i]);
        }

        int num = -1;
        int max = -1;
        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                min = Math.min(min, dsts[j][i]);
            }

            if(max < min) {
                num = i;
                max = min;
            }
        }
        bw.write(Integer.toString(num + 1)); // 번호 1을 기준으로 맞춰주기

        br.close();
        bw.close();
    }

    static public int[] dijkstra(int start) {
        int[] mins = new int[N];
        Arrays.fill(mins, Integer.MAX_VALUE);
        mins[start] = 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node next : graph[cur.num]) {
                if (mins[next.num] > mins[cur.num] + next.len) {
                    mins[next.num] = mins[cur.num] + next.len;
                    queue.offer(new Node(next.num, mins[next.num]));
                }
            }
        }
        return mins;
    }
}