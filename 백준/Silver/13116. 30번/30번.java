import java.io.*;
import java.util.*;

/*
입력
- T(1<=T<=50_000): TC
- A,B(1<=A,B<=1023): 두 정수
- 1부터 시작하는 완전이진트리

로직
- 트리의 최소 공통 조상
- 순서대로 1부터 값이 저장된 완전이진트리 특성상
    - 부모: 나누기 2한 값이 부모 값이 됨
    - depth: log(n)으로 구할 수 있음
- depth가 1일때까지 찾기(root)
- 우선 A와 B 중 depth가 큰 것이 depth가 같아질 때까지 올라가고,
    그 후 같은 부모인지 비교하며 같이 올라간다
    같은 부모를 찾으면 그것이 최소 공통 조상이다
- 10K를 구하라했기 때문에 마지막에 결과값에 곱하기 10을 해준다
출력
- 트리의 최소 공통 조상

 */

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String[] temp = br.readLine().split(" ");
            int A = Integer.parseInt(temp[0]);
            int B = Integer.parseInt(temp[1]);

            // depth 구하기
            int dA = (int) (Math.log(A)/Math.log(2));
            int dB = (int) (Math.log(B)/Math.log(2));
            // 항상 depth가 왼쪽(A)이 낮도록 swap
            if (dA > dB) {
                int tmp = dB;
                dB = dA;
                dA = tmp;

                tmp = B;
                B = A;
                A = tmp;
            }

            // depth 같아질때까지 올라가기
            while (dA < dB) {
                B /= 2;
                dB--;
            }

            // 최소 공통부모 찾기
            while (A != 1 && A != B) {
                A /= 2;
                B /= 2;
            }

            answer.append(A * 10).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }


}