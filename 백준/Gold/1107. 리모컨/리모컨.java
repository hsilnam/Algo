import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final int start = 100;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] btns = new boolean[10];
        Arrays.fill(btns, true);

        if (M > 0) {
            String[] temp = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                btns[Integer.parseInt(temp[i])] = false;
            }
        }

        int min = Math.abs(start - N); // 시작지점(100)에서 +-버튼으로만 움직이는 경우
        // check other
        for (int i = 0; i <= 500_000 * 2; i++) {
            int len = 0; // i의 총 자리수 구하기
            if (i == 0) { // 0인 경우 검사
                len = 1;
                if (!btns[0]) { // 0인데 불가능하면 넘기기
                    continue;
                }
            } else { // 0 이상의 수 검사
                int check = i;
                boolean isOk = true;
                while (check > 0) {
                    len += 1;
                    if (!btns[check % 10]) { // 뒷자리 한자리 수가 버튼이 불가능하면 다음으로 넘기기
                        isOk = false;
                        break;
                    }
                    check /= 10; // 다음 수 검사
                }
                if (!isOk) { // 불가능하면 넘기기
                    continue;
                }
            }

            // 가능한 경우 목표수랑 비교해보기
            if (N == i) { // 아예 같으면 한번에 갈 수 있으니 끝내기
                min = Math.min(len, min);
                break;
            }

            min = Math.min(len + Math.abs(N - i), min); // 숫자버튼으로 우선 이동하고 목표 숫자까지 가는 경우의 최소값
            // (숫자버튼 하나씩 눌리니까 꼭 총 자리수로 계산하기!!)
        }
        System.out.println(min);
    }
}