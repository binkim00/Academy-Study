package chapter9;



class Calc{
//	메서드의 이름이 같아도 매개변수의 개수가 다르거나 자료형이 다르다면 다른 메서드로 생성 가능
//	메서드 이름 : add, 매개변수 = int가 두개
	int add(int a, int b) {
		return a+b;
	}
//	메서드 이름 : add, 매개변수 = int가 한개
	int add(int a) {
		return a+1;
	}
//	반환타입은 다르지만 자료형의 개수가 같기때문에 에러 발생
//	double add(int a) {
//		return a+1;
//	}
//	메서드 이름 : add, 매개변수 = double 두개
	double add(double a, double b) {
		return a+b;
	}
//	메서드 이름 : add, 매개변수 = double 한개
	int add(double a) {
		return (int)a+1;
	}

}

public class OverloadingUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 메서드 오버로딩 : 매개변수의 개수나 자료형을 다르지만 
		// 메서드명은 같은 메서드를 여러개 정의하는 방식
		// 필요이유
		// 같은 이름으로 메서드 작성시 자료형이 달라도 실행되도록 만들기 위해서
		// 예시 : System.out.println();
		int i = 10;
		double d = 3.14;
		String s = "홍길동";
//		println을 메서드 오버로딩을 이용하여 작성했기 때문에 매개변수의 타입에
//		상관없이 사용 가능합니다.
		System.out.println(i);
		System.out.println(d);
		System.out.println(s);
		System.out.println();
	}

}










