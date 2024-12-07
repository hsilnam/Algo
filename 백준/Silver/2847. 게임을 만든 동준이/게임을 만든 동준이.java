import java.io.*;
import java.util.*;

/*
입력
- N: 레벨의 수 (1<=N<=100)
- 각 레벨을 클리어하면 얻는 점수, 레벨 순으로 주어짐 (20_000 이하 양수)

조건
- 플레이어의 점수: 레벨 클리어하면서 얻는 점수의 합
- 점수로 온라인 순위 매김
- 레벨 난이도 순으로 배치
    쉬운 레벨이 어려운 레벨보다 점수를 많이 받게 만듦
    -> 밸런스를 위해 특정 레벨의 점수 감소
- 각 레벨을 클리어할 때 주는 점수가 증가하게 만들어야함
- 점수는 항상 양수, 1만큼 감소가 1번
- 정답은 항상 존재, 여러개이면 점수를 내리는 것을 최소한

풀이
- 뒤에서 부터 검사
- 자신보다 크거나 같은 숫자가 있으면 자신의 점수보다 하나 적게 만든다
  cnt = (자신값-자신 앞의 값+1)


출력
- 점수를 몇번 감소시키면 되는지 출력


[오답노트]
- 3% (실패)
    - 앞뒤값을 검사해서 앞값이 뒤값보다 같거나 크면 뒤값보다 1작을 때까지 뺀다
    - 이때, 감소시키는 현재값의 앞에 있는 값들도 같이 감소시켜야하기 때문에,
      감소시키는 현재값 앞에 있는 숫자들도 만족할 때까지 다시 업데이트한다
   => 앞뒤로 움직일 필요없이
    뒤에서 부터 검사해서 자신보다 크거나 같은 숫자가 있으면
    자신보다 점수가 하나 적게 만드는 것이 좋을 것같다
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int[] nums = new int[N]; // inputs
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) { // 만족
                continue;
            }
            // 불만족
            int down = nums[i - 1] - nums[i] + 1;
            nums[i - 1] -= down;
            cnt += down;
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.flush();
        bw.close();
    }
}