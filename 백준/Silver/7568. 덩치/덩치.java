import java.util.*;
import java.io.*;

/*
- 구현, 브루트포스
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] info = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            info[i][0] = Integer.parseInt(temp[0]);
            info[i][1] = Integer.parseInt(temp[1]);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int rank = 1;
            for (int j = 0; j < N; j++) {
                if(i == j) {
                    continue;
                }
                if(info[i][0] < info[j][0] && info[i][1] < info[j][1]) {
                    rank++;
                }
            }
            result.append(rank).append(" ");
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}