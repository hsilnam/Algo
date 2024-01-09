import java.util.*;
import java.io.*;

/*
PriorityQueue 이용
- LikeInfo: 초밥을 좋아하는 사람들의 정보 (사람 번호, 초밥 타입)
    - 1. 초밥 타입 오름차순 정렬
    - 2. 초밥 타입이 같다면 사람 번호 오름차순 정렬 (앞에 있는 사람이 먼저 초밥을 먹기 때문)
- pq: 회전 초밥 타입 정보
    - 초밥 타입 순으로 정렬 (LikeInfo와 동일한 순서 기준으로 비교하기 위하여)

- LikeInfo, pq의 초밥 타입을 오름차순으로 비교했을 시 (queue의 peek 이용)
    - LikeInfo.type == pq: 해당 순서의 사람이 초밥을 먹음
    - LikeInfo.type < pq : 해당 순서의 사람이 좋아하는 초밥을 먹을 수 있는 경우가 없음
    - LikeInfo.type > pq : 해당 순서의 초밥을 좋아하는 사람이 없음

(HashSet을 이용한 것은 시간초과)
 */
public class Main {

    static class LikeInfo implements Comparable<LikeInfo> {
        int person;
        int type;

        public LikeInfo(int person, int type) {
            this.person = person;
            this.type = type;
        }

        @Override
        public int compareTo(LikeInfo o) {
            if (this.type == o.type) {
                return Integer.compare(this.person, o.person);
            }
            return Integer.compare(this.type, o.type);
        }

        @Override
        public String toString() {
            return "LikeType{" +
                    "person=" + person +
                    ", type=" + type +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        PriorityQueue<LikeInfo> likeTypes = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(temp[0]); j++) {
                likeTypes.offer(new LikeInfo(i, Integer.parseInt(temp[j])));
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (String type : br.readLine().split(" ")) {
            pq.offer(Integer.parseInt(type));
        }

        int[] cnts = new int[N];
        while (!likeTypes.isEmpty() && !pq.isEmpty()) {
            if(likeTypes.peek().type == pq.peek()) {
                pq.poll();
                cnts[likeTypes.poll().person] += 1;
            } else if (likeTypes.peek().type < pq.peek()) {
                likeTypes.poll();
            } else {
                pq.poll();
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            result.append(cnts[i]).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}