import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int B = Integer.parseInt(temp[1]); // 최대
        int W = Integer.parseInt(temp[2]); // 최소

        String[] arr = br.readLine().split("");


        int sIdx = 0;
        int BCnt = 0, WCnt = 0;
        int max = 0; // 최대 길이
        for (int eIdx = 0; eIdx < N; eIdx++) {
            String es = arr[eIdx];
            if ("B".equals(es)) { // B
                BCnt += 1;
            } else { // W
                WCnt += 1;
            }
            if (WCnt >= W) {
                while (sIdx < eIdx && BCnt > B) { // B최대 넘었고, sIdx가 eIdx보다 작을 쌔
                    String ss = arr[sIdx];
                    if ("B".equals(ss)) { // B
                        BCnt -= 1;
                    } else { // W
                        WCnt -= 1;
                    }
                    sIdx += 1;
                }
                if (WCnt >= W && WCnt != 0) {
                    max = Math.max(max, eIdx - sIdx + 1);
                }
            }
        }
        System.out.println(max);
    }
}