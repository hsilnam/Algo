import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        HashMap<String, String> siteInfo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            siteInfo.put(temp[0], temp[1]);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            result.append(siteInfo.get(br.readLine())).append("\n");
        }
        System.out.println(result.toString());
    }
}