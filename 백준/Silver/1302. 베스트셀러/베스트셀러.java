import java.io.*;
import java.util.*;

/*
- map 자료구조를 이용하여 같은 이름의 책의 개수 세기
- 현재까지 가장 많이 팔린 책으로 업데이트 하는 조건:
  '현재까지 가장 많이 팔린 책 개수'와 '지금 검사하는 책 개수'을 비교했을 시,
    - 지금 검사하는 책의 개수 더 많을 경우
    - 개수가 같다면, 사전순으로 더 빠를 경우
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        HashMap<String, Integer> map = new HashMap<>();
        String maxTitle = null;
        for (int i = 0; i < N; i++) {
            String title = br.readLine();

            map.put(title, map.getOrDefault(title, 0) + 1);
            if (maxTitle == null || //초기화
                    (map.get(maxTitle) < map.get(title)) || // 책의 개수 더 많을 때
                    (map.get(maxTitle) == map.get(title) && maxTitle.compareTo(title) > 0)) { // 책의 개수 같지만 제목이 사전순으로 앞에 있을 때
                maxTitle = title;
            }
        }

        bw.write(maxTitle.toString());

        br.close();
        bw.close();
    }
}