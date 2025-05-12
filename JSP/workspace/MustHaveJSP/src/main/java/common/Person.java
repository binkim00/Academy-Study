// 자바 빈즈 규약
// 1. 꼭 패키지 안에 생성
package common;

public class Person {
//	2. 변수 설정시 반드시 private으로 설정
	private String name;
	private int age;
//	3. 기본생성자를 반드시 작성
//	실수하지 않도록 자동완성으로 작성
//	좌측 상단 소스 탭에서 제너레이트 컨스트럭더 유징 필드 기능 사용
	public Person() {}
	public Person(String name, int age) {
	super();
	this.name = name;
	this.age = age;
}

//	4. 멤버변수의 getter/setter를 반드시 작성
//	5. getter/setter는 public으로 설정 
//  실수하지 않도록 자동완성으로 작성할 것
//	좌측 상단 소스 탭에서 제너레이트 겟터 엔 셋터 기능 사용
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
