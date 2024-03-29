import java.util.*;
import java.io.*;

/*
- 브루트포스
String.contains: 문자열 포함 여부 확인
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int num = 666;
        int cnt = 1;
        while (cnt != N){
            num++;
            if(Integer.toString(num).contains("666")) {
                cnt++;
            }
        }
        bw.write(Integer.toString(num));
        br.close();
        bw.close();
    }
}