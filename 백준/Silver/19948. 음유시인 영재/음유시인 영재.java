import java.util.*;
import java.io.*;

/*
- 구현 문제
- 제목과 내용을 전부 적을 수 있으면 제목을, 없으면 -1을 출력한다
- space나 문자는 연속으로 같은 것이 올 경우에는 추가적인 개수를 안세줘도 된다(하나의 space/문자로 취급)
    - 문자는 대소문자를 구분한다
- 남은 문자 개수는 대소문자를 구분하지 않고 모두 대문자를 취급하여 구한다
- 중간에 가능한 최대 space/문자 개수보다 카운팅하고 있는 값이 더 커지게 되면 멈추고 -1을 출력한다
- 각 단어 당 첫번째 단어인 경우 제목으로도 쓰이므로 해당 단어가 2번 사용되는 것과 동일하다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] words = br.readLine().split(" ");

        int remainSpaceCnt = Integer.parseInt(br.readLine());
        int[] remainAlphaCnts = new int[26];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < 26; i++) {
            remainAlphaCnts[i] = Integer.parseInt(temp[i]);
        }

        StringBuilder result = new StringBuilder();
        int spaceCnt = -1; // 첫번째 단어 앞에는 space가 없어서
        for (String word : words) {
            if (word.equals("")) { // 연속으로 스페이스가 온 경우에는 spaceCnt를 세지 않는다
                continue;
            }
            spaceCnt += 1;

            boolean impossible = false;
            char[] chars = word.toCharArray();
            char prevC = '#';
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                char bc = Character.toUpperCase(c);

                if (i == 0) { // title
                    remainAlphaCnts[getAlphaIdx(bc)] -= 1;
                    result.append(bc);
                }

                // content
                if (prevC == c) { // 연속으로 같은 문자 온 경우에는 제외한다
                    continue;
                }
                remainAlphaCnts[getAlphaIdx(bc)] -= 1;

                if (remainAlphaCnts[getAlphaIdx(bc)] < 0) { // 불가능한 경우 (문자)
                    impossible = true;
                    break;
                }
                prevC = c;
            }
            if (spaceCnt > remainSpaceCnt || impossible) { // 불가능한 경우(space / 문자)
                result = new StringBuilder();
                break;
            }
        }

        bw.write((result.length() == 0) ? "-1" : result.toString());

        br.close();
        bw.close();
    }

    public static int getAlphaIdx(char c) {
        return c - 'A';
    }
}