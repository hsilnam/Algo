import java.util.*;
import java.io.*;

/*
맨 위, 왼쪽부터 시작
로봇은 한 번에 오른쪽/아래 칸으로 이동 가능 (대각선 방향X)
격자에 ㅇ표시된 칸은 반드시 지나가야
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);

        int startX = 0;
        int startY = 0;

        int midX = (K == 0) ? 0 : (K - 1) / M;
        int midY = (K == 0) ? 0 : (K - 1) % M;

        int endX = N - 1;
        int endY = M - 1;


        int w1 = midX - startX + 1;
        int h1 = midY - startY + 1;

        int w2 = endX - midX + 1;
        int h2 = endY - midY + 1;

        int W = Math.max(w1, w2);
        int H = Math.max(h1, h2);

        int[][] map = new int[W][H];
        Arrays.fill(map[0], 1);
        for (int i = 0; i < W; i++) {
            map[i][0] = 1;
        }

        for (int i = 1; i < W; i++) {
            for (int j = 1; j < H; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1];
            }

        }

        int result = map[w1 - 1][h1 - 1] * map[w2 - 1][h2 - 1];
        System.out.println(result);
    }
}