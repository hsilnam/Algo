import java.io.*;
import java.util.*;

/*
입력
- 여러개의 테이트 케이스
    - s,t: 2개의 비교 문자열 (s,t 10만 이하)

조건
- 부분문자열: t에서 몇개의 문자를 제거하고, 순서를 바꾸지 않고 합쳤을 경우 s가 된다

풀이
- 앞에서 부터 하나씩 검사를 한다
    - s와 t가 같은 경우에는, 둘다 다음 문자를 가리킨다
    - s와 t가 다른 경우에는, t만 다음 문자를 가리킨다
    - t가 끝에 도달했는데 s의 위치가 마지막이아니면 NO
    - s가 끝에 도달하면 바로 YES 출력

출력
- s,t순서대로 s가 t의 부분 문자열인 경우 Yes라 출력, 아닌경우 No

 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        StringBuilder answer = new StringBuilder();
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(" ");
            char[] ss = temp[0].toCharArray();
            char[] ts = temp[1].toCharArray();

            int sIdx = 0;
            int tIdx = 0;
            while (sIdx < ss.length && tIdx < ts.length) {
                if (ss[sIdx] == ts[tIdx]) { // 같으면 둘다 넘기고
                    sIdx++;
                }
                // 다르면 t만 넘김
                tIdx++;
            }

            answer.append((sIdx == ss.length) ? "Yes" : "No").append("\n");
        }
        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}