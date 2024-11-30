import java.io.*;
import java.util.*;

/*
제한: 2초

입력
- N: 길이 (<=50)
- A,B수 순서대로 주어짐 (<=100)

조건
- S = A[0] × B[0] + ... + A[N-1] × B[N-1]
    - A만 재배열 할 수 있음

로직
- A가 작은값, B가 큰값으로 매칭될 수 있도록 함
    - A는 오름차순, B는 내림차순으로 정렬 후 각 원소 곱해주기

출력
- S가장 작게 만들기

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Integer[] arrA = new Integer[N];
        Integer[] arrB = new Integer[N];
        String[] tempA = br.readLine().split(" ");
        String[] tempB = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(tempA[i]);
            arrB[i] = Integer.parseInt(tempB[i]);
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB, Collections.reverseOrder());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += arrA[i] * arrB[i];
        }

        bw.write(Integer.toString(answer));

        br.close();
        bw.close();
    }
}