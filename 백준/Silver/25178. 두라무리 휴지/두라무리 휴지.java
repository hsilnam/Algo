import java.io.*;
import java.util.*;

/*
입력
- N: 단어의 길이 (2<=N<=100_000)
- 2,3줄 입력값: 길이가 N이고 알파벳 소문자로 이루어진 문자열

조건
- 1: 한 단어를 재배열해 다른 단어를 만들 수 있어야 함
- 2: 두 단어의 첫 글자와 마지막 글자는 서로 동일해야함
- 3: 각 단어에서 모음(a,e,i,o,u)를 제거한 문자열은 동일해야함

풀이
- 적은 계산으로 판별할 수 있는 순으로 조건 비교: 2,3,1
- 문자열 2개 charArray로 받아서 저장
- 2: 처음과 끝 문자가 동일한지 확인
- 3:
    - 인덱스를 각각 옮겨가면서 문자 같은 지 비교
    - hashset으로 모음 집어넣고 모음인지 비교하고,
        모음이면 다음 인덱스로 옮기기
    - 두 문자열이 마지막까지 읽으면 멈춤
- 1: 알파벳 개수 정보를 세서 각 알파멧의 개수가 맞는지 확인

출력
- 문자열이 조건을 만족하면 YES, 아님 NO
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] alphaCnt1 = new int[26];
        int[] alphaCnt2 = new int[26];
        char[] chars1 = br.readLine().toCharArray();
        char[] chars2 = br.readLine().toCharArray();

        if (chars1[0] != chars2[0] || chars1[N - 1] != chars2[N - 1]) { // 2번째 조건
            bw.write("NO");
            bw.flush();
            return;
        }
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < N && idx2 < N) {
            if (isVowel(chars1[idx1])) {
                idx1++;
                continue;
            }
            if (isVowel(chars2[idx2])) {
                idx2++;
                continue;
            }

            if (chars1[idx1] != chars2[idx2]) {
                bw.write("NO");
                bw.flush();
                return;
            }
            if (idx1 < N) {
                idx1++;
            }
            if (idx2 < N) {
                idx2++;
            }
        }
        // 첫번째 조건
        for (
                int i = 0;
                i < N; i++) {
            alphaCnt1[chars1[i] - 'a'] += 1;
        }
        for (
                int i = 0;
                i < N; i++) {
            alphaCnt2[chars2[i] - 'a'] += 1;
        }
        for (
                int i = 0;
                i < 26; i++) {
            if (alphaCnt1[i] != alphaCnt2[i]) {
                bw.write("NO");
                bw.flush();
                return;
            }
        }
        bw.write("YES");

        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isVowel(char c) {
        HashSet<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');

        return vowelSet.contains(c);
    }
}