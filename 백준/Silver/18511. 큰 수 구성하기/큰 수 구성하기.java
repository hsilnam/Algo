import java.io.*;
import java.util.*;

/*
입력
- N: 시간 (10 <= N <= 100_000_000)
- K: 포함되는 숫자 (1 <= K의 원소의 개수 <= 3)
- K의 원소들 (1<=원소<=9)
- 가능한 경우만 입력값 주어짐

풀이
- K원소들 오름차순 정렬 (마지막 부터 검사)
- dfs를 통해 뒤에서 부터 큰값을 기준으로 숫자를 만들어 N보다 작고 조합된 수 주 가장 큰 값을 구한다
    - ex) N: 73 K:{7,4}
          -> 47


출력
- N보다 작거나 같은 자연수 중,
    K의 원소로만 구성된 가장 큰 수 출력

 */

public class Main {
    public static int N, K, answer;
    public static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(st.nextToken()); // 1~9 (한자리수)
        }
        Arrays.sort(nums);

        dfs(0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int cur) {
        if (cur > N) {
            return;
        }
        if (answer < cur) {
            answer = cur;
        }

        for (int i = K - 1; i >= 0; i--) {
            dfs(cur * 10 + nums[i]);
        }
    }
}