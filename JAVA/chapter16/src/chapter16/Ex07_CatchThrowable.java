package chapter16;

import java.util.Scanner;

public class Ex07_CatchThrowable {
	
	// 첫 번째 함수
    public static void myMethod1() {
        myMethod2();  // 두 번째 함수를 불러서 실행
    }
    public static void myMethod3() {
		myMethod4();
	}
	public static void myMethod4() {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = 10/num1;
		System.out.println(num2);
	}

    // 두 번째 함수
    public static void myMethod2() {
        Scanner sc = new Scanner(System.in); // 키보드로 숫자 입력 받음
        int num1 = sc.nextInt();  // 입력한 숫자를 num1에 저장
        int num2 = 10 / num1;     // 10을 num1으로 나누기 시도
        System.out.println(num2); // 결과 출력
    }

    public static void main(String[] args) {
        try {
            myMethod1();   // 에러가 날 수도 있는 코드 실행
            // 위에서 예외가 발생하면 아래 코드는 실행되지 않음
            myMethod3();
        } catch (Throwable e) {  // 에러가 나면 여기서 잡아줌
        						// 에러와 예외 전부 감지 가능
//        	Throwable : try에서 실행한 메서드에서 예외가 발생했을때 사용하는 예외클래스
//        	시스템 에러까지 잡으면 프로그램이 엉뚱하게 계속 돌 수도 있음.
//        	실무에서는 예외를 정확히 분류해서 잡는 게 안전.
            e.printStackTrace();  // 에러 종류랑 어디서 터졌는지 알려줌
        }
        
        System.out.println("Good Bye~~!");
        // 만약 주석 풀면, 에러 메시지 내용만 간단히 출력됨
        // System.out.println(e.getMessage());

	}

}
