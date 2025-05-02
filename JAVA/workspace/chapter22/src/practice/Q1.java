package practice;

import java.util.Scanner;

/**
 * 함수형 인터페이스: 메서드가 단 하나만 존재해야 람다식 사용 가능!
 * 아래는 각 문제에 해당하는 인터페이스 정의
 */

// 1번 문제: 특정 범위에서 특정 배수의 합을 계산하는 함수형 인터페이스
interface Test1 {
	int test1(int start, int end, int num); // 예: 0~100 중 3의 배수 합
}

// 2번 문제: 층수를 입력받아 건물 높이를 계산하는 함수형 인터페이스
interface Test2 {
	int getHeight(int floor);  // 입력값: 층수, 반환값: 높이
}

// 3번 문제: x값을 받아 y = x*x - 3x + 7 계산
interface Test3 {
	int calcY(int x); // 입력값: x, 반환값: y
}

// 4번 문제: 학점 입력값에 따라 평가 메시지를 반환
interface Test4 {
	String getMessage(String grade); // 입력값: 학점, 반환값: 평가 메시지
}

public class Q1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// ✅ 1번 문제: 람다식으로 3의 배수 합 구하기
		// (start, end, num) → start부터 end까지 num 간격으로 합계 계산
		Test1 test1 = (start, end, num) -> {
			int sum = 0;
			for (int i = start; i <= end; i += num) {
				sum += i; // num의 배수만 더함
			}
			return sum; // 최종 합계 반환
		};
		// 람다식 실행: 0부터 100까지 3의 배수의 합
		System.out.println("1번 결과 (0~100 사이 3의 배수 합): " + test1.test1(0, 100, 3));

//------------------------------------------------------------------------		
		
		// ✅ 2번 문제: 람다식으로 건물 높이 계산
		// 층수(floor)를 입력받아 높이 = 층수 × 5m 반환
		System.out.print("2번) 건물 층수를 입력하세요: ");
		int floor = sc.nextInt();

		// 간단한 계산은 한 줄로도 람다식 표현 가능
		Test2 test2 = f -> f * 5;
		System.out.println("건물의 높이: " + test2.getHeight(floor) + "m");

//------------------------------------------------------------------------		
		
		// ✅ 3번 문제: 람다식으로 y 계산 (y = x^2 - 3x + 7)
		System.out.print("3번) x 값을 입력하세요: ");
		int x = sc.nextInt();

		// 입력값 n을 받아 y값을 계산해서 반환하는 람다식
		Test3 test3 = n -> n * n - 3 * n + 7;
		System.out.println("y의 값: " + test3.calcY(x));

//------------------------------------------------------------------------		
		
		// ✅ 4번 문제: 람다식으로 학점 메시지 출력
		System.out.print("4번) 학점을 입력하세요 (A~F): ");
		String grade = sc.next().toUpperCase(); // 소문자 대응도 포함

		// 학점을 받아 평가 메시지를 반환하는 람다식
		Test4 test4 = g -> {
			// 여러 조건이 있을 경우 람다식 내부에 switch 문 사용 가능
			switch (g) {
				case "A":
				case "B":
					return "Excellent";  // A, B 학점
				case "C":
				case "D":
					return "Good";       // C, D 학점
				case "F":
					return "Bye";        // F 학점
				default:
					return "Invalid grade"; // 잘못된 입력 처리
			}
		};
		System.out.println("결과: " + test4.getMessage(grade));  // 람다식 실행
	}
}
