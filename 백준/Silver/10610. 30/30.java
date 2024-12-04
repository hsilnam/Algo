import java.io.*;
import java.util.*;

/*
입력
- N: 최대 10^5개의 숫자로 구성된 양수
    - 0으로 시작하지 않는다

풀이
- 30의 배수가 되는 조건
    - 30의 배수를 나누면 10과 3의 배수
    - String에 0이 존재하지 않는다면 30의 배수가 될 수 없다
        -> 뒷자리에 0이 적어도 하나 있어야한다
    - 각 자리수의 합이 3의 배수여야한다
    - 앞에는 0이 오지 않도록 해야한다
- charArray에 숫자를 나눠서 담는다
- 가장 큰 수를 찾는게 목표이므로 내림차순으로 정렬한다
- 10^5자리수를 표현하기위해 String으로 만들자
- 자리수 더할때만 해당 자리수를 숫자로 변환하여 계산한다

출력
- 숫자들을 섞어 30의 배수가 되는ㄴ 가장 큰수, 존재하지않으면 -1

[오답노트]
- 시간초과
    - 숫자들을 조합해서 3의 배수를 찾으려 하였음
    => 각 자리수의 합이 3의 배수이면 그 숫자는 3의 배수이다
        = 내림차순으로 정렬했을 때의 값만 비교하면 된다

- 틀림(2%)
    - 최대 10^5 자리수를 5자리 숫자로 오인함
    - int, long내로 숫자를 표현할 수 없음
    => 자리수 더할때만 해당 자리수를 숫자로 변환하여 계산하고,
        숫자를 만드려면 String으로 만들자
 */

public class Main {
    public static char[] chars;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        chars = br.readLine().toCharArray();
        Arrays.sort(chars);
        // reverse
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }

        bw.write(isOk() ? String.valueOf(chars) : "-1");

        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isOk() {
        if (chars[chars.length - 1] != '0') { // 마지막이 10의 배수가 아닌 경우
            return false;
        }

        // 3의 배수 검사하기
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                break;
            }
            sum += chars[i] - '0';
        }
        if (sum % 3 != 0) {
            return false;
        }

        return true;
    }
}