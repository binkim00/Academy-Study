package chapter7;

public class Ex01_MethodType {
//public,private,protected
//	접근제어자
//         정적메서드(클래스이름으로 사용가능하도록 생성)
//	              반환타입 : void=반환하지 않음, 자료형=해당 타입을 반환한다
//	                   메서드 이름(카멜표기법)
//						    매개변수 : 메서드가 실행될때 필요한 데이터
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 메서드와 함수의 차이
		// 함수(Function) : 특정한 매개변수를 넣으면 특정한 값을 돌려주는 기능
		// 메서드 : 클래스 안에 만들어진 함수		
		
//		자바에서의 메서드 선언 방식
//		메서드 생성시 필수로 적어야하는 명령어 : 반환타입, 메서드이름, 매개변수
		
//		메서드 실행하기
//		1. 클래스 선언하기(static이 없는 메서드)
//		클래스를 선언해서 만든 결과물 ex01을 인스턴스(객체) 라고 합니다.
		Ex01_MethodType ex01 = new Ex01_MethodType();
//		2. 클래스 이름 뒤에 .을 붙여 메서드 이름 작성하기
		System.out.println(ex01.add(5, 10));
		
//		static(정적인)이 있는 메서드 실행하기
//		static메서드의 경우 클래스를 선언하지 않고 클래스이름으로 바로 사용할 수 있음.
//		static을 붙이면 프로그램이 실행될때부터 바로 사용가능한 상태로 만듬
		System.out.println(Ex01_MethodType.power(2, 2));
		
		//return이 없는 메서드 실행하기
		ex01.sayHello();
		// 0으로 나눌 경우 에러 발생 => 0으로 나누지 않도록 메서드 안에서 해결
		Ex01_MethodType.divide(2, 0);
	}
	//더하기 메서드 : 반환타입=int, 메서드이름=add, 매개변수=x,y
	int add(int x, int y) {
		return x+y;
	}
	// 빼기 메서드	
	int minus(int x, int y) {
		return x-y;
	}
	// 곱하기 메서드, 반환타입=int, 메서드이름=mul, 매개변수=x,y
	int mul(int x, int y) {
		return x*y;
	}
	// 나누기 메서드, 반환타입=double, 메서드이름=div, 매개변수=x,y 
	double div(double x, double y) {
		return x/y;
	}
	// 제곱 메서드, 
	static int power(int x, int count) {
		int result = x;
		for(int i=1; i<count; i++) {
			result = result*x;
		}
//		return에 모든 식을 작성할 필요는 없음
//		반환타입에 맞춰 변수나 값을 작성할 수 있음
		return result;
	}
//	return이 없는 메서드 작성하기 : 반환타입으로 void 사용
	void sayHello() {
		System.out.println("Hello, world!");
	}
//	메서드의 실행을 특정위치에서 종료할때
	public static void divide(int x, int y) {
		if(x==0 || y==0) {
			System.out.println("0으로 나눌 수 없습니다.");
//			return의 뒤에 아무것도 적지 않으면 메서드를 종료 시킵니다.
			return;
		}
		System.out.println(x/y);
	}
}





