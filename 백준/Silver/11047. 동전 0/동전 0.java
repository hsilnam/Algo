import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        for (int i = N - 1; i >= 0; i--) {
            if(K < coins[i]) {
                continue;
            }
            total += K/coins[i];
            K %= coins[i];
        }

        bw.write(Integer.toString(total));

        br.close();
        bw.close();
    }
}