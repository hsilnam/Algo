import java.math.BigInteger;
import java.util.*;
import java.io.*;

/*
n번 반복하는데 걸리는 시간?
타이핑 / 지금까지 적은 문자 복붙 가능 (지금까지의 정확히 2배)
1번 적은데 S초
복붙하는데 t초
n번 적는데 걸리는 최소 시간
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            BigInteger N = new BigInteger(br.readLine());
            String[] temp = br.readLine().split(" ");
            BigInteger s = new BigInteger(temp[0]);
            BigInteger t = new BigInteger(temp[1]);

            BigInteger half = new BigInteger("2");

            BigInteger result = BigInteger.ZERO;
            BigInteger tg = N;
            BigInteger sUsed = tg.multiply(s); // tg*s. 단순 s를 사용했을 경우
            BigInteger tUsed = t.add(tg.mod(half).multiply(s)).add(tg.divide(half).multiply(s)); // t + ((tg%2)*s) + ((tg/2)*s). 딱 떨어지지 않을 때는 s도 같이 활용
//            System.out.println(tg + " : "+sUsed + " " +tUsed);
            while (sUsed.compareTo(tUsed) > 0) { // sUsed > tUsed. 단순 s를 사용한 것보다 t를 활용한게 더 빠를 때
                result = result.add(t.add(tg.mod(half).multiply(s))); // result += tUsed. 결과 더하기

                tg = tg.divide(half); // tg/2. 다음 타켓
                // 다음 타켓에 대한 시간 다시 구하기
                sUsed = tg.multiply(s); // tg*s. 단순 s를 사용했을 경우
                tUsed = t.add(tg.mod(half).multiply(s)).add(tg.divide(half).multiply(s)); // t + ((tg%2)*s) + ((tg/2)*s). 딱 떨어지지 않을 때는 s도 같이 활용
//                System.out.println(tg + " : "+sUsed + " " +tUsed);
            }
            result = result.add(sUsed); // tg*sUsed. 더이상 t를 활용할 수 없는 경우면 단순 S로 구하기
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}