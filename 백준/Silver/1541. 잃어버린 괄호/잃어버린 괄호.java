import java.io.*;
import java.util.*;

/*
입력
- 식 (‘0’~‘9’, ‘+’, ‘-’)
    - 가장 처음과 마지막 문자는 숫자
    - 연속해서 연산자X
    - 5자리 숫자 없음
    - 수는 0으로 시작할 수 있음
    - 식의 길이는 50보다 작거나 같음

조건
- 괄호를 적절히 치자 (계산 우선순위 정하기)

풀이
- 음수값이 클수록 좋으니 한번 '-'가 나오면 뒤에 것이 무엇이 오든 다 '-'취급
    - 예) 55-50+40 => 55-(50+40)
- 부호가 나오기 전이나 마지막까지 숫자로 만든다 (뒤의 숫자 추가할 때마다 자리수 * 10)
    - 앞이 0으로 이루어져 있으면 0으로 취급하다가 숫자가 나오면 대체한다

출력
- 괄호를 적절히 쳐서 값을 최소로 만들기
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        boolean isMinus = false;
        int sum = 0; // 전체 합 결과값
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(isDigit(c)) { // 숫자일 떄
                if (num == 0 && c == '0') { // 앞의 숫자가 0일 때
                    continue;
                }
                num = num * 10 + (c - '0');
            }

            if (!isDigit(c) || i == input.length() - 1) { // 부호이거나 마지막인 경우 sum 업데이트하고 초기화 작업
                if (isMinus) { // 음수로 만들어주기
                    num *= -1;
                }
                sum += num;
                num = 0;
            }
            if (!isMinus && c == '-') { // -처음 나오는 때 검사
                isMinus = true;
            }
        }

        bw.write(Integer.toString(sum));

        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isDigit(char c) {
        return ('0' <= c && c <= '9');
    }
}