package chapter7;

public class Ex02_VariableScope {
//	클래스 변수(멤버 변수) : 클래스 이름 아래에(안에) 작성하는 static 변수
	static int staticA = 10;
//	인스턴스 변수(멤버 변수) : 클래스 이름 아래에(안에) 작성하는 변수
	int a = 0;
//	지역 변수(로컬 변수) : 메서드 이름 아래에(안에) 작성하는 변수
//	매개 변수(파라미터 parameter) : 메서드 선언부, 메서드의 이름 뒤에 괄호에 적는 변수
	
//	main메서드는 프로그램을 실행하는 메서드로 작성되어있는 클래스와 전혀 관련이 없음.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		main메서드 안의 어디서든 사용 가능 = myState, num1
//		main메서드의 중괄호 안에서 선언한 변수는 main의 어디서든 사용가능
//		지역변수 : main메서드 안에서 선언했기 때문에 main안에서만 사용 가능
		boolean myState = true;
		int num1 = 11;
		
		if(myState) {
			num1++;
			System.out.println(num1);
			System.out.println(myState);
		}
		
//		{} 중괄호만 사용해서 코드를 작성하는 것이 가능하지만 일반적으로 사용하지 않음
		{
			int num2 = 33;
			num2++;
			System.out.println(num2);
		}
//		{}중괄호 안에서 선언한 변수는 중괄호 밖에서 사용할 수 없습니다.
//		System.out.println(num2);
		
//		for문의 괄호 안에서 작성한 i변수는 for문 안에서만 사용 가능
		for(int i=0; i<3; i++) {
			System.out.println(i);
		}
//		System.out.println(i);
//		System.out.println(staticA+","+a);
	}
	int add(int x, int y) {
//		클래스 변수는 클래스 안에 존재하는 main을 제외한 모든 메서드에서 사용가능
		System.out.println(staticA+","+a);
//		매개변수 x,y 는 add메서드 안에서만 사용 가능
		return x+y;
	}

}










