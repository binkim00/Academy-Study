package chapter2;

public class Ex01_VariableType {

	public static void main(String[] args) {
		// 자바스크립트의 경우 모든 변수의 앞에 let 사용
		// 자바의 경우 따로 자료형을 입력 해줘야 함
		
		// 정수 숫자 자료형
		// byte : -128 ~ 127
		byte b=127;
		// short : -32,768 ~ 32,767
		short s=32767;
		// int : -2,147,483,648 ~ 2,147,483,647
		int i=2147483647;
		// long : -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807
		// long 자료형의 경우 뒤에 영어L을 붙여야 에러가 발생하지 않음
		long l=9223372036854775807L;
		// byte, short는 거의 사용하지 않고 기본은 int로 사용
		// 21억보다 큰 숫자를 저장하는 경우에만 long 사용
		
		// 문자 자료형
		// char : 한글자만 저장할 수 있는 자료형, 작은 따옴표로 감싸서 사용
		char c = 'A';
		System.out.println(c);
		c = 54970; // 아스키코드, 유니코드로 저장 가능
		System.out.println(c);
		// String : 여러 글자를 저장할 수 있는 자료형, 자주 사용
		// 대문자로 시작해야 함, 위에 사용한 소문자 자료형은 기본 자료형
		// 첫글자가 대문자인 경우는 클래스 자료형
		// 큰 따옴표로 감싸서 사용
		String str = "안녕하지비";
		//클래스 자료형의 경우 .을 이용하여 여러 가지 기능 사용가능
		System.out.println(str);
		System.out.println(str.length());
		// 기본 자료형에 해당하는 클래스 자료형이 모두 있음
		Byte by = 'a';
		Short sh = 35;
		Integer in = 21; // int만 클래스 자료형의 이름이 다르다.
		Long lo = 98L;
		
		// 실수 자료형 : 소수점을 저장하는 자료형
		float f = 3.14F; // 소수점 아래 7자리
		double d = 3.14; //소수점 아래 15자리, 기본
		
		// 컴퓨터는 소수점 계산이 정확하지 않음
		double num1 = 1.0000001;
		System.out.println(num1);
		double num2 = 2.0000001;
		System.out.println(num2);
		double result = num1 + num2;
		System.out.println(result);
		
		
	}

}








