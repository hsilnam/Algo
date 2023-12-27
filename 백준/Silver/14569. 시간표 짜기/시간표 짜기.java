import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] lectures = new int[N][51]; // 해당 수업의 교시. 0은 제외
        int[] lecTimeCnt = new int[N]; // 총 교시 수

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int timeNum = Integer.parseInt(temp[0]);
            lecTimeCnt[i] = timeNum;
            for (int j = 1; j <= timeNum; j++) {
                lectures[i][Integer.parseInt(temp[j])] = 1;
            }
        }
        
        StringBuilder result = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");

            int[] templecTimeCnt = new int[N];
            for (int j = 1; j <= Integer.parseInt(temp[0]); j++) {
                for (int n = 0; n < N; n++) {
                    templecTimeCnt[n] += lectures[n][Integer.parseInt(temp[j])];
                }
            }
            int sum = 0;
            for (int n = 0; n < N; n++) {
                if (templecTimeCnt[n] == lecTimeCnt[n]) {
                    sum++;
                }
            }
            result.append(sum).append("\n");
        }
        System.out.println(result.toString());
    }
}