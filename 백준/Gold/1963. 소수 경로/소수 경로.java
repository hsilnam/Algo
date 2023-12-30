import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String[] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[] visited = getPrimeInit();
            queue.offer(new int[]{start, 0}); // 소수, cnt
            visited[start] = true;


            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curPrime = cur[0];
                int curCnt = cur[1];

                if(curPrime == end) {
                    result.append(curCnt).append("\n");
                    break;
                }
                // 각 자리수
                int p4 = (curPrime / 1000) % 10;
                int p3 = (curPrime / 100) % 10;
                int p2 = (curPrime / 10) % 10;
                int p1 = curPrime % 10;

                int nextCnt = curCnt + 1;
                for (int j = 0; j <= 9; j++) { // 바꿀 숫자
                    int next4 = j * 1000 + p3 * 100 + p2 * 10 + p1;
                    if (next4 >= 1000 && !visited[next4]) {
                        visited[next4] = true;
                        queue.offer(new int[]{next4, nextCnt});
                    }

                    int next3 = p4 * 1000 + j * 100 + p2 * 10 + p1;
                    if (next3 >= 1000 && !visited[next3]) {
                        visited[next3] = true;
                        queue.offer(new int[]{next3, nextCnt});
                    }

                    int next2 = p4 * 1000 + p3 * 100 + j * 10 + p1;
                    if (next2 >= 1000 && !visited[next2]) {
                        visited[next2] = true;
                        queue.offer(new int[]{next2, nextCnt});
                    }

                    int next1 = p4 * 1000 + p3 * 100 + p2 * 10 + j;
                    if (next1 >= 1000 && !visited[next1]) {
                        visited[next1] = true;
                        queue.offer(new int[]{next1, nextCnt});
                    }
                }
            }
        }
        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static boolean[] getPrimeInit() { // prime인 것만 visited false 처리
        int min = 1000;
        int max = 9999;
        boolean[] primeInit = new boolean[10000];

        for (int i = 2; i <= Math.sqrt(max); i++) {
            for (int j = min; j <= max; j++) {
                if (j % i == 0) {
                    primeInit[j] = true;
                }
            }
        }
        return primeInit;
    }
}