package chapter22;

interface Unit7{
	void move(String s);
//	void move2(String s);
}
public class Ex07_Lambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		람다식의 경우 인터페이스에 있는 하나의 메서드를 구현할 수 있음.
//		인터페이스 안에 미구현메서드가 2개 이상 있는 경우 람다식을 사용할 수 없음.
//		람다식을 이용하면 여러가지 메서드 작성 문법을 생략할 수 있음. 
//		               매개변수   람다식화살표 
		Unit7 unit =     (s)    ->
//		실행할 코드
		{
			System.out.println(s);
		};
		unit.move("람다식으로 실행");
		
		Unit7 unit2 = new Unit7() {
			@Override
			public void move(String s) {
				System.out.println(s);
			}

//			@Override
//			public void move2(String s) {
//				System.out.println(s);
//			}
			
		};
		unit2.move("익명 내부 클래스로 실행");
	}

}













