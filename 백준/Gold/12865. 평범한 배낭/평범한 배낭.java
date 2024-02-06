import java.util.*;
import java.io.*;

/*
- 배낭문제
1. 일차원 배열로 최대 value값을 저장하는 maxs 배열 만듦
    - 가능한 최대 무개: K
    - 가능한 최대 가치: N(100)*V(1,000) < int
    - idx가 무게, value가 가치를 뜻함 (여러 물건들이 합쳐진)
    - 뒤의 값부터 비교한다
      (앞에서 부터 비교한다면 이차원 배열로 만들어야한다. value값을 비교할 시 이전 물건에 대한 결과값으로 해야하므로)
        - 무게가 해당 물건 적으면 넣는 것자체가 불가능하므로 물건 무게 까지만 검사한다.
2. 해당 무게가 나올 수 있는 경우에 대하여,
    현재 물건을 포함하지 않았을 경우의 value 값
        vs 현재 물건 포함했을 떄의 value (현재 물건 뺀값의 value + 현재 물건 value)
    비교하여 최대값으로 대체
3. maxs 배열에서 최대값을 고르고 출력한다.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        int[] maxs = new int[K + 1];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            int W = Integer.parseInt(temp[0]);
            int V = Integer.parseInt(temp[1]);

            for (int j = K; j >= W; j--) {
                maxs[j] = Math.max(maxs[j], maxs[j - W] + V);
            }
        }

        int max = 0;
        for (int i = 0; i <= K; i++) {
            max = Math.max(maxs[i], max);
        }

        bw.write(Integer.toString(max));

        br.close();
        bw.close();
    }
}