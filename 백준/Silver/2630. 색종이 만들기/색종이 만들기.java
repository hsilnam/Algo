import java.util.*;
import java.io.*;

/*
1. 2차원 누적합 구하기
2. 분할정복을 통해(재귀로 구현) 텀색
    - 현재 검사하는 한 변의 길이가 n일 경우,
    - 해당 부분 합이
        - n*n(너비)인 경우 검은색 카운트 1 올리고 현재 단계 탐색 멈추기 (종료조건)
        - 0인 경우 하얀색 카운트 1 올리고 현재 단계 탐색 멈추기 (종료조건)
    - n이 0이면 탐색 멈추기 (종료조건)
 */
public class Main {
    static int whiteCnt, blueCnt; // 색깔 표시: white 0, blue 1
    static int[][] sumMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        sumMap = new int[N][N];
        // get input
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                sumMap[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 누적합 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                sumMap[i][j] += sumMap[i][j - 1];
            }
        }
        for (int j = 0; j < N; j++) {
            for (int i = 1; i < N; i++) {
                sumMap[i][j] += sumMap[i - 1][j];
            }
        }

        explore(0, 0, N - 1, N - 1, N);

        bw.write(whiteCnt+"\n"+blueCnt);
        br.close();
        bw.close();
    }

    static public void explore(int sx, int sy, int ex, int ey, int n) {
        int sum = sumMap[ex][ey];
        if(sx != 0) {
            sum -= sumMap[sx - 1][ey];
        }
        if(sy != 0) {
            sum -= sumMap[ex][sy - 1];
        }
        if(sx != 0 && sy != 0) {
            sum += sumMap[sx - 1][sy - 1];
        }

        if (sum == n * n) {
            blueCnt++;
            return;
        } else if (sum == 0) {
            whiteCnt++;
            return;
        }

        int mx = sx + n / 2;
        int my = sy + n / 2;

        if (n == 1) {
            return;
        }
        explore(sx, sy, mx - 1, my - 1, n / 2);
        explore(sx, my, mx - 1, ey, n / 2);
        explore(mx, sy, ex, my - 1, n / 2);
        explore(mx, my, ex, ey, n / 2);
    }
}