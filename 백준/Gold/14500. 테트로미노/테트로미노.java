import java.util.*;
import java.io.*;

/*
- 구현, 브루트포스 문제
 */
public class Main {

    public static int N, M;
    public static int[][] map;
    public static boolean[][] visited;
    public static int max = 0;

    public static int[][] moves = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 북서남동

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // ㅓㅏㅗㅜ 형태를 제외한 도형에 대한 최댓값 찾는 dfs
                visited[i][j] = true;
                explore(i, j, map[i][j], 1);
                visited[i][j] = false;

                // ㅓㅏㅗㅜ형태를 이용한 도형에 대한 최댓값
                explore2(i, j);
            }
        }

        bw.write(Integer.toString(max));

        br.close();
        bw.close();
    }

    public static void explore(int x, int y, int sum, int cnt) {
        if (cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int[] move : moves) {
            int nx = move[0] + x;
            int ny = move[1] + y;

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            explore(nx, ny, sum + map[nx][ny], cnt + 1);
            visited[nx][ny] = false;
        }

    }

    public static void explore2(int x, int y) {
        for (int i = 0; i < 4; i++) {
            boolean isPossible = true;
            int sum = map[x][y];
            for (int j = 0; j < 3; j++) {
                int[] move = moves[(i + j) % 4];
                int nx = move[0] + x;
                int ny = move[1] + y;
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    isPossible = false;
                    break;
                }
                sum += map[nx][ny];
            }
            if (!isPossible) {
                continue;
            }
            max = Math.max(max, sum);
        }
    }
}