import java.io.*;
import java.util.*;

/*
dfs 사용
 */
public class Main {
    static int[][] map;
    static boolean[][] visited;
    static boolean isOk = false;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        explore(0, 0);


        bw.write((isOk) ? "HaruHaru" : ("Hing"));
        br.close();
        bw.close();
    }

    public static void explore(int x, int y) {
        if (!isIn(x, y)) {
            return;
        }
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        if (map[x][y] == -1) {
            isOk = true;
            return;
        }

        explore(map[x][y] + x, y);
        explore(x, map[x][y] + y);
    }

    public static boolean isIn(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

}