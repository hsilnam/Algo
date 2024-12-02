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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);
        int X = Integer.parseInt(temp[3]);

        ArrayList<int[]>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            graph[Integer.parseInt(temp[0])].add(new int[]{Integer.parseInt(temp[1]), 1});
        }

        int[] mins = new int[N + 1];
        Arrays.fill(mins, Integer.MAX_VALUE);
        mins[X] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        queue.offer(new int[]{X, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (mins[cur[0]] < cur[1]) {
                continue;
            }
            for (int[] next : graph[cur[0]]) {
                if (mins[next[0]] > mins[cur[0]] + next[1]) {
                    mins[next[0]] = mins[cur[0]] + next[1];
                    queue.offer(new int[]{next[0], mins[next[0]]});
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (mins[i] == K) {
                answer.append(i).append("\n");
            }
        }


        bw.write((answer.length() > 0) ? answer.toString() : "-1");

        br.close();
        bw.close();
    }
}