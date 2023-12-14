import java.util.*;
import java.io.*;

public class Main {
    static int[][] moves = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    static int N, M;
    static int[][] map;
    static int maxDst;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            int j = 0;
            for (char c : br.readLine().toCharArray()) {
                if (c == 'W') { // 바다
                    map[i][j] = 0;
                } else if (c == 'L') { // 지상
                    map[i][j] = 1;
                }
                j++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    getShortDst(i, j);
                }
            }
        }

        System.out.println(maxDst);
    }

    static public void getShortDst(int x, int y) {
        int[][] shortDst = new int[N][M]; // 최단거리
        for (int i = 0; i < N; i++) {
            Arrays.fill(shortDst[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});
        shortDst[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cCnt = cur[2];

            int nCnt = cCnt + 1;
            for (int[] move : moves) {
                int nx = move[0] + cx;
                int ny = move[1] + cy;
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                if (shortDst[nx][ny] == -1 || shortDst[nx][ny] > nCnt) {
                    maxDst = Math.max(maxDst, nCnt);
                    shortDst[nx][ny] = nCnt;
                    queue.offer(new int[]{nx, ny, nCnt});
                }
            }
        }
    }
}