import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<int[]> times = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            times.add(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])});
        }

        Collections.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<Integer> rooms = new PriorityQueue(); // room end time
        
        for (int[] time : times) {

            int start = time[0];
            int end = time[1];

            if(!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll();
            }
            rooms.offer(end);
        }

        bw.write(Integer.toString(rooms.size()));

        br.close();
        bw.close();
    }
}