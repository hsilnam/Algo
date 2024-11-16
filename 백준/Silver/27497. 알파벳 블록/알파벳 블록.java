import java.io.*;
import java.util.*;

/*
3가지 버튼을 통해 문자열 만들기
- 문자열을 만드는 Deque와 삽입 방향(앞/뒤) 정보를 가진 Stack 이용
- 버튼 종류
    - 1: 문자열 맨 뒤에 블록 추가 (Deque: addFirst, Stack: push)
    - 2: 문자열 맨 앞에 플록 추가 (Deque: addLast, Stack: push)
    - 3: 가장 나중에 추가된 블록 제거 (Deque: (Stack 정보 결과) removeFirst/removeLast, Stack: pop)
        (이때, Deque, Stack이 비어있으면 remove, pop하지 않음)


-------
[오답 노트]
Stack 사용 (시간 초과)
- 문자열이 가장 나중에 추가된 블럭을 제거하기 위해, Stack 자료구조 사용해서 문자 정보 저장.
    - Block이라는 클래스를 통해 문자와 문자가 앞/뒤 중 어디에 붙어야하는지 정보 저장
- Stack을 통한 블록 정보를 통해 문자열 결과 만들기:
    foreach문을 통해 들어온 순서대로 Block 정보에 맞게 앞/뒤로 문자를 붙여 문자열 결과 완성

=> Stack의 정보를 통해 문자열을 만들 때 드는 시간 때문에 느려지는 것같음
    ->> 문자열을 만드는 Deque와 삽입 방향(앞/뒤) 정보를 가진 Stack, 2개를 이용해서,
        실행 과정에서 문자열을 완성시키는 것이 좋을 것같음
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Deque<Character> strDeque = new ArrayDeque<>();
        Stack<Boolean> dirStack = new Stack<>(); // front: true, last: false
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int type = Integer.parseInt(temp[0]);

            char c = 0;
            if (type < 3) {
                c = temp[1].charAt(0);
            }

            switch (type) {
                case 1:
                    strDeque.addLast(c);
                    dirStack.push(false);
                    break;
                case 2:
                    strDeque.addFirst(c);
                    dirStack.push(true);
                    break;
                case 3:
                    if (!strDeque.isEmpty() && !dirStack.isEmpty()) {
                        if (dirStack.pop()) {
                            strDeque.removeFirst();
                        } else {
                            strDeque.removeLast();
                        }
                    }
                    break;
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!strDeque.isEmpty()){
            answer.append(strDeque.pop());
        }

        bw.write((answer.length() == 0) ? "0" : answer.toString()); // 빈문자열이면 0출력

        br.close();
        bw.close();
    }
}