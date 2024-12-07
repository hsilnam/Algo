import java.io.*;
import java.util.*;

/*
입력
- T: tc
- N: 카드의 개수(1<=N<=1000)
- N장의 카드에 적힌 알파벳의 초기 순서
    (모두 대문자)

조건
- 가장 왼쪽에 있는 카드부터 차례대로 한장씩 가져올 수 있음
- 처음으로 가져온 카드를 제외하고 가져온 카드를 놓인 카드의 가장 왼쪽, 오른쪽에 둘 수 있닺

풀이
- 처음에는 첫번재와 비교, 두번째부터는 양옆을 비교하여
    알파벳 상대적은 순서가 앞인 것은 왼쪽에, 뒤인 것은 오른쪽에 붙인다

출력
- 태욱이가 만들 수 있는 문자열 중에서 사전 순으로 가장 빠른 문자열
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder str = new StringBuilder(st.nextToken());
            for (int i = 1; i < N; i++) {
                char c = st.nextToken().charAt(0);
                if (str.charAt(0) >= c) { // 맨 왼쪽값보다 작거나 같은 경우 경우 왼쪽에 붙임
                    str.insert(0, c);
                    continue;
                }
                // 오른쪽은 비교를 하지 않아도 사전순보다 작으려면 무조건 오른쪽에 붙여야한다
                str.append(c);
            }
            answer.append(str.toString()).append("\n");
        }
        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}