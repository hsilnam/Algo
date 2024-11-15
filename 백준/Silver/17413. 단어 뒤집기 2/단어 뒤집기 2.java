import java.io.*;
import java.util.*;

/*
문자열의 문자 하나씩 검사하면서 단어('a'-'z', '0'-'9'로 이루어진 문자)만 뒤집기
- 문자 뒤집기: Stack 자료구조를 사용
- 현재 문자가 '<'인 경우: '>'가 나올 때까지 그대로 문자열에 추가
- 현재 문자가 단어에 해당하지 않는 경우: 그대로 문자열에 추가
- 현재 문자 단어에 속하는 문자인 경우 ('a'-'z', '0'-'9'): 단어 정보 쌓기
- 단어가 끝나는 지점 / 끝까지 도달한 경우: 단어 reverse하고 문자열에 추가 && 단어 정보 초기화
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();

        StringBuilder answer = new StringBuilder();
        Stack<Character> wordStack = new Stack<>();
        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            if (isCharForWord(c)) {
                wordStack.push(c);
            }
            if ((i == chars.length - 1) // 마지막에 도달한 경우
                    || (i + 1 < chars.length - 1 && !(isCharForWord(chars[i + 1])))) { // 다음 문자가 단어에 속하지 않는 경우
                while (!wordStack.isEmpty()) {
                    answer.append(wordStack.pop());
                }
                wordStack.clear();
            }

            if (c == '<') { // tag
                while (i < chars.length) {
                    answer.append(chars[i]);
                    if (chars[i] == '>') {
                        break;
                    }
                    i++;

                }
            } else if (!isCharForWord(c)) {
                answer.append(c);
            }
            i++;
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }

    public static boolean isCharForWord(char c) {
        return ('a' <= c && c <= 'z') || ('0' <= c && c <= '9');
    }
}
