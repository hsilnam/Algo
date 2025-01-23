import java.io.*;
import java.util.*;

/*
입력
- N: 종이의 한 변의 길이 (2, 4, 8, 16, 32, 64, 128)
- 색 정보
    - 하얀색: 0
    - 파란색: 1

조건
- 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눔
- 자르기 반복종료 조건:
    - 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있음
    - 하나의 정사각형 칸이 되어 더 이상 자를 수 없음

풀이
- 누적합 구하기
- 재귀로 반으로 나누면서 체크하기
    - 사각형의 누적합이 사각형의 너비면 하얀색 카운트+1,  0이면 파란색 카운트+1 하고 더이상 자르지 않는다

출력
- 잘라진 햐얀색 색종이의 개수, 파란색 색종이의 개수
 */

public class Main {
    public static int wCnt, bCnt;
    public static int[][] mapsum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        mapsum = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                mapsum[i + 1][j + 1] = map[i][j] + mapsum[i][j + 1] + mapsum[i + 1][j] - mapsum[i][j];
            }
        }

        divide(0, 0, N);

        bw.write(wCnt + "\n" + bCnt + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void divide(int r, int c, int size) {
        int sum = mapsum[r + size][c + size] - mapsum[r][c + size] - mapsum[r + size][c] + mapsum[r][c];
        if (sum == 0) {
            wCnt++;
            return;
        } else if (sum == size * size) {
            bCnt++;
            return;
        }

        int newSize = size / 2;
        divide(r, c, newSize);
        divide(r, c + newSize, newSize);
        divide(r + newSize, c, newSize);
        divide(r + newSize, c + newSize, newSize);
    }
}