import java.util.*;
import java.io.*;


/*
삼각형을 만들 수 있는 경우
- 두 변의 합이 나머지 한 변보다 길어야한다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] lens = new int[N];
        for (int i = 0; i < N; i++) {
            lens[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lens);

        int result = -1;
        for (int i = N - 1; i >= 2; i--) {
            int c = lens[i];
            int a = lens[i - 1];
            int b = lens[i - 2];

            if (c < a + b) {
                result = a + b + c;
                break;
            }
        }
        System.out.println(result);
    }
}