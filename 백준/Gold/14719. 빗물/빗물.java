import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int H = Integer.parseInt(temp[0]);
        int W = Integer.parseInt(temp[1]);

        int[] heights = new int[W];
        temp = br.readLine().split(" ");
        int maxHeight = 0;
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(temp[i]);
            maxHeight = Math.max(maxHeight, heights[i]);
        }

        int[][] map = new int[maxHeight][W];
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < heights[i]; j++) {
                map[j][i] = 1;
            }
        }
        
        // 1(벽)이 있을경우, 1(벽) 나올때까지 카운트하다가 1(벽)나오면 카운트한 거 result에 추가하기
        int result = 0;
        for (int i = 0; i < maxHeight; i++) {
            boolean isStart = false;
            int cnt = 0;
            for (int j = 0; j < W; j++) {
                if (!isStart) {
                    if (map[i][j] == 1) {
                        isStart = true;
                    }
                    continue;
                }
                if (map[i][j] == 1) {
                    result += cnt;
                    cnt = 0;
                } else {
                    cnt += 1;
                }
            }
        }
        System.out.println(result);
    }
}