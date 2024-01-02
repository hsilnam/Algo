import java.util.*;
import java.io.*;

/*
-가 최초로 나오면 그 뒤의 값에 모두 음수로 인식하게 한다
6-1+3+4-2+3 => 6-(1+3+4)-(2+3)
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] temp = br.readLine().toCharArray();

        int total = 0;
        StringBuilder numSb = new StringBuilder();
        int sign = 1;
        for (char c : temp) {
            if (c == '-' || c == '+') {
                total += (Integer.parseInt(numSb.toString()) * sign);
                // init
                if(c == '-') {
                    sign = -1;
                }
                numSb = new StringBuilder();
                continue;
            }
            numSb.append(c);
        }
        total += (Integer.parseInt(numSb.toString()) * sign);


        bw.write(Integer.toString(total));

        br.close();
        bw.close();
    }
}