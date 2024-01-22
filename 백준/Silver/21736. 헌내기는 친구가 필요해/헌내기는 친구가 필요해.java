import java.util.*;
import java.io.*;

public class Main {

    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int startx = 0, starty = 0;
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] tempc = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tempc[j];
                if (tempc[j] == 'I') {
                    startx = i;
                    starty = j;
                }
            }
        }

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[startx][starty] = true;
        queue.offer(new int[]{startx, starty});

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] move : moves) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 'X') {
                    continue;
                }

                if (map[nx][ny] == 'P') {
                    cnt += 1;
                }

                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        bw.write((cnt > 0) ? Integer.toString(cnt) : "TT");

        br.close();
        bw.close();
    }
}