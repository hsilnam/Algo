import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        long n = Long.parseLong(br.readLine());
        long left = 0;
        long right = n;
        long answer = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            if (Math.pow(mid, 2) >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        bw.write(String.valueOf(answer));
        
        br.close();
        bw.close();
    }
}