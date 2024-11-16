import java.io.*;
import java.util.*;

/*
다른 위치의 같은 글자기리 아치형 곡선을 그어 쌍을 지을 때, 선이 교차하지 않는 글자 찾기
- 괄호(()[]{})같이 Stack을 통해 서로의 쌍을 남는 것 없이 매칭 시켜주면 됨
- stack에 추가하는 조건: stack이 비어있거나, 현재 비교하는 값이 stack의 마지막값과 다른 경우
- stack에서 삭제하는 조건: 현재 비교하는 값이 stack의 마지막 값과 같은 경우
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int goodCnt = 0;
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            Stack<Character> stack = new Stack<>();
            String input = br.readLine();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                    continue;
                }

                stack.push(c);
            }
            if (stack.size() == 0) {
                goodCnt++;
            }
        }

        bw.write(Integer.toString(goodCnt));

        br.close();
        bw.close();
    }
}