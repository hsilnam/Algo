import java.util.*;
import java.io.*;

/*
- 브루트포스 문제
- (50-8)*(50-8)*8*8*2 => 최대 225,792
    - (M*N를 8*8로 잘라서 검사하는 경우의 수)* (B/W로 시작하는 경우)
1. M*N을 8*8로 잘라서 모든 경우를 검사한다
    - B로 시작하는 경우와, W로 시작하는 경우에 대해서 바꿔야하는 개수를 센다
        - 이때, 전체 최소개수보다 현재 세는 개수가 같거나 많아지면 최소값 후보에서 제외되므로 검사를 멈춘다
2. 바꿔야하는 최소 개수를 프린트한다
 */
public class Main {
    public static int N, M;
    public static char[][] map;
    public static int min = Integer.MAX_VALUE;
    public static char[] type = {'W', 'B'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] tempC = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tempC[j];
            }
        }


        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // W로 시작
                explore(i, j, 0);
                // B로 시작
                explore(i, j, 1);
            }
        }

        bw.write(Integer.toString(min));

        br.close();
        bw.close();
    }

    public static void explore(int x, int y, int typeIdx) {
        int cnt = 0;
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                if (min <= cnt) {
                    return;
                }
                if (map[i][j] != type[typeIdx]) {
                    cnt++;
                }
                if (j != y + 7) {
                    typeIdx = (typeIdx + 1) % 2;
                }
            }
        }
        min = cnt;
    }
}