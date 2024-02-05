import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int N, K;
	static int size = 100001;
	static int[] arr = new int[size];
	static final int max = Integer.MAX_VALUE - 1;
	static int min = max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.valueOf(temp[0]);
		K = Integer.valueOf(temp[1]);

		for (int i = 0; i < size; i++) {
			arr[i] = max;
		}

		search();
		System.out.println(min);
	}

	public static void search() {
		int idx, cnt;

		Queue<int[]> queue = new ArrayDeque<>();

		int[] start = { N, 0 }, tg;
		queue.offer(start);

		while (!queue.isEmpty()) {
//			for (int[] q : queue) {
//				System.out.print(Arrays.toString(q) + " ");
//			}
//			System.out.println();
			tg = queue.poll();
			idx = tg[0];
			cnt = tg[1];
			if (cnt >= min) {
				continue;
			}
			if (idx == K) {
				min = Math.min(cnt, min);
//				System.out.println(min);
				continue;
			}

			int[] nextIdxes = { idx - 1, idx + 1, idx * 2 };
			int nextCount = cnt + 1;
			for (int nextIdx : nextIdxes) {
				if (nextIdx == idx) {
					continue;
				}

				if (nextIdx < 0 || nextIdx >= size) {
					continue;
				}
				if (arr[nextIdx] != max && nextCount > arr[nextIdx]) {
					continue;
				}
				arr[nextIdx] = nextCount;
				int[] nextTg = { nextIdx, nextCount };
				queue.offer(nextTg);
			}
		}
	}
}
