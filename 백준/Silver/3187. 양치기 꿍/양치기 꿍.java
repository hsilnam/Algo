import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static char[][] map;
    private static boolean[][] visited;
    private static int R, C;
    private static int sheepCount, wolfCount; // 임시 카운터

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] dimensions = br.readLine().split(" ");
        R = Integer.parseInt(dimensions[0]);
        C = Integer.parseInt(dimensions[1]);

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int totalSheep = 0;
        int totalWolves = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] != '#') {
                    sheepCount = 0;
                    wolfCount = 0;

                    dfs(i, j);

                    // 양과 늑대의 수에 따라 생존자 결정
                    if (sheepCount > wolfCount) {
                        totalSheep += sheepCount;
                    } else {
                        totalWolves += wolfCount;
                    }
                }
            }
        }

        bw.write(totalSheep + " " + totalWolves);
        br.close();
        bw.close();
    }

    private static void dfs(int x, int y) {
        // 방문 처리
        visited[x][y] = true;

        // 현재 위치의 양과 늑대 개수 카운팅
        if (map[x][y] == 'k') {
            sheepCount++;
        } else if (map[x][y] == 'v') {
            wolfCount++;
        }

        // 4방향 탐색
        for (int[] direction : DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && map[nx][ny] != '#') {
                dfs(nx, ny);
            }
        }
    }
}