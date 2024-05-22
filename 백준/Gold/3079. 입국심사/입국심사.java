import java.io.*;
import java.util.*;

/*
이분탐색
- lower-bound
- 기준: 심사 전체 시간
- 판별: 심사가 전부 가능한가? + 해당 심사 시간이 최소값인가?
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        long M = Long.parseLong(temp[1]);

        int[] times = new int[N];
        int maxTime = 0;
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

        long left = 0;
        long right = maxTime * M;
        long result = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            for (int time : times) {
                if (count >= M) {
                    break;
                }
                count += mid / time;
            }
            if (count >= M) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        bw.write(Long.toString(result));

        br.close();
        bw.close();
    }
}