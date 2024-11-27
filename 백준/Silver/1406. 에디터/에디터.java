import java.io.*;
import java.util.*;

/*
2개의 스택 활용
- 커서가 위치한 지점을 기준으로 left, right로 나눈 stack으로 문자를 저장한다
    - left, right가 서로 이동할 시 가장 마지막으로 들어온 문자열이 옮겨지므로 둘다 stack 이다
- 처음에는 커서가 맨 뒤에 위치하고 있으므로 left에 모든 문자가 쌓인다
- 커서가 왼쪽으로 이동하면 left의 가장 마지막에 있던 문자가 right로 이동한다
- 커서가 오른쪽으로 이동하면 right의 가장 마지막에 있던 문자가 left로 이동한다
- 삭제 시 left의 마지막 문자가 사라진다
- 마지막에는 left와 right를 순서대로 이어붙인 문자열을 완성한다
    - left top을 뒤에서부터 채우기
    - right: top을 앞에서부터 채우기


[오답노트]
시간초과(50%)
- left를 역순으로 가져올때 StringBuilder의 insert(0,x)를 쓴 것이 O(n) 연산을 초래함
=> foreach를 통해 순서대로 가져오는 걸로 변경
*/

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            left.push(input.charAt(i));
        }

        Integer N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            String cmd = temp[0];
            switch (cmd) {
                case "L":
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }

                    break;
                case "D":
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                    break;
                case "B":
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                    break;
                case "P":
                    left.push(temp[1].charAt(0));
                    break;
            }
        }

        StringBuilder result = new StringBuilder();
        for (char l: left) {
            result.append(l);
        }

        while (!right.isEmpty()){
            result.append(right.pop());
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}