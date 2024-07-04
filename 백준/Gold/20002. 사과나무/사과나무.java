import java.io.*;
import java.util.*;

/*
정사각형의 누적합 이용
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1] + Integer.parseInt(temp[j - 1]) - map[i - 1][j - 1];
            }
        }

        int max = -1;
        for (int s = 1; s < N + 1; s++) {
            for (int i = s; i < N + 1; i++) {
                for (int j = s; j < N + 1; j++) {
                    max = Math.max(map[i][j] - map[i - s][j] - map[i][j - s] + map[i - s][j - s], max);
                }
            }
        }
        bw.write(Integer.toString(max));

        br.close();
        bw.close();
    }
}