import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;


/*
- Map 자료구조을 이용해서 파일의 확장자가 몇개 있는지 카운트
- 확장자를 사전순으로 출력해야하므로 TreeMap을 이용한다
    (TreeMap: 값 저장 시 키를 기준으로 자동 오름차순 정렬)

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            String ext = br.readLine().split("\\.")[1]; // 주의: 그냥 ".'으로 작성하면 정규표현식에 사용하는 문자로 구분
            treeMap.put(ext, treeMap.getOrDefault(ext, 0) + 1);
        }


        StringBuilder answer = new StringBuilder();
        for (Map.Entry<String, Integer> entry: treeMap.entrySet()) {
            answer.append(entry.getKey())
                    .append(" ")
                    .append(entry.getValue())
                    .append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}