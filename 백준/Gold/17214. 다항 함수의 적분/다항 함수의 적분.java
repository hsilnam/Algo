import java.util.*;
import java.io.*;

/*
[적분]
x^n => 1/(n+1)*x^(n+1)
-----
6x+6 => 6*(1/2)*x^2 + 6*(1)*x + W
     => 3*x^2 + 6*x + W

 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int i = 0;
        int xCnt = 0;
        int sign = 1;
        StringBuilder numSb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (char c : br.readLine().toCharArray()) {
            if (c == 'x') {
                xCnt += 1;
            } else if (c == '+' || c == '-') {
                if (i == 0) {
                    sign = (c == '+') ? 1 : -1;
                    continue;
                }
                result.append(getIntegralResult((result.length() == 0), sign, Integer.parseInt(numSb.toString()), xCnt));

                // 초기화
                xCnt = 0;
                sign = (c == '+') ? 1 : -1;
                numSb = new StringBuilder();
            } else { // 숫자인 경우
                numSb.append(c);
            }
            i++;
        }
        // 마지막 항수 계산 결과 추가
        result.append(getIntegralResult((result.length() == 0), sign, Integer.parseInt(numSb.toString()), xCnt));

        // 적분 상수 추가
        result.append((result.length() > 0) ? "+" : "").append("W");

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    static public String getIntegralResult(boolean isFirst, int sign, int num, int xCnt) {
        StringBuilder result = new StringBuilder();

        String signStr = (sign > 0) ? "+" : "-";

        int numResult = num / (xCnt + 1);
        String numResultStr = "";
        if (numResult == 0) {
            return "";
        } else if (numResult > 1) {
            numResultStr = Integer.toString(numResult);
        }

        // 해당 항의 적분 결과
        result.append(signStr) // 부호
                .append(numResultStr); // 앞의 상수
        for (int x = 0; x < xCnt + 1; x++) { // x차수
            result.append("x");
        }

        if (isFirst && sign > 0) { // 첫번째 결과가 양수이면 '+' 지우기
            result.deleteCharAt(0);
        }
        return result.toString();
    }
}