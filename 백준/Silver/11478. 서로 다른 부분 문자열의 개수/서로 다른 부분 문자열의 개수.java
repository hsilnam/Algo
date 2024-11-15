import java.io.*;
import java.util.HashSet;


/*
- 중복 없는 부분문자열의 개수를 구하기 위해 set 자료구조 사용
- 부분 문자열을 길이를 다르게하여 처음부터 검사하여 set에 넣어 set의 개수를 구함
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();
        HashSet<String> set = new HashSet<>();

        for (int i = 1; i <= chars.length; i++) { // 검사할 부분 문자열의 길이
            for (int j = 0; j <= chars.length - i; j++) { // 처음부터 끝까지 부분 문자열 검사
                StringBuilder partStr = new StringBuilder();
                for (int k = j; k < j + i; k++) { // 부분 문자열 만들기
                    partStr.append(chars[k]);
                }
                set.add(partStr.toString());
            }
        }

        bw.write(Integer.toString(set.size()));

        br.close();
        bw.close();
    }
}