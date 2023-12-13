import java.util.*;
import java.io.*;

public class Main {
    static int[] digits = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static ArrayList<Long> nums = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine())-1;

        for (int i = 0; i < 10; i++) {
            comb(0, 0);
        }

        Collections.sort(nums);

        long result = -1;
        if(nums.size() > N) {
            result = nums.get(N);
        }

        System.out.println(result);

        br.close();
    }

    public static void comb(long num, int idx) {
        if(idx == 10) {
            if(!nums.contains(num)) {
                nums.add(num);
            }
            return;
        }

        comb(num, idx + 1);
        comb(num * 10 + digits[idx], idx + 1);
    }
}