import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static HashMap<Character, Integer> powerInfo;

    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        M = Integer.parseInt(temp[0]);
        N = Integer.parseInt(temp[1]);

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] tempc = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tempc[j];
            }
        }

        powerInfo = new HashMap<>();
        powerInfo.put('W', 0);
        powerInfo.put('B', 0);

        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(new int[]{i, j});
                }
            }
        }

        bw.write(powerInfo.get('W') + " " + powerInfo.get('B'));

        br.close();
        bw.close();
    }

    public static void bfs(int[] start) {
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        char tg = map[start[0]][start[1]];

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            cnt += 1;

            for (int[] move : moves) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] != tg) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        powerInfo.put(tg, powerInfo.get(tg) + cnt * cnt);
    }
}