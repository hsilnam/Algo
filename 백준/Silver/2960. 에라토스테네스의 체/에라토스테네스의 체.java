import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        boolean[] visited = new boolean[N + 1];
        visited[0] = true;
        visited[1] = true;

        int kCnt = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                if (visited[j]) {
                    continue;
                }
                kCnt += 1;
                visited[j] = true;
                if (kCnt == K) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}