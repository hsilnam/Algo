import java.util.Scanner;

/*
 * url: https://www.acmicpc.net/problem/15649
 */

public class Main {
	
	static int N, M; // N: 자연수(1~N), M(수열길이)
	static int[] numbers; // 수열 배열
	static boolean[] isSelected; // 자연수 사용 여부
	static StringBuilder result; // 결과 문자열
	
	public static void main(String[] args) { // main start
		Scanner sc = new Scanner(System.in); // input값 가져오기 시작
		N = sc.nextInt(); // N 설정값 가져오기
		M = sc.nextInt(); // M 설정값 가져오기
		
		numbers = new int[M]; // M의 수열길이를 가지는 numbers 배열 생성
		isSelected = new boolean[N+1]; // 자연수(1~N)에 대한 배열 생성 
		result = new StringBuilder(); //결과 문자열 생성
		
		permutation(0); // 재귀를 통해 순열 경우의 수 구하는 함수 호출
		
		System.out.println(result.toString()); // 결과 출력
	} // main end
	
	public static void permutation(int cnt) { // permutation start. 재귀를 통해 수열 생성하는 함수. cnt: 현재 자리수(0~M)
		if(cnt == M) { // 기저조건 start
			for(int i=0 ; i<M ; i++) { // StringBuffer에 완성한 수열(numbers)의 원소 정보를 넣는 반복문
				if(i > 0) { // 첫번째 원소를 제외하고 공백을 넣는 조건문
					result.append(" "); // 공백 추가
				} // 조건문 종료
				result.append(numbers[i]); // 원소 정보 추가
			} // 반복문
			result.append("\n"); // 마지막에 새로운 라인 추가
			return; // 재귀 종료
		} // 기저조건 end
		
		for(int i=1 ; i<=N ; i++) { // 자연수(1~N)을 이용하여 수열을 만드는 반복문
			if(isSelected[i] == true) continue; // 이미 앞에 해당 자연수(i)를 사용했다면 넘어감
			numbers[cnt] = i; // 해당 자리(cnt)에 해당 자연수(i)를 넣음
			isSelected[i] = true; // 해당 자연수(i)를 사용했다고 표시
			permutation(cnt+1); // 재귀를 통해 다음 자리수에 대하여 동일한 작업 
			isSelected[i] = false; // 수열 생성 완료 후 다른 수열을 만들기 위한 초기화 작업
		} // 반복문 종료
	} // permutation end
}