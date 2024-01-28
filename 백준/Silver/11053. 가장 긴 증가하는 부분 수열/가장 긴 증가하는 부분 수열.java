/*
https://www.acmicpc.net/problem/11053
 */

/*
[입력]
- N: 수열 크기 (1 ≤ N ≤ 1,000)
- i: 수열 원소 (1 ≤ i ≤ 1,000)

[출력]
가장 긴 증가 부분 수열 길이 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] LIS = new int[N];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
            max = Math.max(LIS[i], max);
        }
        System.out.println(max);

    }

}