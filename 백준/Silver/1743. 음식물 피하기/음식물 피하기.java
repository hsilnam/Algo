import java.util.*;
import java.io.*;


public class Main {

    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 사방탐색

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);

        ArrayList<int[]> checks = new ArrayList<>(); // 음식물 위치 부터 체크
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]) - 1; // 0으로 맞추기
            int y = Integer.parseInt(temp[1]) - 1; // 0으로 맞추기
            map[x][y] = 1; // 음식물
            checks.add(new int[]{x, y});
        }

        int maxCnt = 0;
        for (int[] check : checks) {
            int x = check[0];
            int y = check[1];
            if (visited[x][y]) {
                continue;
            }

            Queue<int[]> queue = new ArrayDeque();
            queue.offer(check);
            visited[x][y] = true;
            int cnt = 1; // 자기자신 포함

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
                    if (map[nx][ny] == 0) {
                        continue;
                    }
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
        }
        System.out.println(maxCnt);
    }
}