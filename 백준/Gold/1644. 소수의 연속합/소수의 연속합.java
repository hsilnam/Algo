import java.util.*;
import java.io.*;

/*
1. 에스테라토스의 체로 소수 구하기
2. 투포인터를 이용해서 가능한 경우의 수를 구한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        boolean[] isPrimes = new boolean[N + 1];
        Arrays.fill(isPrimes, true);
        isPrimes[1] = false;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            for (int j = i * 2; j <= N; j += i) {
                isPrimes[j] = false;
            }
        }

        ArrayList<Integer> primes = new ArrayList();
        for (int i = 0; i <= N; i++) {
            if (isPrimes[i]) {
                primes.add(i);
            }
        }

        int cnt = 0;
        int left = 0, right = 0;
        int sum = 0;
        while (left <= right) {
            if (sum <= N) {
                if (sum == N) {
                    cnt++;
                }

                right += 1;
                if (right == primes.size()) {
                    break;
                }
                sum += primes.get(right);
            } else if (sum > N) {
                sum -= primes.get(left);
                left += 1;
            }
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.close();
    }
}