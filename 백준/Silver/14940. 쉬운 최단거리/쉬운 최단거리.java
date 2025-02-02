import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/*
입력
- 지도의 크기(세로,가로): N,M (2<=N,M<=1000)
- 지도 정보
    - 0: 갈 수 없는 땅
    - 1: 갈 수 있는 땅
    - 2: 목표지점 (단, 한개)

조건
- 모든 지점에 대하여 목표지점까지의 거리
- 오직 가로 세로만 움직일 수 있음

풀이
- 출발점부터 시작해서 bfs를 통해 거리 구하기
    - 출발점은 거리 0으로 표시
- 출력 시 1인데 방문하지 않은 곳 -1로 변경

출력
- 목표지점까지의 거리 출력
    - 원래 갈 수 없는 땅: 0
    - 원래 갈 수 있는 부분 중 도달할 수 없는 위치: -1
 */

public class Main {
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        int[][] map = new int[N][M];
        int[] tgIdx = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    tgIdx = new int[]{i, j};
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>(); // x, y
        boolean[][] visited = new boolean[N][M];

        queue.offer(new int[]{tgIdx[0], tgIdx[1]});
        visited[tgIdx[0]][tgIdx[1]] = true;
        map[tgIdx[0]][tgIdx[1]] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] move : moves) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];
                int ncnt = map[cur[0]][cur[1]] + 1;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                map[nx][ny] = ncnt;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    map[i][j] = -1;
                }
                answer.append(map[i][j]).append(" ");
            }
            answer.append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}