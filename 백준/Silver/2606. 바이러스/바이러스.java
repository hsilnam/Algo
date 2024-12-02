import java.io.*;
import java.util.*;

/*
입력
- N: 컴퓨터수 (0<=N<=100)
- M: 컴퓨터 쌍의 수
- 연결되어있는 컴퓨터 번호 쌍

조건
- 컴퓨터 번호 1부터 시작
- 양방향

논리
- bfs 사용
- 새로운 곳 방문할 때마다 1 카운트 해서 컴퓨터 세기
- 컴퓨터 번호 맞춰주기

출력
- 1번 컴퓨터가 바이러스 걸렸을 때,
    바이러스에 걸리게 되는 컴퓨터 수

 */

public class Main {

    static final int[][] moves = {
            {-1, -2}, {-2, -1}, {-1, 2}, {-2, 1},
            {1, 2}, {2, 1}, {1, -2}, {2, -1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N + 1]; // 컴퓨터 번호 맞춰주기
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1]; // 컴퓨터 번호 맞춰주기
        queue.offer(1);
        visited[1] = true;

        int cnt = 0; // 본인 제외
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (visited[next]) {
                    continue;
                }
                queue.offer(next);
                visited[next] = true;
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.flush();
        bw.close();
    }
}