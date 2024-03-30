import java.util.*;
import java.io.*;

/*
- 구현
- 문자열에서 지정한 단축키를 []로 묶기위해 해당 문자의 idx 위치를 저장한다 (cmdIdx)
    - idx는 0부터 시작이므로 초기값은 -1로 한다 (첫번쨰 글자가 0으로 시작하기 위해)
    - 나중에 문자열의 substring(start,end) 함수를 활용한다
- 지정한 단축키를 저장하힉 위해 HashSet을 이용한다
- 대소문자는 구분하지 않으므로 저장한 단축키는 모두 대문자로 저장한다
- 검사
    1. 모든 단어의 첫글자 확인
    2. 불가능하다면, 모든 문자 확인하기
        - 이때, 알파벳만 검사하도록 한다
    3. 불가능하다면, 현재 문자열 그냥 출력하기

[실패]
- 4% (틀림)
    - 모든 문자를 확인하는 지점에서 (2.) 띄어쓰기를 단축키로 지정하는 경우가 생김
        => 알파벳만 검사하도록 조건문을 추가해주어야한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String[] words = str.split(" ");
            // 모든 단어의 첫글자 보기
            boolean isOk = false;
            int cmdIdx = -1;
            for (String word : words) {
                char cmd = Character.toUpperCase(word.charAt(0));
                if (!set.contains(cmd)) { // 단축키 지정
                    set.add(cmd);
                    cmdIdx += 1;
                    isOk = true;
                    break;
                }
                cmdIdx += word.length() + 1; // 띄어쓰기를 포함하기 위해 +1 추가
            }
            // 모든 알파벳 보기
            if (!isOk) {
                cmdIdx = -1;
                for (char c : str.toCharArray()) {
                    if(c == ' ') { // 알파벳이 아닌 공백이면 넘어가기
                        cmdIdx += 1;
                        continue;
                    }
                    char cmd = Character.toUpperCase(c);
                    if (!set.contains(cmd)) { // 단축키 지정
                        set.add(cmd);
                        cmdIdx += 1;
                        isOk = true;
                        break;
                    }
                    cmdIdx += 1;
                }
            }
            // 아무것도 지정할 수 없음
            if (!isOk) {
                cmdIdx = -1;
            }

            if (cmdIdx == -1) { // 지정할 수 없는 경우
                result.append(str); // 그대로 출력
            } else {
                if (cmdIdx > 0) { // 첫글자가 지정된 단축키가 아닌 경우, 단축키 앞의 문자열 출력
                    result.append(str.substring(0, cmdIdx));
                }
                result.append("[").append(str.charAt(cmdIdx)).append("]") // 단축키는 []로 감쌈
                        .append(str.substring(cmdIdx + 1, str.length())); // 단축키 위의 문자열 출력
            }
            result.append("\n");
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}