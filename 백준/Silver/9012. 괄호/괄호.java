import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            char[] inputs = br.readLine().toCharArray();
            int openCnt = 0;
            boolean isVPS = true;
            for (char input : inputs) {
                if (input == '(') {
                    openCnt++;
                } else if (input == ')') {
                    if (openCnt == 0) { // '(' 가 없는 경우
                        isVPS = false;
                        break;
                    }
                    openCnt--;
                }
            }
            if (openCnt > 0) { // '(' 가 남은 경우
                isVPS = false;
            }
            result.append((isVPS) ? "YES" : "NO").append("\n");
        }
        System.out.println(result.toString());
    }
}