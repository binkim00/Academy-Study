package chapter6;

public class StringSwitchAndIf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "홍길동";
		switch(name) {
		case "홍길동" :
			System.out.println("제 이름은 홍길동 입니다.");
			break;
		case "전우치" :
			System.out.println("제 이름은 전우치 입니다.");
			break;
		case "손오공" :
			System.out.println("제 이름은 손오공 입니다.");
			break;
		default :
			System.out.println("같은 이름이 없습니다.");
		}
		
		
		String name2 = "홍길동";
		String name3 = new String("홍길동"); // 클래스 서넌문으로 새롭게 변수를 생성
		System.out.println(name == name2); // true가 출력되지만 상황에 따라 false 출력될 가능성도 있음
		System.out.println(name2 == name3); // 메모리 주소가 다르기 때문에 false 출력
		System.out.println(name2.equals(name3));
		// == 으로는 비교가 불가능 하기 때문에 equals 사용
		// == 메모리 비교, equals 안의 데이터를 비교
		// String의 경우 클래스 자료형이기 때문에 equals로 비교해야 결과 정상
		
		// 기초자료형 : byte, short, int, long, float, double, char, boolean 는 == 으로 비교
		// 클래스 자료형 : Byte, Short, Integer, Long, Float, Double, Boolean, Character 등은 equals 사용
		
		// if문의 경우 문자열 비교시 == 를 사용하여 비교할 수 없음/내용이 짧으면 가능하기도 함.
		if(name2.equals(name3)) {
			System.out.println("name2와 name은 같지 않습니다");
		}

	}

}
