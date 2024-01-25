import java.util.*;
import java.io.*;

/*
문자의 위치를 저장한 Stack과 괄호를 완성한 길이 위치 저장한 배열을 이용
1. [push] '('인 경우 Stack에 해당 문자의 위치를 저장한다
2. ')'인 경우
    - Stack이 비어있다면, 괄호를 완성할 수 없으므로 넘긴다
      (괄호가 정상적으로 만들어졌다면 stack이 비어있을 것)
    - [pop] 현재위치까지 만들 수 있는 괄호의 길이 (len = i - stack.pop() + 1)와
            괄호가 시작되기 바로 직전의 위치의 괄호 길이를 가져온다
            (len += lens[((i - len) > 0) ? i - len : 0])
            (단, i-len이 처음 괄호인 경우 -1이 나올 수 있으므로 양수가 나오는 경우만 더해준다)
            (같은 depth에 있는 괄호의 길이를 함께 이어서 합산하는 것)
       => 구해진 것을 바탕으로 max값을 갱신한다
3. 최종 max값을 프린트한다
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[] inputs = br.readLine().toCharArray();

        int[] lens = new int[N];
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            if (inputs[i] == '(') {
                stack.push(i);
            } else { // inputs[i] == ')'
                if (stack.empty()) { // 잘못된 괄호
                    continue;
                }
                int len = i - stack.pop() + 1;
                len += lens[((i - len) > 0) ? i - len : 0];
                lens[i] = len;
                maxLen = Math.max(maxLen, len);
            }
        }
        
        bw.write(Integer.toString(maxLen));

        br.close();
        bw.close();
    }
}