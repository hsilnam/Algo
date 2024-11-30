import java.io.*;
import java.util.*;

/*
제한: 2초

입력
- N: 좌표 수 (1<=N<=1_000_000)
- X: 좌표들(-10^9 <= X <= 10^9)

조건
- Xi를 압축한 결과는 Xi>Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야함

로직
- 좌표압축이란? 수들의 정렬 순서로 좌표를 표현한 것 (인덱스로 범위 줄임)
- 입력값들을 정렬한 결과를 가진 배열을 통해 좌표의 순서를 알아낸다
- map에 해당 숫자의 압축 결과 저장 후, 입력값 순서대로 출력

출력
- 좌표 압축 적용한 결과 출력

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");

        int[] inputs = new int[N];
        int[] sortedInputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(temp[i]);
            sortedInputs[i] = inputs[i];
        }

        Arrays.sort(sortedInputs);

        Map<Integer, Integer> map = new TreeMap<>();
        map.put(sortedInputs[0], 0);
        int order = 0;
        for (int i = 1; i < N; i++) {
            if (sortedInputs[i - 1] == sortedInputs[i]) {
                continue;
            }
            map.put(sortedInputs[i], ++order);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(map.get(inputs[i])).append(" ");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}