import java.util.*;
import java.io.*;


/*
- segmemtTree이용
- 주의할 점: 입력값의 범위 (0 <= value <= 1_000_000)
    => int의 max값(2,147,483,647) < value*value의 최대값(1,000,000,000,000) < long의 max값 (9,223,372,036,854,775,807)
    1. 구간곱을 구하는 계산 와중에 overflow가 날 위험이 있으므로 타입을 value의 타입을 long으로 한다
    2. 원래 구간합이면 originValue와 newValue의 차이를 구해 내려가면서 더해주었는데,
        이 문제는 구간곱이며, newValue가 0인 값도 있고, 0에서 다른 숫자로 변하는 경우도 있기때문에,
        처음에 segmentTree를 빌드해주는 것처럼 리프노드에서부터 root까지 다시 곱해주며 구간곱을 구해줘야한다.
 */
public class Main {
    /*
    Top-Bottom으로 구현
     */
    static class SegmentTree {
        long[] tree; // tree는 1부터 시작 (관련: node)
        long[] arr; // arr(leaf node)는 0부터 시작 (관련: start, end, right, left, idx)
        int N;
        final int MOD = 1_000_000_007;

        public SegmentTree(long[] arr) {
            this.arr = arr;
            this.N = arr.length;
            tree = new long[N * 4];

            init(1, 0, N - 1);
        }

        public void init(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);

            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD; // calc!!
        }

        public long query(int left, int right) {
            return query(1, 0, N - 1, left, right);
        }

        private long query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) { // 아예 범위에 해당하지 않는다면
                return 1;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long q1 = query(node * 2, start, mid, left, right);
            long q2 = query(node * 2 + 1, mid + 1, end, left, right);
            return (q1 * q2) % MOD; // calc!!
        }

        public void update(int idx, long newValue) {
            update(1, 0, N - 1, idx, newValue);
        }

        private void update(int node, int start, int end, int idx, long newValue) {
            if (idx < start || end < idx) {
                return;
            }

            if (start == end) {
                tree[node] = newValue;
                arr[idx] = newValue;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, newValue);
            update(node * 2 + 1, mid + 1, end, idx, newValue);

            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD; // calc!!
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            temp = br.readLine().split(" ");
            if ("1".equals(temp[0])) {
                int idx = Integer.parseInt(temp[1]) - 1;
                long newValue = Long.parseLong(temp[2]);
                segmentTree.update(idx, newValue);
            } else if ("2".equals(temp[0])) {
                int left = Integer.parseInt(temp[1]) - 1;
                int right = Integer.parseInt(temp[2]) - 1;
                result.append(segmentTree.query(left, right)).append("\n");
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}