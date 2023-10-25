import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] tips = new int[N];
        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(tips);
        int order = 0;
        long result = 0;
        for (int i = N - 1; i >= 0; i--) {
            order += 1;
            int tip = tips[i] - (order - 1);

            if (tip <= 0) {
                continue;
            }
            result += tip;
        }
        System.out.println(result);
    }
}