import java.util.*;
import java.io.*;

/*
- 플로이드 워샬
1. 입력값을 받는다
    - 갈 수 없는 부분은 계산을 위해 최대값으로 바꿔준 후 진행한다
2. 플로이드 워샬을 진행한다
3. 갈수없는 경우(0이나 무한대)는 0으로, 갈 수 있는 경우는 1로 표시하여 결과를 프린트한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int MAX = 10_000;

        int N = Integer.parseInt(br.readLine());

        int[][] mins = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(temp[j]);
                if (num == 0) {
                    num = MAX;
                }
                mins[i][j] = num;
            }
        }

        for (int k = 0; k < N; k++) { // 경유지
            for (int i = 0; i < N; i++) { // 출발지
                for (int j = 0; j < N; j++) { // 도착지
                    mins[i][j] = Math.min(mins[i][j], mins[i][k] + mins[k][j]);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = mins[i][j];
                if(num == 0 || num == MAX) {
                    num = 0;
                } else {
                    num = 1;
                }
                result.append(num).append(" ");
            }
            result.append("\n");
        }
        bw.write(result.toString());

        br.close();
        bw.close();
    }
}