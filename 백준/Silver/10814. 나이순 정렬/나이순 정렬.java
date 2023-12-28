import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] arr = new String[N][2];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            arr[i][0] = temp[0]; // 나이
            arr[i][1] = temp[1]; // 이름
        }

        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.compare(Integer.parseInt(o1[0]), Integer.parseInt(o2[0]));
            }
        });

        StringBuilder result = new StringBuilder();
        for (String[] ele : arr) {
            result.append(ele[0]).append(" ").append(ele[1]).append("\n");
        }
        
        System.out.println(result.toString());
    }
}