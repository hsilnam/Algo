import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : br.readLine().toCharArray()) {
            if (deque.isEmpty()) { // init
                deque.offer(c);
                continue;
            }
            if (c <= deque.peek()) {
                deque.offerFirst(c);
            } else {
                deque.offer(c);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : deque) {
            result.append(c);
        }
        System.out.println(result.toString());
    }

}