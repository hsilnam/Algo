import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		while(deque.size() > 1) {
			deque.pop();
			deque.add(deque.pop());
		}
		System.out.println(deque.pop());
	}
}
