import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] inputs;

    static int[] nums;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        HashSet<Integer> hashset = new HashSet<>();
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            hashset.add(Integer.parseInt(temp[i]));
        }

        inputs = new int[hashset.size()];
        int i = 0;
        for (int ele : hashset) {
            inputs[i++] = ele;
        }
        Arrays.sort(inputs);

        nums = new int[M];
        comb(0, 0);

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    static public void comb(int start, int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                result.append(nums[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        int before = 0;
        for (int i = start; i < inputs.length; i++) {
            if (before != inputs[i]) {
                before = inputs[i];
                nums[cnt] = inputs[i];
                comb(i, cnt + 1);
            }
        }
    }
}