import java.io.*;
import java.util.*;

/*

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();

        StringBuilder answer = new StringBuilder();
        StringBuilder word = new StringBuilder();
        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            if (isCharForWord(c)) {
                word.append(c);
            }
            if ((i == chars.length - 1) // 마지막에 도달한 경우
                    || (i + 1 < chars.length - 1 && !(isCharForWord(chars[i + 1])))) { // 다음 글자가 문자에 속하는 글자가 아닌 경우
                answer.append(word.reverse());
                word = new StringBuilder();
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