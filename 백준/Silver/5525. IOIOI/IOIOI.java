import java.util.*;
import java.io.*;

/*
1. 연속으로 IOI...을 만족하는 구간을 찾기
    - 찾기 용이하게 처음과 마지막과 동일한 값을 앞뒤에 넣고 검사한다
2. 찾는 문자열의 길이를 다음과 같은 식을 구하면 개수를 구할 수 있다
    (S-(2*N+1))/2+1
    - S: 연속으로 IOI을 만족하는 구간의 길이
    - (2*N+1): 찾는 문자열의 길이
    - (S-(2*N+1)): 나머지부분
    - (S-(2*N+1))/2: 나머지 부분의 개수('IO'을 하나라고 인식했을 때의 개수)
    - + 1: 자기자신
    * 단 음수면 만들어질 수 없는 경우이다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] temp = br.readLine().toCharArray();
        char[] arr = new char[M + 2];
        arr[0] = temp[0];
        arr[M + 1] = temp[M - 1];
        for (int i = 1; i < M + 1; i++) {
            arr[i] = temp[i - 1];
        }
        int tgLen = 2 * N + 1;
        int cnt = 0;
        int sLen = 0;

        for (int i = 1; i < M + 2; i++) {
            if (arr[i - 1] == arr[i]) { // S end
                if (sLen - tgLen >= 0) {
                    cnt += (sLen - tgLen) / 2 + 1;
                }
                sLen = 0;
            }
            if (sLen == 0 && arr[i] == 'I') { // S start
                sLen = 1;
            } else if (sLen > 0) { // s ing
                sLen += 1;
            }
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.close();
    }
}