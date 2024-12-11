import java.io.*;
import java.util.*;

/*
입력
- N: 단어의 개수 (1<=M<=N<7)
- 영단어: 소문자, 길이 20 이하, 에너그램 수 100_000 이하

조건
- 에너그램: 입력받은 영단어의 철자로 만들 수 있는 모든 단어

풀이
- 알파벳 소문자를 인덱스로 활용한 배열(alphaCnt) 사용 (크기 26)
- 단어에 사용된 알파벳 개수 배열에 저장 (alphaCnt)
- 사용할 수 있는 알파벳은 선택된 알파벳 개수 배열(selected)과 alphaCnt을 통해 알아낸다
    - 해당 알파벳의 selected와 alhpaCnt의 값이
        - 같으면, 더이상 알파벳을 사용할 수 없음
        - 작으면, 알파벳을 사용할 수 있음
- 사용한 알파벳의 순서와 위치는 word배열에 저장

출력
- 각 영단어마다 모든 가능한 에너그램 출력
- 알파벳 순서, 중복 없음

[오답노트]
- 메모리초과 (25%)
    - charArray로 단어를 받고, 철자들을 사전순으로 정렬한 후 시작한다
    - 중복없는 순열처럼 단어의 길이 만큼의 idx를 순열로 만들고
   만들어진 idx 순서대로 단어 매핑하여 단어 만듦
   => 중복인 애너그램 발생: 같은 알파벳이라도 다른 인덱스로 선택 처리
    ex) a(1)a(2)bc, a(2)a(1)bc
 */

public class Main {
    public static int N, M;
    public static int[] alphaCnt;
    public static int[] word; // input값의 idx와 매핑용
    public static int[] selected;

    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            alphaCnt = new int[26];
            for (char c : input.toCharArray()) {
                alphaCnt[c - 'a'] += 1;
            }

            M = input.length();
            word = new int[M];
            selected = new int[26];

            perm(0);
        }


        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void perm(int idx) {
        if (M == idx) {
            for (int w : word) {
                answer.append((char) (w + 'a'));
            }
            answer.append("\n");
            return;
        }

        for (int i = 0; i < 26; i++) { // 각 알파벳에 대하여 사용 경우의 수 따지기
            if (selected[i] == alphaCnt[i]) {
                continue;
            }
            selected[i] += 1; // 선택
            word[idx] = i; // 값저장
            perm(idx + 1); // 다음
            selected[i] -= 1; // 선택해제 (백트레킹)
        }
    }
}
