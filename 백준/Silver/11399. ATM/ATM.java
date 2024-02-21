import java.util.*;
import java.io.*;

/*
- 최소값: 적은 숫자가 많이 반복되고, 큰 숫자가 적게 반복될 때 => 오름차순으로 정렬한 다음에 더해준다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(arr);
        int total = 0;
        int wait = 0;
        for (int i = 0; i < N; i++) {
            wait += arr[i];
            total += wait;
        }

        bw.write(Integer.toString(total));

        br.close();
        bw.close();
    }
}