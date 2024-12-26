import java.io.*;
import java.util.*;

/*
입력
- 보드판: 크기 최대 50
    - '.', 'X'로 이루어짐

조건
- 폴리오미노 2개 무한대로 가짐: AAAA,BB
- X를 모두 폴리오미노로 엎으려함
- 단, '.'는 덮기 불가능

풀이
- AAAA가 뭔저 되는지 확인하고, BB가 되는지 확인해야 사전 순
  (4배수(AAAA), 2배수(BB))
- 연속으로 온 X의 개수를 세기
    - 4로 나눈 몫만큼 AAAA로 채우기, 나머지는 BB로 채우기
    - 4배수나 2배수. 즉, 2배수가 아니라면 -1 출력


출력
- 폴리오미노로 모두 덮은 보드판 출력
- 사전순으로 가장 앞서는 답 출력
- 불가능하면 -1
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        StringBuilder answer = new StringBuilder();
        int xcnt = 0; // 연속 X 개수
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i);
            if (c == 'X') { // X수 세기
                xcnt++;
            }
            if (c == '.' || i == input.length() - 1) { // 폴리오미노로 덮고 초기화하기
                if (xcnt % 2 != 0) {
                    bw.write("-1");
                    bw.flush();
                    return;
                }

                for (int a = 0; a < xcnt / 4; a++) {
                    answer.append("AAAA");
                }
                for (int b = 0; b < (xcnt % 4) / 2; b++) {
                    answer.append("BB");
                }
                if (c == '.') {  // 그대로 붙이기
                    answer.append(".");
                }
                xcnt = 0;
            }

        }


        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}