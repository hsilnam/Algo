import java.io.*;
import java.util.*;

/*
입력
- S,T: 2개의 문자열 (1<=S의길이<=999, 2<=T의길이<=1000, S<T)
    - 알파벳 소문자, 길이 100 이하

조건
- 문자열은 AB로만 이루어져있다
- 연산은 2개만 가능
    - 문자열 뒤에 A추가
    - 문자열 뒤집고 뒤에 B를 추가
    - ex) AB -> AB+A, BA+B

풀이
- StringBuilder 사용: 문자열 연산에 효율적
- T에서 S로 변환이 가능한지 역으로 추론해본다
    - 뒤의 문자가 무엇이 왔는지만 확인하면 추론 가능
        (연산 후에는 반드시 다른 알파벳이 붙었으므로)
- 길이가 같아질 때까지 진행했을 때 문자열이 같은지 검사

출력
- S를 T로 바꿀 수 있으면 1, 없으면 0출력


[참고]
- StringBuilder가 ArrayList보다 문자열 조작에 더 적합해 성능이 뛰어나다
- Collections.reverse(), Collections.reverseOrder()의 차이
    - Collections.reverse(): 단순히 역순 정렬
    - Collections.reverseOrder(): 내림차순 정렬
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        // T를 S와 길이가 같아질때까지 역순으로 조건에 맞게 만든다
        while (S.length() < T.length()) {
            // type 가져오기
            char type = T.charAt(T.length()-1);
            T.deleteCharAt(T.length()-1);

            if (type == 'B') { // B일때만 reverse 시킴
                T.reverse();
            }
        }

        bw.write((S.toString().equals(T.toString())) ? "1" : "0");

        br.close();
        bw.flush();
        bw.close();
    }
}