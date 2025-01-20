import java.io.*;
import java.util.*;

/*
입력
- 쇠막대기와 레이저의 배치를 나타내는 괄호표현 (길이 최대 100_000)

조건
- 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있음
- 쇠막대기를 다른 쇠막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝점은 겹치지 않도록 놓음
- 각 쇠막대기를 자르는 레이저는 적어도 하나 존재함
- 레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않음
- 괄호
    - 레이저: 여는 괄호와 닫는 괄호의 인접한 쌍 ‘( ) ’
    - 쇠막대기: 왼쪽 끝은 여는 괄호 ‘ ( ’ 로, 오른쪽 끝은 닫힌 괄호 ‘) ’로


풀이
- 전의 문자가 무엇이었는지 저장한다
    - '(', ')'라면, (레이저)
       쇠막대기 조각 누적합에 지금까지의 쇠막대기 개수를 더한다 (쇠막대기의 잘려나간 왼쪽)
    - '(', '('라면,
        쇠막대기 카운트를 올린다
    - ')', '('라면,
        아무것도 하지 않는다
    - ')', ')' 라면,
        - 쇠막대기 조각 누적합에 지금까지의 쇠막대기 조각 누적합(쇠막대기의 왼쪽들)+1(자기자신의 오른쪽)를 더한다
        - 쇠막대기 개수를 줄인다


출력
- 잘려진 조각의 총 개수

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] inputs = br.readLine().toCharArray();

        char prevC = '(';
        int pipeCnt = 0;
        int sum = 0;
        for (int i = 1; i < inputs.length; i++) {
            char c = inputs[i];
            if (prevC == '(' && c == ')') { // 레이저
                sum += pipeCnt;
            } else if (prevC == '(' && c == '(') {
                pipeCnt++;
            } else if (prevC == ')' && c == ')') {
                sum++;
                pipeCnt--;
            }
            prevC = c;
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.close();

    }
}