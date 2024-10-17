import java.util.*;
import java.io.*;

/*
플로이드
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX = 100_000_000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) {
                    continue;
                }
                graph[i][j] = 100_000_000;
            }
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);

            graph[a][b] = Math.min(graph[a][b], w);
        }

        for (int k = 1; k < N + 1; k++) { // 경유지
            for (int i = 1; i < N + 1; i++) { // 출발지
                for (int j = 1; j < N + 1; j++) { // 목적지
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                result.append((graph[i][j] != MAX) ? graph[i][j] : 0).append(" ");
            }
            result.append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}