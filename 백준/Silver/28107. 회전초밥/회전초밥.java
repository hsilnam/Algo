import java.util.*;
import java.io.*;

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