package chapter18;

enum Human2 { MAN, WOMAN }
enum Machine2{ TANK, AIRPLANE }

public class Ex02_Enum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createUnit(Machine2.TANK);
		createUnit(Machine2.AIRPLANE);
//		enum에서 설정한 이름
		System.out.println("Machine2.TANK : "+Machine2.TANK);
//		enum에서 설정한 이름
		System.out.println("Machine2.TANK.name() : "+Machine2.TANK.name());
//		enum에서 설정한 이름
		System.out.println("Machine2.TANK.toString() : "+Machine2.TANK.toString());
//		enum에 저장한 순서
		System.out.println("Machine2.TANK.ordinal() : "+Machine2.TANK.ordinal());
		
		System.out.println("Machine2.values() : "+Machine2.values());
		
		System.out.println(Machine2.AIRPLANE);
// 		매개변수의 타입이 Machine2이기 때문에 에러 발생
//		createUnit(Human1.MAN); 
//		createUnit(1);
	}
	public static void createUnit(Machine2 kind) {
		switch(kind) {
//		상수 사용시 TANK의 값이 1이 설정되고 int자료형 1이면 모두 실행
		case TANK:
			System.out.println("탱크를 만듭니다.");
			break;
		case AIRPLANE:
			System.out.println("비행기를 만듭니다.");
		}
	}
}








