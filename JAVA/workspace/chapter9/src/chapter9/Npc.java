package chapter9;

// 코드 자동정렬 단축키 : ctrl + shift + f

// 클래스란 : 객체와 관련된 데이터와 처리동작을 한데 모은 코드 뭉치
//         인스턴스      변수     메서드        인스턴스 생성 코드(설계도)
public class Npc {
//	멤버 변수 - 데이터를 저장하는 용도
	private String name;
	private int hp;

//	멤버 변수에 데이터를 설정하는 메서드 -> set메서드, setter
	void setHp(long amount) {
//		hp를 1~100 사이의 값만 저장할 수 있도록 제한
		if (amount < 1) {
			amount = 1;
		} else if (amount > 100) {
			amount = 100;
		}
		this.hp = (int) amount;
	}

	void setName(String name) {
//		this는 자기자신(인스턴스 자신)을 의미
//		this와 . 을 이용하여 메서드, 멤버변수를 사용할 수 있음.
//		멤버변수 = 매개변수
//		이름의 글자수가 2~10글자만 저장되도록 제한
		if (name.length() < 2) {
			System.out.println("두글자 이상 입력해주세요.");
			name = "재설정필요";
		} else if (name.length() > 10) {
			System.out.println("10글자 이하로 입력해주세요.");
			name = "재설정필요";
		}
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public String getName() {
		return name;
	}
//	getter/setter 자동완성하기
//	1.source탭 열기 -> 2.Generate getters and setters 항목 클릭하기 ->
//	3.getter,setter를 생성할 멤버 변수 선택하기 -> 4. generate버튼 누르기

	public void say() {
		System.out.println("안녕하세요. 저는 " + this.name + "입니다.");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		클래스를 이용한 인스턴스 생성
//		클래스 인스턴스명 = new 생성자(); 
		Npc n = new Npc();
//		클래스에 데이터 설정하기
//		직접적으로 변수에 데이터를 저장하는 방식
		n.hp = -10000;
		n.name = "경비00000000000000000";
//		직접적으로 변수명을 사용하는 방식
		System.out.println("이름:" + n.name);
		System.out.println("hp:" + n.hp);

//		각 변수에 맞는 setter를 사용하여 데이터 설정
		n.setHp(-10000);
		n.setName("경비00000000000000000");
//		클래스에서 데이터 꺼내쓰기
//		getter를 사용하는 방식
		System.out.println("이름:" + n.getName());
		System.out.println("hp:" + n.getHp());

//		직접 설정하는 방식을 사용하지 않는 이유
//		데이터 설정시 특정한 범위의 데이터를 저장하거나 
//		특정한 조건이 있는 경우 제한 할 수 없음

		n.say();

	}

}
