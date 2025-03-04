import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visitors = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visitors[i] = Integer.parseInt(st.nextToken());
        }

        int maxVisit = 0, currentSum = 0, count = 0;
        
        for (int i = 0; i < X; i++) {
            currentSum += visitors[i];
        }
        maxVisit = currentSum;
        count = 1;
        
        for (int i = X; i < N; i++) {
            currentSum += visitors[i];
            currentSum -= visitors[i - X];

            if (currentSum > maxVisit) {
                maxVisit = currentSum;
                count = 1;
            } else if (currentSum == maxVisit) {
                count++;
            }
        }

        if (maxVisit == 0) {
            sb.append("SAD\n");
        } else {
            sb.append(maxVisit).append("\n")
                    .append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}