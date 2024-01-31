import java.util.*;
import java.io.*;

/*
---
- dp 이용
- 메모리는 4MB이기 때문에 100,000*100,000 행렬이 아닌,
  해당 위치에서의 최소, 최댓값을 저장하는 1차원 배열사용
- 기준에 따른 방법 2가지
    1. 다음에 더할 값들 중에서 최대/최소값 비교해서 현재의 최대/최소값과 더해주기
       maxDp[0] + Math.max(map[i][0], map[i][1])
       maxDp[1] + Math.max(Math.max(map[i][0], map[i][1]), map[i][2])
       maxDp[2] + Math.max(map[i][1], map[i][2])
    2. 현재의 최대/최소값 결과 중에서 최대/최소값을 비교해서 다음에 더할 값과 더해주기
       map[i][0] + Math.max(maxDp[0], maxDp[1]);
       map[i][1] + Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
       map[i][2] + Math.max(maxDp[1], maxDp[2]);
[실패들]
- 3% (시간초과)
  - dfs로 순차적으로 더해가면서 min과 max를 구한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        for (int i = 0; i < 3; i++) {
            maxDp[i] = map[0][i];
            minDp[i] = map[0][i];
        }

/*        for (int i = 1; i < N; i++) {
            maxDp[0] += Math.max(map[i][0], map[i][1]);
            maxDp[1] += Math.max(Math.max(map[i][0], map[i][1]), map[i][2]);
            maxDp[2] += Math.max(map[i][1], map[i][2]);

            minDp[0] += Math.min(map[i][0], map[i][1]);
            minDp[1] += Math.min(Math.min(map[i][0], map[i][1]), map[i][2]);
            minDp[2] += Math.min(map[i][1], map[i][2]);
        }*/

        for (int i = 1; i < N; i++) {
            int max0 = map[i][0] + Math.max(maxDp[0], maxDp[1]);
            int max1 = map[i][1] + Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
            int max2 = map[i][2] + Math.max(maxDp[1], maxDp[2]);

            maxDp[0] = max0;
            maxDp[1] = max1;
            maxDp[2] = max2;

            int min0 = map[i][0] + Math.min(minDp[0], minDp[1]);
            int min1 = map[i][1] + Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
            int min2 = map[i][2] + Math.min(minDp[1], minDp[2]);

            minDp[0] = min0;
            minDp[1] = min1;
            minDp[2] = min2;
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxDp[i]);
            min = Math.min(min, minDp[i]);
        }

        bw.write(max + " " + min);

        br.close();
        bw.close();
    }

}