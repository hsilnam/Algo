import java.util.*;
import java.io.*;

/*
 */
public class Main {
    static int N, M;
    static int[] inputs;
    static boolean[] visited;
    static int[] nums;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");
        inputs = new int[N];
        nums = new int[M];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(inputs);

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

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                nums[cnt] = inputs[i];
                perm(cnt + 1);
                visited[i] = false;
            }
        }
    }
}