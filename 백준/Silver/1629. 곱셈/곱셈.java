import java.util.*;
import java.io.*;

/*
- 재귀함수를 통한 분할 정복 이용
    - 5^11 인경우,
      - 5^11: 5^8 * 5^8 * 5
      - 5^8: 5^4 * 5^4
      - 5^4: 5^2 * 5^2
      - 5^2: 5 * 5
      - 지수가 홀수라면 A를 별도로 곱해준다
    - overflow가 나지 않도록,
        - long을 통하여 계산
        - 계산 결과를 C를 통해 지속적으로 나머지 연산
        (int가 최대 2,147,483,647인데, A,B,C가 2,147,483,647 이하의 자연수)
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        long A = Long.parseLong(temp[0]);
        long B = Long.parseLong(temp[1]);
        long C = Long.parseLong(temp[2]);

        bw.write(Long.toString(pow(A, B, C)));
        br.close();
        bw.close();
    }

    static long pow(long a, long exp, long c) {
        if (exp == 1) {
            return a % c;
        }

        long half = pow(a, exp / 2, c) % c;
        return (((half * half) % c)
                * ((exp % 2 == 1) ? (a % c) : 1))
                % c;
    }
}