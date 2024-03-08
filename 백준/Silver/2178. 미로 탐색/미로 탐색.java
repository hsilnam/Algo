import java.util.*;
import java.io.*;

public class Main {
    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() { // x, y, cnt
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        boolean[][] visited = new boolean[N][M];
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        int minCnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            if (x == N - 1 && y == M - 1) {
                minCnt = cnt;
                break;
            }

            int nCnt = cnt + 1;
            for (int[] move : moves) {
                int nx = move[0] + x;
                int ny = move[1] + y;
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                queue.offer(new int[]{nx, ny, nCnt});
                visited[nx][ny] = true;
            }

        }

        bw.write(Integer.toString(minCnt));

        br.close();
        bw.close();
    }
}