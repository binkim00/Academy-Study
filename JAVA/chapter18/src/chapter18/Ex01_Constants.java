package chapter18;

interface Human1{
	int MAN = 1;
	int WOMAN = 2;
}
interface Machine1{
	int TANK = 1;
	int AIRPLANE = 2;
}

public class Ex01_Constants {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createUnit(Machine1.TANK);
		createUnit(Human1.MAN);
		createUnit(1);
	}
	public static void createUnit(int kind) {
		switch(kind) {
//		상수 사용시 TANK의 값이 1이 설정되고 int자료형 1이면 모두 실행
		case Machine1.TANK:
			System.out.println("탱크를 만듭니다.");
			break;
		case Machine1.AIRPLANE:
			System.out.println("비행기를 만듭니다.");
		}
	}
}








