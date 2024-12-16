import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
입력
- N: 포켓몬 개수 (1<=N<=100_000)
- M: 맞춰야하는 문제 개수 (1<=M<=100_000)
- 포켓몬
    - 1번~
    - 첫글자만 대문자, 나머지 소문자
    - 일부는 마지막 문자만 대문자
    - 2<=길이<=20
- 문제
    - 알파벳 ->포켓몬 번호
    - 숫자 -> 포켓몬 문자

풀이
- 배열 1번부터 순서대로 포켓몬스터 이름 저장 (N+1크기 배열, 앞에는 패딩)
- 해쉬맵를 통해 포켓몬이름에 대한 숫자 저장
- 숫자면 이름, 이름이면 숫자 답하기

출력
- 각 문제에 대한 답 출력

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] names = new String[N + 1];
        HashMap<String, Integer> numMap = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            String name = br.readLine();
            names[i] = name;
            numMap.put(name, i);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String quest = br.readLine();
            if ('A' <= Character.toUpperCase(quest.charAt(0)) && Character.toUpperCase(quest.charAt(0)) <= 'Z') { // 이름
                answer.append(numMap.get(quest)).append("\n");
            } else { // 숫자
                answer.append(names[Integer.parseInt(quest)]).append("\n");
            }
        }

        bw.write(answer.toString());
        
        br.close();
        bw.flush();
        bw.close();
    }
}