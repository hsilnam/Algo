import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
재귀함수
- 종료조건: 검사하는 선의 길이가 1이 되었을 때(n이 0일 때 (3^0))
- 길이가 1이 되었을 때, '-'을 추가한다
- 3등분으로 나누었을 때,(n-1)
    왼쪽, 오른쪽 부분은 계속 검사하고,
    가운데 부분은 더이상 검사하지 않고 공백으로 채워둔다 (길이: 3^(n-1))
 */
public class Main {
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);

            run(N);
            answer.append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }

    public static void run(int n) {
        if (n == 0) {
            answer.append("-");
            return;
        }

        int nextN = n - 1;
        StringBuilder midspace = new StringBuilder();
        for (int i = 0; i < Math.pow(3, nextN); i++) {
            midspace.append(" ");
        }

        run(nextN); // 왼
        answer.append(midspace); // 가운데
        run(nextN); // 오
    }
}