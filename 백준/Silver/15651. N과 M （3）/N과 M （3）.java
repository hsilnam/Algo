import java.io.*;
import java.util.*;

/*
입력
- N,M: 자연수 (1<=M<=N<7)

조건
- 1부터 N까지 자연수 중에서 M개를 고른 수열
- 같은 수를 여러번 골라도됌

풀이
- 순차적으로 자리에 채워나가기

출력
- 수열은 사전순으로 증가하는 순서
- 중복되는 수열 없음
 */

public class Main {
    public static int N, M;
    public static int[] nums;
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        nums = new int[M];

        perm(0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void perm(int idx) {
        if (M == idx) {
            for (int n : nums) {
                answer.append(n).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            nums[idx] = i; // 값저장
            perm(idx + 1); // 다음
        }
    }
}