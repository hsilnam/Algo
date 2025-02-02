import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];

        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes); // 오름차순 정렬

        int maxW = 0;
        for (int k = 0; k < N; k++) { // k번째 로프부터 끝까지 사용
            maxW = Math.max(maxW, ropes[k] * (N - k));
        }

        bw.write(String.valueOf(maxW));

        br.close();
        bw.flush();
        bw.close();
    }
}