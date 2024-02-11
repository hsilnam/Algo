import java.util.*;
import java.io.*;

public class Main {
    public static int N, M;
    public static int[] inputs;
    public static int[] nums;
    public static boolean[] visited;
    public static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");
        inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(inputs);

        visited = new boolean[N];
        nums = new int[M];

        perm(0);

        bw.write(result.toString());

        br.close();
        bw.close();
    }


    public static void perm(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                result.append(nums[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        int beforeNum = 0; // 이전 수열의 같은 위치에 있는 숫자값
        for (int i = 0; i < N; i++) {
            if (!visited[i] && beforeNum != inputs[i]) {
                visited[i] = true;
                nums[cnt] = inputs[i];
                beforeNum = inputs[i];
                perm(cnt + 1);
                visited[i] = false;
            }
        }

    }

}