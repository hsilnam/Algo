import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int max = Integer.parseInt(br.readLine());
        int idx = 1;
        for (int i = 2; i <= 9; i++) {
            int cur = Integer.parseInt(br.readLine());
            if(max < cur) {
                idx = i;
                max = cur;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(max).append("\n").append(idx);
        bw.write(result.toString());

        br.close();
        bw.close();
    }
}