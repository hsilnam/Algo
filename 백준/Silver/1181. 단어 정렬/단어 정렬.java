import java.io.*;
import java.util.*;

/*
제한: 2초

입력
- N: 단어 개수 수 (1<=N<=20_000)
- 소문자로 이루어진 단어들 (문자열 길이 <=50)

조건
- 길이가 짧은 순, (같으면) 사전 순으로 정렬
- 중복된 단어는 제거해야함

로직
- set을 이용해서 중복 단어 제거, list를 통해 중복없이 단어 저장 후 정렬
    - Comparator을 통해 length 비교, (같으면) 단어 순서 비교 해서 정렬

출력
- 정렬 결과

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (set.contains(word)) {
                continue;
            }
            words.add(word);
            set.add(word);
        }

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(o1.length(), o2.length());
            }
        });

        StringBuilder answer = new StringBuilder();
        for (String word : words) {
            answer.append(word).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}