import java.io.*;
import java.util.*;

public class Main {
    static int N, S, count = 0;
    static int[] numbers;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        explore(0, 0, false);
        
        bw.write(String.valueOf(count));
        
        br.close();
        bw.close();
    }
    
    public static void explore(int idx, int sum, boolean isIncluded) {
        if (idx == N) {
            if (sum == S && isIncluded) {
                count++;
            }
            return;
        }
        
        explore(idx + 1, sum + numbers[idx], true); // 현재 숫자 포함
        explore(idx + 1, sum, isIncluded); // 현재 숫자 미포함
    }
}