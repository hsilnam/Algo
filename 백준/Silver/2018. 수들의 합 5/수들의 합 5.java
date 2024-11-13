import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
투포인터
- sum(연속된 숫자 범위 합) < N: 목표값에 도달하기 위하여 다음 값을 더한다
- sum > N: 목표값을 초과함으로 현재 범위의 맨 앞의 값을 제외한다
- sum = N: 목표값이므로 가지수(count)를 올려주고, 다음 검사를 위해 맨 앞의 값을 제외한다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        int count = 1; // 자기자신(N) 포함

        int i = 1;
        int j = 2;
        int sum = i + j;
        while (i < j) {
            if (sum == N) {
                count++;
                sum -= i;
                i++;
            } else if (sum < N) {
                j++;
                sum += j;
            } else if (sum > N) {
                sum -= i;
                i++;
            }
        }


        bw.write(Integer.toString(count));
        
        br.close();
        bw.close();
    }
}