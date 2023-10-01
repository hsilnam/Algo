import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]); // 최대 공부시간
        int K = Integer.parseInt(temp[1]);

        // init
        long[] arr = new long[N + 1]; // 최대 중요도 계산하는데 사용되는 arr (공부시간이 idx)
        long max = 0;

        // get max
        for (int i = 0; i < K; i++) {
            temp = br.readLine().split(" ");
            int I = Integer.parseInt(temp[0]); // 중요도
            int T = Integer.parseInt(temp[1]); // 공부시간

            for (int j = N; j >= T; j--) { // 거꾸로 하는 이유는 arr를 하나로만 진행하기위 하여 (뒤에서부터 해야 잎의 결과에 영향을 안받음, 앞에서부터 검사하면 이전 결과를 저장하는 arr 따로 존재해야함)
                arr[j] = Math.max(arr[j], arr[j - T] + I); // 현재 공부시간 위치에서 (기존 최대 중요도, 현재 중요도를 합친 결과) 중 비교하여 최대값으로 대체하기
                max = Math.max(max, arr[j]); // 계산된 결과로 최대 중요도 구하기
            }
        }
        System.out.println(max);
    }
}