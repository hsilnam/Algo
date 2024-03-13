import java.util.*;
import java.io.*;

/*
- brute force 문제
- 조합을 구하는 중에, 불가능한 조합이 들어간 경우를 제외한다
- 불가능한 조합의 입력값이 순서대로 안오는 경우를 대비하여 작은 것은 앞으로, 큰 것은 뒤로 가도록 한다
    (조합을 구하는 방식이 앞에 오는 숫자보다 뒤에 있는 숫자가 더 커야하는 특징을 가지고 있기 때문에)
- 불가능한 조합이 한 아이스크림에 대하여 여러경우가 있을 수 있기 때문에,
    boolean[][]을 이용한다
    - idx 0: 앞에 오는 숫자의 여부
    - idx 1: 앞에 오는 숫자와 조합되는 뒤에오는 숫자의 존재 여부
    - 아이스크림은 1~N이기 때문에 배열 사이즈를 N+1로 설정한다
- 조합을 구하고 난 후,
  구해진 숫자들의 idx (0,1),(0,2),(1,2)의 경우를 겂들을 조사하여 하나라도 불가능한 조합이 있다면,
  넘어간다

[실패]
- 4%(틀림)
   - 조합을 구하는 도중 불가능한 조합을 체크할 때, 조합의 앞뒤 값에 대해서만 조사해서 문제가 됨
   ex) N: 4, M: 1, 불가능한 조합:(1,4)
       1 2 3 => pass
       1 2 4 => pass (틀림)
       1 3 4 => pass (틀림)
       2 3 4 => pass
   => 불가능한 조합이 가능한 경우가 시작된 부분의 값을 저장해야함
 */
public class Main {
    public static int N, M;
    public static int[] nums;
    public static boolean[][] disables;
    public static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        nums = new int[3];
        disables = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            if (a > b) {
                int t = b;
                b = a;
                a = t;
            }
            disables[a][b] = true;
        }
        comb(0, 0);

        bw.write(Integer.toString(total));

        br.close();
        bw.close();
    }

    public static void comb(int idx, int cnt) {
        if (cnt == 3) {
            if (disables[nums[0]][nums[1]] ||
                    disables[nums[1]][nums[2]] ||
                    disables[nums[0]][nums[2]]) {
                return;
            }
            total += 1;
            return;
        }
        for (int i = idx + 1; i <= N; i++) {
            nums[cnt] = i;
            comb(i, cnt + 1);
        }
    }
}