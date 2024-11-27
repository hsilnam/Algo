import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.valueOf(temp[0]);
		int K = Integer.valueOf(temp[1]);
		
		LinkedList<Integer> people = new LinkedList<Integer>();
		for(int i=1; i<= N; i++) {
			people.add(i);			
		}

		
		StringBuilder result = new StringBuilder();
		result.append("<");
		
		int tgIdx=K-1;
		while(true) {
			if(people.size() == 0) {
				break;
			}
			tgIdx %= people.size();
			
			result.append(people.get(tgIdx));
			if(people.size() != 1) {
				result.append(", ");		
			}

			people.remove(tgIdx);
			tgIdx += K-1;
		}
		result.append(">");
		System.out.println(result.toString());
	}
}
