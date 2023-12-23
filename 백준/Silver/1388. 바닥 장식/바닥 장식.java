import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tempS = br.readLine().split(" ");
        int N = Integer.parseInt(tempS[0]);
        int M = Integer.parseInt(tempS[1]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] tempC = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (tempC[j] == '-') {
                    map[i][j] = 0; // 가로
                } else if (tempC[j] == '|') {
                    map[i][j] = 1; // 세로
                }
            }
        }

        int totalCnt = 0;
        // 가로 구하기
        for (int i = 0; i < N; i++) {
            int sameCnt = 0;
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    sameCnt += 1;
                }
                if (map[i][j] == 1 || j == M - 1) {
                    if (sameCnt > 0) {
                        totalCnt += 1;
                    }
                    sameCnt = 0;
                }
            }
        }

        // 세로 구하기
        for (int j = 0; j < M; j++) {
            int sameCnt = 0;
            for (int i = 0; i < N; i++) {
                if (map[i][j] == 1) {
                    sameCnt += 1;
                }
                if (map[i][j] == 0 || i == N - 1) {
                    if (sameCnt > 0) {
                        totalCnt += 1;
                    }
                    sameCnt = 0;
                }
            }
        }


        System.out.println(totalCnt);
    }

}