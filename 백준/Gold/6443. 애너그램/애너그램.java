import java.io.*;
import java.util.*;

/*
입력
- N: 단어의 개수 (1<=M<=N<7)
- 영단어: 소문자, 길이 20 이하, 에너그램 수 100_000 이하

조건
- 에너그램: 입력받은 영단어의 철자로 만들 수 있는 모든 단어

풀이
- charArray로 단어를 받고, 철자들을 사전순으로 정렬한 후 시작한다
- 중복없는 순열처럼 단어의 길이 만큼의 idx를 순열로 만들고
   만들어진 idx 순서대로 단어 매핑하여 단어 만듦

출력
- 각 영단어마다 모든 가능한 에너그램 출력
- 알파벳 순서, 중복 없음
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

        for (int i = 0; i < 26; i++) {
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