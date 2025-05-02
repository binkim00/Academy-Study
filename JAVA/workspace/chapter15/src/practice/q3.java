package practice;

import java.util.Scanner;

public class q3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		정수를 입력받아 짝수이면 “짝”, 홀수이면 “홀”을 출력하는 프로그램을 작성하라. 사용자가 정수를 입력하지 않는 경우에는 프로그램을 종료하라. 
		while(true) {
			System.out.println("정수를 입력하세요.(입력하지 않고 엔터를 누르면 종료됩니다.)");
			String input = sc.nextLine();
			
			if(input.isEmpty()) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			try {
				int num = Integer.parseInt(input);
				if(num % 2 == 0) {
					System.out.println("짝");
				}else {
					System.out.println("홀");
				}
			} catch (NumberFormatException e) {
				System.out.println("유효한 정수를 입력하세요.");
			}
		} sc.close();

//		예외처리없이 하는 방법.
//		System.out.print("정수를 입력하세요>>");
//		String str = sc.next();
		// 문자열의 갯수만큼 반복
//		for(int i = 0; i < str.length(); i++) {
			// charAt()을 이용하여 한글자씩 char에 저장
//			char c = str.charAt(i);
			// 글자가 숫자인지 아닌지 확인하는 if문
//			if(c < '0' || c > '9') {
//				System.out.println("수를 입력하지 않아 프로그램을 종료합니다.");
//				return;
//			}
//		}
		//모든 문자가 숫자라면 int로 변경
//		int num = Integer.parseInt(str);
		// 짝수인지 홀수인지 확인하는 if문
//		if(num % 2 == 0) {
//			System.out.println("짝수");
//		}else {
//			System.out.println("홀수");
//		}
		
	}
}
