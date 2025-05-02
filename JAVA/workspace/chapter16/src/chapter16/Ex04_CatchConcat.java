package chapter16;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex04_CatchConcat {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			int num1 = sc.nextInt();
			int num2 = 10 / num1;
			System.out.println(num2);
		}catch(ArithmeticException | InputMismatchException e) {
			// | or 기호를  사용하여 여러가지 예외를 동시에 처리할 수 있음
			System.out.println("예외발생");
			e.printStackTrace();
			// 예외이름, 예외 발생시의 메세지, 어디서 발생했는지의 코드 위치(예외문에 꼭 들어가야함.)
			System.out.println("에러 메세지: " + e.getMessage());
			// 에러 발생시의 메시지만 반환하는 메서드
			// 다른 문구를 포함해서 출력할 때 사용
		}catch(Exception e) {
			// Exception e : 모든 예외를 의미
			// e 변수의 내용 : 어떤 예외가 발새했는지의 정보
			System.out.println("위에서 작성하지 않은 모든 예외를 처리");
		}
		
		System.out.println("Good bye~~!");

	}

}
