import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int i = 0;
        for (String temp : br.readLine().split(" ")) {
            arr[i++] = Integer.parseInt(temp);
        }
        Arrays.sort(arr);

        int l = 0, r = N - 1;
        int[] result = new int[2];
        long minAbsDiff = Long.MAX_VALUE;
        while (l < r) {
            long sum = arr[l] + arr[r];
            long sumAbs = Math.abs(sum);
            if (minAbsDiff > sumAbs) {
                result[0] = arr[l];
                result[1] = arr[r];
                minAbsDiff = sumAbs;
            }
            if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}