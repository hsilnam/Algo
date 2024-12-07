import java.io.*;
import java.util.*;

/*
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int cnt = 10;
        while (N > 1) {
            N/=2;
            cnt++;
        }

        bw.write(String.valueOf(cnt));
        
        bw.flush();
        br.close();
        bw.close();
    }
}