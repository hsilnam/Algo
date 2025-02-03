import java.io.*;
import java.util.*;

/*
입력
- T: 테스트 케이스 개수
- N: 정수 (0<=n<=4*10^9)

풀이
- 각각 입력값을 올려보면서 소수인지만 판별
- 에스테라토스의체(nlogn) 활용

출력
- n보다 크거나 같은 소수 중 가장 작은 소수

[오답노트]
- 25%(시간초과)
    - 미리 소수 판별 정보 저장
    - 홀수에 대해서만 소수 판별, BitSet 활용
    -> BitSet연산으로 아직도 느림
    => ArrayList로 소수인 것만 넣기
- 25%(메모리 초과)
    - 소수인것을 저장
    - 홀수에 대해서만 소수 판별, boolean배열과 ArrayList 활용 + 이진탐색
    -> 홀수 prime을 따지는 boolean배열과 ArrayList가 문제
    => 미리다 구하지말고 각각 입력값을 올려보면서 소수인지만 판별해보기
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            long num = Long.parseLong(br.readLine());
            if (num <= 2) {
                answer.append(2).append("\n");
                continue;
            }

            while (true) {
                boolean isPrime = true;
                for (long i = 2; i * i <= num; i++) {
                    if (num % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    answer.append(num).append("\n");
                    break;
                }
                num++;
            }

        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}