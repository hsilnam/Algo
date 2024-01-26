import java.util.*;
import java.io.*;


/*
[최소 힙 구현]
- 힙은 배열로 구현
- root는 idx 1부터시작
- 부모 데이터 <= 자식 데이터
- push
    1. 힙의 끝에 데이터를 넣는다
    2. 부모 데이터와 자식 데이터를 비교하여 필요시 swap한다
       (부모 데이터 > 자식 데이터 라면 swap)
    3. 최대 힙의 조건에 만족할 때까지 2번을 반복한다
- pop
    1. 힙의 root 데이터 (최소값)을 리턴한다
    2. 힙의 끝에 있는 데이터를 root로 옮긴다
    3. push 때와 마찬가지로 부모 데이터와 자식들 데이터를 비교하여 필요시 swap한다
       (부모 데이터 > 자식 데이터 라면 swap)
       (왼쪽, 오른쪽 자식 모두 비교했을 시 더 큰 값으로 교체)
    3. 최대 힙의 조건에 만족할 때까지 3번을 반복한다
    ** 주의: 자식 데이터를 검사할 때, 현재 존재하는 데이터의 길이를 넘지않도록 조건을 추가해야한다
            (왼쪽 자식이 전체 데이터 길이 넘을 때는 아예 break, 왼쪽은 괜찮으나 오른쪽이 넘을 때에는 왼쪽 데이터로 검사하도로 조건을 달아야한다)
 */
public class Main {

    public static class MinHeap {
        int N;
        long[] heap;
        final int sIdx = 1;
        int eIdx;

        public MinHeap(int N) {
            this.N = N;
            this.heap = new long[N + 1];
        }

        public void push(long data) {
            // heap의 끝에 데이터 추가
            eIdx += 1;
            heap[eIdx] = data;

            // heap 구조 조정
            int dIdx = eIdx;
            while (sIdx < dIdx) {
                int pIdx = dIdx / 2;
                if (heap[pIdx] <= heap[dIdx]) {
                    break;
                }
                swap(pIdx, dIdx);
                dIdx = pIdx;
            }
        }

        public long pop() {
            if (eIdx == 0) { // heap is empty
                return 0;
            }
            // 루트 데이터 저장하기
            long popData = heap[sIdx];

            // 끝 데이터를 처음으로 옮김
            heap[sIdx] = heap[eIdx];
            heap[eIdx] = 0;
            eIdx -= 1;

            // heap 구조 조정
            int dIdx = sIdx;
            while (eIdx > dIdx) {
                int cIdxL = dIdx * 2;
                int cIdxR = dIdx * 2 + 1;
                if (cIdxL > eIdx) {
                    break;
                }
                if (cIdxR > eIdx) {
                    cIdxR = cIdxL;
                }
                int cIdx = (heap[cIdxL] < heap[cIdxR]) ? cIdxL : cIdxR;

                if (heap[dIdx] <= heap[cIdx]) {
                    break;
                }
                swap(dIdx, cIdx);
                dIdx = cIdx;
            }

            return popData;
        }

        public void swap(int aIdx, int bIdx) {
            long temp = heap[aIdx];
            heap[aIdx] = heap[bIdx];
            heap[bIdx] = temp;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        MinHeap minHeap = new MinHeap(N);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            long data = Long.parseLong(br.readLine());
            if (data == 0) {
                result.append(minHeap.pop()).append("\n");
            } else {
                minHeap.push(data);
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }

}