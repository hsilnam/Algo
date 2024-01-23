import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            HashMap<String, Integer> cloths = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split(" ");
                cloths.put(temp[1], cloths.getOrDefault(temp[1], 0) + 1);
            }

            int cnt = 1;
            for (int cloth:cloths.values()) {
                cnt *= (cloth+1);
            }

            result.append(cnt-1).append("\n");
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}