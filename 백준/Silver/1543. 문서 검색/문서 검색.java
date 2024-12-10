import java.io.*;
import java.util.*;

/*
입력
- 문서 (길이 최대 2500)
- 검색하고 싶은 단어 (최대 50)
- 알파벳 소문자, 공백

조건
- 동시에 셀 수 없음

풀이
- 각 문자열을 하나씩 검사하면서
    같은 문자열이 나오면
    (검색하고 싶은 단어가 마지막 문자를 가리키고 있는데 문서와 알파벳이 같은 경우)
    카운트를 올리고, 문서의 다음 단어부터 다시 검사를 시작한다
- 다른 글자가 나왔을 때, 단어를 처음을 가리키는 것으로 초기화 할 뿐만아니라,
        문서의 (현재까지 검사한 단어길이+1)만큼 빼서 다시 검사한다

출력
- 중복 없이 최대 몇번 등장하는지

[오답노트]
- 실패 (37%)
    - 각 문자열을 하나씩 검사하면서
    같은 문자열이 나오면
    (검색하고 싶은 단어가 마지막 문자를 가리키고 있는데 문서와 알파벳이 같은 경우)
    카운트를 올리고, 문서의 다음 단어부터 다시 검사를 시작한다
    -> 반례:
        ababaa
        abaa
        = 정답 1/ 출력 0
        같은 것을 검사하고 바로 넘어가버려서 중복이 검사 안됨
        (baa != abaa)

    => 다른 글자가 나왔을 때, 단어를 처음을 가리키는 것으로 초기화 할 뿐만아니라,
        문서의 (현재까지 검사한 단어길이+1)만큼 빼서 다시 검사한다
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] doc = br.readLine().toCharArray();
        char[] word = br.readLine().toCharArray();


        int i = 0, j = 0;

        int cnt = 0;
        while (i < doc.length) {
            if (doc[i] == word[j]) { // 같은 글자
                if (j == word.length - 1) {
                    cnt++;
                    // 단어 초기화
                    j = 0;
                } else if (j < word.length) {
                    j++;
                }
            } else { // 다른 글자
                // 단어 초기화
                i -= j;
                j = 0;
            }

            if (i < doc.length) {
                i++;
            }

        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.flush();
        bw.close();
    }
}