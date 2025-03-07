import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        BigInteger n = new BigInteger(br.readLine());
        BigInteger left = BigInteger.ZERO;
        BigInteger right = n;
        BigInteger answer = BigInteger.ZERO;
        
        while (left.compareTo(right) <= 0) {
            BigInteger mid = left.add(right).divide(BigInteger.TWO);
            if (mid.multiply(mid).compareTo(n) >= 0) {
                answer = mid;
                right = mid.subtract(BigInteger.ONE);
            } else {
                left = mid.add(BigInteger.ONE);
            }
        }
        
        bw.write(String.valueOf(answer));
        
        br.close();
        bw.close();
    }
}