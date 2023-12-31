import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int pass = 0;

    static boolean isPossible = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        visited = new boolean[N][M];
        for (int c = 0; c < M; c++) {
            if (isPossible) {
                break;
            }
            if (map[0][c] == pass && !visited[0][c]) {
                bfs(new int[]{0, c});
            }
        }

        bw.write((isPossible) ? "YES" : "NO");

        br.close();
        bw.close();
    }

    public static void bfs(int[] start) {
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new ArrayDeque();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N - 1) { // 끝에 도달할경우
                isPossible = true;
                break;
            }

            for (int[] move : moves) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] != pass) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}