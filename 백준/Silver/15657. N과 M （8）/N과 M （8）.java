import java.util.*;
import java.io.*;


public class Main {
    static int N, M;
    static int[] arr, nums;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        arr = new int[N];
        nums = new int[M];
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(arr);

        comb(0, 0);

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void comb(int idx, int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                result.append(nums[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int i = idx; i < N; i++) {
            nums[cnt] = arr[i];
            comb(i, cnt + 1);
        }
    }
}