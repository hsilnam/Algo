import java.io.*;
import java.util.*;

/*
입력
- N: 문제의 개수
- P,L: 문제 번호, 난이도
- M: 명령문의 개수
- 명령문들

조건
- 명령어
    - recommend x
        - x 1: 가장 어려운 문제번호 출력, (난이도 내림차순 > 문제헌호 내림차순)
        - x -1: 가장 쉬운 문제 (난이도 오름차순 > 문제헌호 오름차순)
    - add P L
        - 추천 문제 추가
        - 리스트에 없는 것만 추가
    - solved P
        - 문제 번호 제거

풀이
- treeSet을 이용하여 어려움 '난이도 오름차순 > 문제헌호 오름차순'으로 정렬한다.
- hashMap을 이용하여 key: 문제, value: 난이도를 저장한다
    - treeSet에서 삭제 시 문제,난이도를 통해서 지우기 위해 정보 필요,
    - 문제가 key여야 지우기 쉬움

출력
- recommend 명령 주어질 때마다 문제번호 한줄씩 출력

 */

public class Main {
    public static class Problem implements Comparable<Problem> {
        int num;
        int level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(this.level, o.level);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> treeset = new TreeSet<>();
        HashMap<Integer, Integer> info = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            treeset.add(new Problem(num, level));
            info.put(num, level);
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num;
            switch (st.nextToken()) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    int level = Integer.parseInt(st.nextToken());
                    treeset.add(new Problem(num, level));
                    info.put(num, level);
                    break;
                case "recommend":
                    Problem problem = (Integer.parseInt(st.nextToken()) == 1) ? treeset.last() : treeset.first();
                    answer.append(problem.num).append("\n");
                    break;
                case "solved":
                    num = Integer.parseInt(st.nextToken());
                    treeset.remove(new Problem(num, info.get(num)));
                    info.remove(num);
                    break;
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.close();

    }
}