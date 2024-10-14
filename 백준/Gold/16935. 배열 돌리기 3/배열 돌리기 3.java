import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M, R;
	static String[][] arr;
	static String[][] tempArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tempInput = br.readLine().split(" ");
		N = Integer.parseInt(tempInput[0]);
		M = Integer.parseInt(tempInput[1]);
		R = Integer.parseInt(tempInput[2]);

		arr = new String[N][M];

		for (int i = 0; i < N; i++) {
			tempInput = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = tempInput[j];
			}
		}

		tempInput = br.readLine().split(" ");
		for (int i = 0; i < R; i++) {
			calc(Integer.valueOf(tempInput[i]));
		}
		
		StringBuilder result = new StringBuilder();
		int row = arr.length;
		int col = arr[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				result.append(arr[i][j]).append(" ");
			}
			result.append("\n");
		}
		System.out.println(result);
	}

	public static void calc(int mode) {
		if (mode == 1) {
			calc1();
		} else if (mode == 2) {
			calc2();
		} else if (mode == 3) {
			calc3();
		} else if (mode == 4) {
			calc4();
		} else if (mode == 5) {
			calc5();
		} else if (mode == 6) {
			calc6();
		}
	}

	public static void calc1() {
		int row = arr.length;
		int col = arr[0].length;

		tempArr = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tempArr[i][j] = arr[row - i - 1][j];
			}
		}
		arr = tempArr;

	}

	public static void calc2() {
		int row = arr.length;
		int col = arr[0].length;
		
		tempArr = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tempArr[i][j] = arr[i][col - j - 1];
			}
		}
		arr = tempArr;
	}

	public static void calc3() {
		int row = arr.length;
		int col = arr[0].length;
		
		tempArr = new String[col][row];
		for (int i = 0; i < tempArr.length; i++) {
			for (int j = 0; j < tempArr[i].length; j++) {
				tempArr[i][j] = arr[row - 1 - j][i];
			}
		}
		arr = tempArr;
	}

	public static void calc4() {
		int row = arr.length;
		int col = arr[0].length;
		
		tempArr = new String[col][row];
		for (int i = 0; i < tempArr.length; i++) {
			for (int j = 0; j < tempArr[i].length; j++) {
				tempArr[i][j] = arr[j][col - 1 - i];
			}
		}
		arr = tempArr;
	}

	public static void calc5() {
		int row = arr.length;
		int col = arr[0].length;
		
		tempArr = new String[row][col];
		for (int i = 0; i < row/2; i++) {
			for (int j = 0; j < col/2; j++) {
				tempArr[i][j] = arr[i+row/2][j];
				tempArr[i+row/2][j] = arr[i+row/2][j+col/2];
				tempArr[i+row/2][j+col/2] = arr[i][j+col/2];
				tempArr[i][j+col/2] = arr[i][j];
			}
		}
		arr = tempArr;
	}

	public static void calc6() {
		int row = arr.length;
		int col = arr[0].length;
		
		tempArr = new String[row][col];
		for (int i = 0; i < row/2; i++) {
			for (int j = 0; j < col/2; j++) {
				tempArr[i+row/2][j] = arr[i][j];
				tempArr[i+row/2][j+col/2] = arr[i+row/2][j];
				tempArr[i][j+col/2] = arr[i+row/2][j+col/2];
				tempArr[i][j] = arr[i][j+col/2];
			}
		}
		arr = tempArr;
	}

}
