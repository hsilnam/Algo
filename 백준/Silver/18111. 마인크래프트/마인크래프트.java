import java.util.*;
import java.io.*;

/*
- 구현 / 브루트포스 알고리즘
- B: 주어진 블록 개수
1. 맵에있는 높이의 최소, 최대값을 구한다
2. 높이의 최소값에서 최대값 순차적으로 기준값(tgH)으로 두어 땅을 고르는데 시간과 땅의 높이를 체크를 한다
    - 고르는데 걸리는 최소시간과 (같은 시간 중) 최대 높이는 arr에 저장해놓는다
    1) 기준값과 해당 땅의 높이의 차를 구한다(tgH-map[i][j])
        - 높이차 같으면, 넘어가기
        - 높이차 양수면, 해당 땅에 블럭을 체워야하므로 time += abs(dff)*2, b-abs(diff)
        - 높이차 음수면, 해당 땅에 블럭을 빼야하므로 time+=abs(diff), b+abs(diff)
    2) 만약 주어진 블록개수(b)를,
        - 초과해서 사용하면 불가능한 경우이다
        - 초과해서 사용하지 않았다면
            - arr과 현재 나온 결과값을 바탕으로 업데이트한다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int B = Integer.parseInt(temp[2]);


        int[][] map = new int[N][M];
        int maxH = 0;
        int minH = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                maxH = Math.max(map[i][j], maxH);
                minH = Math.min(map[i][j], minH);
            }
        }

        int[] arr = new int[]{Integer.MAX_VALUE, 0}; // 고르는데 걸린 최소시간, 땅의 최대 높이
        for (int tgH = minH; tgH <= maxH; tgH++) {
            int h = tgH;
            int time = 0;
            int b = B;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == tgH) {
                        continue;
                    }
                    int diff = tgH - map[i][j];
                    if (diff > 0) {
                        b -= Math.abs(diff);
                        time += Math.abs(diff);
                    } else {
                        b += Math.abs(diff);
                        time += Math.abs(diff)*2;
                    }
                }
            }
            if (b >= 0) {
                if (arr[0] > time) {
                    arr[0] = time;
                    arr[1] = h;
                } else if (arr[0] == time) {
                    arr[1] = Math.max(arr[1], h);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(arr[0]).append(" ").append(arr[1]);
        bw.write(result.toString());

        br.close();
        bw.close();
    }
}