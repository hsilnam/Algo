import java.io.*;
import java.util.*;

/*

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[8001];
        for (int i = 0; i < N; i++) {
            nums[Integer.parseInt(br.readLine()) + 4000]++;
        }

        int mean = 0;
        int median = 0;
        int mode = 0;
        int range = 0;

        int sum = 0;
        for (int i = 0; i < 8001; i++) {
            sum += nums[i] * (i - 4000);
        }
        mean = (int) Math.round((double) sum / (double) N);

        int cnt = 0;
        for (int i = 0; i < 8001; i++) {
            cnt += nums[i];
            if (cnt >= 1 + N / 2) {
                median = i - 4000;
                break;
            }
        }

        int intfrequency = 0;
        boolean dupCheck = false;
        for (int i = 0; i < 8001; i++) {
            if (intfrequency < nums[i]) {
                intfrequency = nums[i];
                dupCheck = true;
                mode = i - 4000;
            } else if (intfrequency == nums[i] && dupCheck) {
                mode = i - 4000;
                dupCheck = false;
            }
        }

        int minRange = 0;
        int maxRange = 0;
        for (int i = 0; i < 8001; i++) {
            if (nums[i] > 0) {
                minRange = i - 4000;
                break;
            }
        }
        for (int i = 8000; i >= 0; i--) {
            if (nums[i] > 0) {
                maxRange = i - 4000;
                break;
            }
        }

        range = maxRange - minRange;

        StringBuilder answer = new StringBuilder();
        answer.append(mean).append("\n");
        answer.append(median).append("\n");
        answer.append(mode).append("\n");
        answer.append(range).append("\n");

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}