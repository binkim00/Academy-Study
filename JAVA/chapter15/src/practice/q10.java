package practice;

public class q10 {

	public static void main(String[] args) {
//		아래의 그림과 같이 출력되는 프로그램을 작성하시오.
		int row = 5;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= row - i; j++) {
				System.out.print("  ");
			}
			for (int k = 1; k <= 2 * i - 1; k++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		for(int i = row - 1; i >= 1; i--) {
			for (int j = 1; j <= row - i; j++) {
				System.out.print("  ");
			}
			for(int k = 1; k <= 2 * i - 1; k++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		
	}
}
