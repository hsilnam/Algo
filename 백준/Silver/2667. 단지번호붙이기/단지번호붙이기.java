import java.util.*;
import java.io.*;

/*
- bfs문제
 */
public class Main {
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];

        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
            }
        }

        boolean[][] visited = new boolean[N][N];
        ArrayList<Integer> cnts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j]) {
                   continue;
                }
                if (map[i][j] == '1') {
                    visited[i][j] = true;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});

                    int cnt = 0;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        cnt++;
                        for (int[] move : moves) {
                            int nx = move[0] + cur[0];
                            int ny = move[1] + cur[1];

                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                                continue;
                            }
                            if (visited[nx][ny]) {
                                continue;
                            }
                            if (map[nx][ny] == '0') {
                                continue;
                            }
                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                    cnts.add(cnt);
                }
            }
        }

        Collections.sort(cnts);

        StringBuilder result = new StringBuilder();
        result.append(cnts.size()).append("\n");
        for (int cnt : cnts) {
            result.append(cnt).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}