package Ifpractice;

public class q3_7 {

	public static void main(String[] args) {
//		3. 반복문을 사용하여 다음 모양을 출력하는 프로그램을 만들어 보세요
		for (int i = 1; i <= 5; i++) { 
            for (int j = 1; j <= i; j++) { 
                System.out.print("*");
            }
            System.out.println(); //줄바꿈
	}
		
		System.out.println();
		
//		4. 반복문을 사용하여 다음 모양을 출력하는 프로그램을 만들어 보세요
		 for (int i = 5; i >= 1; i--) {
	            for (int j = 1; j <= i; j++) {
	                System.out.print("*");
	            }
	            System.out.println();
	        }
		 
		 System.out.println();
		 
//		5. 반복문을 사용하여 다음 모양을 출력하는 프로그램을 만들어 보세요
		int rows = 5;
		for (int i = 1; i <= rows; i++) {
	            for (int j = 1; j <= rows - i; j++) {
	                System.out.print(" ");
	            }
	            for (int k = 1; k <= 2 * i - 1; k++) {
	                System.out.print("*");
	            }
	            System.out.println();
	        }
		 
		 System.out.println(); 
		 
//		 6. 반복문을 사용하여 다음 모양을 출력하는 프로그램을 만들어 보세요
		 for (int i = rows; i >= 1; i--) {
	            // 공백 출력 (행 수에 맞춰 공백의 개수는 증가)
	            for (int j = 1; j <= rows - i; j++) {
	                System.out.print(" ");
	            }

	            // 별 출력 (2 * i - 1 개의 별을 출력)
	            for (int k = 1; k <= 2 * i - 1; k++) {
	                System.out.print("*");
	            }

	            System.out.println();
		 }	 
		 
		 System.out.println();
		 
//		 7. 반복문을 사용하여 다음 모양을 출력하는 프로그램을 만들어 보세요
		 for (int i = 1; i <= rows; i++) {
	            // 공백 출력 (행 수에 맞춰 공백의 개수는 줄어듬)
	            for (int j = 1; j <= rows - i; j++) {
	                System.out.print(" ");
	            }

	            // 별 출력 (2 * i - 1 개의 별을 출력)
	            for (int k = 1; k <= 2 * i - 1; k++) {
	                System.out.print("*");
	            }

	            // 줄 바꿈
	            System.out.println();
	        }

	        // 두 번째 패턴: 별의 개수가 감소하는 부분
	        for (int i = rows; i >= 1; i--) {
	            // 공백 출력 (행 수에 맞춰 공백의 개수는 증가)
	            for (int j = 1; j <= rows - i; j++) {
	                System.out.print(" ");
	            }

	            // 별 출력 (2 * i - 1 개의 별을 출력)
	            for (int k = 1; k <= 2 * i - 1; k++) {
	                System.out.print("*");
	            }

	            // 줄 바꿈
	            System.out.println();
	        }
		 
		 
		 
		 
		 
		 
		 
		 
		 
}
}
