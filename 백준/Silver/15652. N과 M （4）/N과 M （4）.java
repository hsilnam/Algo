import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[M];

        result = new StringBuilder();
        run(0, 1);

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void run(int cnt, int num) {
        if(cnt == M) {
            for (int i = 0; i < M; i++) {
                result.append(arr[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int i = num; i <= N; i++) {
            arr[cnt] = i;
            run(cnt+1, i);
        }
    }

}