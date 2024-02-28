import java.util.*;
import java.io.*;


/*
- 연산자에 대하여 stack을 이용한 후위표기식 만들기
    - '+', '-', '*', '/', '(' 문자열을 담는다
- 후위표기식을 만들어야하는 경우
    1. 괄호가 닫힌 경우 (')')
        -> stack에서 '(' 문자열이 나올 때까지 표기식을 만든다
    2. 우선순위가 높은 것뒤에 우선 순위가 낮거나 같은 것이 오는 경우
        (우선순위: '*','/' > '+','-')
       -> stack에서 현재 입력값의 우선순위보다 현재 stack의 top에 있는 연산의 우선순위가 낮다면 멈춘다
    3. 마지막에 stack에 연산자가 남은 경우
- 문자는 입력될 때마다 바로 결과 문자열에 추가해주면 된다
- 연산자는 stack에 있는 순서대로 stack이 비거나 '('가 나오기 전까지 문자열에 추가하면 된다

[실패들]
- 틀림 (9%)
  오답예시) A*B*C = (A*B)*C
  - 결과: ABC**
  - 답: AB*C*
  => 후위표기식을 만들어야하는 경우 중에 다음 연산에 의해 오답이 나오게 되었다.
  - stack에서 마지막 값이 '*'나 '/'였는데 현재 추가될 연산자가 '+'나 '-' 인 경우
    (우선순위가 높은 것뒤에 우선 순위가 낮은게 오는 경우)
    -> 곱하기 연산이 연속적으로 있는 경우 우선 순위가 낮은 연산자가 올 때만 한꺼번에 계산하게 되어버린다
        '*', '/'연산이 연속적으로 있을 때에도 '+','-'가 나오기 전까지 후위표기식을 만들어야한다
  => 따라서, 우선순위가 같거나 우선순위가 낮은게 오는 경우로 바꿔주어야한다
    우선순위를 알려주는 함수를 따로 만들어 체크해주는 방식으로 변경하도록 한다
- 틀림 (21%)
  오답예시) G*(A-B*(C/D+E)/F)
  - 결과: GABCD/E+*-F/* => G*((A-B*(C/D+E))/F)
  - 답: GABCD/E+*F/-*
  => 후위표기식을 만들어야하는 경우 중에 다음 연산에 의해 오답이 나오게 되었다.
  - 우선순위가 높은 것뒤에 우선 순위가 낮거나 같은 것이 오는 경우
    (우선순위: '*','/' > '+','-')
    -> 이 경우, '('이나 stack이 비어질때까지 후위표기식을 표시하는데,
        -----
        result(ing): 'GABCD/E+'
        stack: ['*', '(', '-', '*']
        input: '/'
        ------
        '*'의 우선 순위는 '/'와 같아서 연산을 하는게 맞지만,
        뒤에오는 '-'의 우선 순위는 '/'보다 작기 때문에 멈춰서야한다
    => 따라서, stack에 있는 top에 있는 문자의 우선순위만 검사하는게 아니라,
        while문이 끝나기 전까지 그 뒤의 연산자들도 우선순위를 검사하여 멈춰야한다
 */
public class Main {
    static StringBuilder result = new StringBuilder();
    static Stack<Character> opStack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (char c : br.readLine().toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append(c);
            } else if (c == '(') {
                opStack.push(c);
            } else if (c == ')') {
                make();
                opStack.pop(); // '(' 제거하기
            } else {
                make(c);
                opStack.push(c);
            }
        }
        make();

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void make() {
        while (!opStack.isEmpty() && opStack.peek() != '(') {
            result.append(opStack.pop());
        }
    }

    public static void make(char compareC) {
        while (!opStack.isEmpty() && opStack.peek() != '(') {
            if (getPriority(opStack.peek()) < getPriority(compareC)) {
                break;
            }
            result.append(opStack.pop());
        }
    }

    public static int getPriority(char c) {
        if (c == '*' || c == '/') {
            return 1;
        }
        return 0;
    }
}