import java.util.*;
import java.io.*;

/*
- 수학, 구현, 정렬
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int offset = (int) Math.round(N * 0.15);

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = offset; i < N - offset; i++) {
            sum += arr[i];
        }

        int result = (int) Math.round((double) sum / (N - offset * 2));
        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}