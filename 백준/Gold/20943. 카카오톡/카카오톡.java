import java.util.*;
import java.io.*;

/*
a) Map으로 기울기 개수 구하기 (평행한 것은 같은 기울기로 취급)
    - 'ax + by + c = 0'의 기울기: -b/a
        - 최대공약수를 구하고 a,b에 나누기

b) 전체 조합의 수 - 동일한 기울기의 조합의 수 (nC2 이용)
- 주의점: double, long 타입 맞춰주기

----
[실패들]
1. [14%]
    a) Map으로 기울기 개수 구하기 (평행한 것은 같은 기울기로 취급)
        - 'ax + by + c = 0'의 기울기의 배수 없애기
            - (b%a == 0)
                - a = 1
                - b = b/a
            - (a%b == 0)
                - a = a/b
                - b = 1
            - a가 음수이면 전체적으로 (-1) 곱해줘서 통일성 주기
                (2x-3y+4, -2+3y+4도 기울기가 같음)
    b) 이중 for문으로 조합 구하기
2. [7%]
    a) Map으로 기울기 개수 구하기 (평행한 것은 같은 기울기로 취급)
        - 'ax + by + c = 0'의 기울기: -b/a
    b) 이중 for문으로 조합 구하기
3. [69%(시간초과)]
    a) Map으로 기울기 개수 구하기 (평행한 것은 같은 기울기로 취급)
        - 'ax + by + c = 0'의 기울기: -b/a
            - 최대공약수를 구하고 a,b에 나눈 상태에서 기울기 구하기
    b) 이중 for문으로 조합 구하기
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Map<Double, Integer> slopes = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            long a = Long.parseLong(temp[0]);
            long b = Long.parseLong(temp[1]);
            long gcd = gcd(a, b);
            double ag = a / gcd;
            double bg = b / gcd;
            double slope = (-1.0) * (bg / ag);
            slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
        }

        long result = nC2(N);
        Set<Double> keySet = slopes.keySet();
        for (Double k : keySet) {
            result -= nC2(slopes.get(k));
        }

        bw.write(Long.toString(result));

        br.close();
        bw.close();
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long nC2(long n) {
        return (n * (n - 1)) / 2;
    }
}