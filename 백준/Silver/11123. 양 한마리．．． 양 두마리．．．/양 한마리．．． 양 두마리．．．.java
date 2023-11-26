import java.util.*;
import java.io.*;

/*
# -> 1
. -> 0
 */
public class Main {
    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String[] temp = br.readLine().split(" ");
            int W = Integer.parseInt(temp[0]); // 문제에서는 H
            int H = Integer.parseInt(temp[1]); // 문제에서는 W
            int[][] map = new int[W][H];
            for (int i = 0; i < W; i++) {
                char[] tempc = br.readLine().toCharArray();
                for (int j = 0; j < H; j++) {
                    if (tempc[j] == '#') {
                        map[i][j] = 1;
                    } else if (tempc[j] == '.') {
                        map[i][j] = 0;
                    }
                }
            }

            int cnt = 0;
            boolean[][] visited = new boolean[W][H];
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }
                    if (visited[i][j]) {
                        continue;
                    }
                    Queue<int[]> queue = new ArrayDeque();
                    visited[i][j] = true;
                    queue.offer(new int[]{i,j});
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int[] move:moves) {
                            int nx = move[0] + cur[0];
                            int ny = move[1] + cur[1];
                            if(nx < 0 || nx >= W || ny < 0 || ny >= H) {
                                continue;
                            }
                            if(visited[nx][ny]) {
                                continue;
                            }
                            if(map[nx][ny] != 1) {
                                continue;
                            }
                            queue.offer(new int[]{nx,ny});
                            visited[nx][ny] = true;
                        }
                    }
                    cnt += 1;
                }
            }
            result.append(cnt).append("\n");
        }
        System.out.println(result.toString());
    }
}