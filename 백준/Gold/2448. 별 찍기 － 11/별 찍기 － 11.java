import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = (int) (Math.log(N / 3) / Math.log(2));
        int M = getM(K);

        int[][] map = new int[N][M];

        // init
        int mid = M / 2;
        int[][] starPosInit = {
                {0, mid},
                {1, mid - 1}, {1, mid + 1},
                {2, mid - 2}, {2, mid - 1}, {2, mid}, {2, mid + 1}, {2, mid + 2}
        };
        for (int[] starPos : starPosInit) {
            map[starPos[0]][starPos[1]] = 1;
        }

        // get map
        for (int k = 1; k <= K; k++) {
            int prevN = 3 * (int) Math.pow(2, k - 1);
            int prevM = getM(k - 1);

            int prevStartX = 0;
            int prevStartY = mid - prevM / 2;

            int x = prevN;
            for (int i = prevStartX; i < prevStartX + prevN; i++) {
                int y1 = mid - prevM;
                int y2 = mid + 1;
                for (int j = prevStartY; j < prevStartY + prevM; j++) {
                    map[x][y1] = map[i][j];
                    map[x][y2] = map[i][j];
                    y1++;
                    y2++;
                }
                x++;
            }
        }

        // draw star
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result.append((map[i][j] == 1) ? "*" : " ");
            }
            result.append("\n");
        }

        System.out.println(result.toString());

        br.close();
    }

    public static int getM(int K) {
        int M = 5;
        for (int i = 0; i < K; i++) {
            M = M * 2 + 1;
        }
        return M;
    }
}