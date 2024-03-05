import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int M = Integer.parseInt(temp[0]);
        int N = Integer.parseInt(temp[1]);

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= N; i++) {
            for (int j = i * 2; j <= N; j = j + i) {
                if (isPrime[j]) {
                    isPrime[j] = false;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = M; i <= N; i++) {
            if (isPrime[i]) {
                result.append(i).append("\n");
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}