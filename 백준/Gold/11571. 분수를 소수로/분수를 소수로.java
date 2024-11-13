import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/*
- 순환소수를 구하기 위해서는 나눈 결과를 반복적으로 나누는 것
    - 나누려는 값이 분모보다 작으면 뒤에 0을 붙여서 계산한다
    - 나는 결과에 대한 몫은 하나의 문자열로 쌓아간다(StringBuilder 사용)
- 순환소수의 첫지점이 어딘지 알아내기
    -> HashMap을 이용 (key: 나눈 결과 값, value: 순서(idx))
    - 나눈 결과들 중에 동일한 값이 있다면, 그때부터 순환 소수가 시작되는 것
    - 순환 소수 시작되는 지점의 앞에 '(', 끝에 ')'을 표시한다
 */
public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            HashMap<Integer, Integer> remains = new HashMap<>();
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            int q = a / b;
            int r = a % b;

            StringBuilder rs = new StringBuilder();
            int i = 0;
            while (!remains.containsKey(r)) {
                remains.put(r, i++);
                if (r < b) {
                    r *= 10;
                }
                rs.append(r / b);
                r %= b;
            }

            int idx = remains.get(r);
            StringBuilder result = new StringBuilder();
            result.append(q).append(".");

            rs.insert(idx, "(");
            rs.append(")");

            result.append(rs);

            answer.append(result).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}