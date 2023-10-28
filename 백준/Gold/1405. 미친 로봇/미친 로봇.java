import java.util.*;
import java.io.*;

public class Main {
    final static int maxLen = 100;
    static int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // EWSN
    static int N;
    static double[] percentages;
    static boolean[][] visited = new boolean[maxLen][maxLen];
    static double result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);

        percentages = new double[4];
        for (int i = 1; i < 5; i++) {
            percentages[i - 1] = Double.parseDouble(temp[i]) * 0.01;
        }

        visited[maxLen / 2][maxLen / 2] = true;
        dfs(maxLen / 2, maxLen / 2, 0, 1.0);

        System.out.println(result);

        br.close();
    }

    static public void dfs(int x, int y, int cnt, double total) {
        if (cnt == N) {
            result += total;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(percentages[i] == 0.0) {
                continue;
            }

            int[] move = moves[i];
            int nx = x + move[0];
            int ny = y + move[1];
            if (nx < 0 || nx >= maxLen || ny < 0 || ny >= maxLen) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }

            visited[nx][ny] = true; // 방문한 경우
            dfs(nx, ny, cnt + 1, total * percentages[i]);
            visited[nx][ny] = false; // 방문 안했을 경우
        }
    }

}