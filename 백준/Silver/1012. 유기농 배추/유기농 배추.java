import java.util.*;
import java.io.*;

public class Main {

    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder result = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] temp = br.readLine().split(" ");
            int M = Integer.parseInt(temp[0]); // 가로 길이 column
            int N = Integer.parseInt(temp[1]); // 세로 길이 row
            int K = Integer.parseInt(temp[2]);

            int[][] map = new int[N][M];
            for (int k = 0; k < K; k++) {
                temp = br.readLine().split(" ");
                int y = Integer.parseInt(temp[0]);
                int x = Integer.parseInt(temp[1]);
                map[x][y] = 1;
            }


            int cnt = 0;
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        cnt++;

                        // 연결된 곳 조사
                        visited[i][j] = true;
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.offer(new int[]{i, j});

                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();
                            for (int[] move : moves) {
                                int nx = cur[0] + move[0];
                                int ny = cur[1] + move[1];

                                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                                    continue;
                                }
                                if (map[nx][ny] == 0) {
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
            }
            result.append(cnt).append("\n");
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}