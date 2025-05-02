package chapter9;

import chapter9_1.Student1;

// 접근제한자 생략 : 같은 패키지 내부에서만 접근 가능
// public : 패키지에 관계없이 어디서든 접근 가능
// private : 같은 클래스 내부에서만 접근 가능
// protected : 같은 패키지 내부와 상속 관계의 클래스 에서만 접근 가능
class Student2{
	public String name;
	private int age;
	public Student2(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age<0 || age>150) {
			System.out.println("나이가 부적절합니다.");
			this.age = 0;
			return;
		}
		this.age = age;
	}
}

public class PrivateUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student1 student1 = new Student1();
//		접근제한자 생략의 경우 패키지가 다르면 변수를 사용할 수 없음
//		student1.name = "홍길동";
//		student1.age = -100;
//		System.out.printf("%s의 나이는 %d살입니다. \n", student1.name, student1.age);
		
		Student2 student2 = new Student2("전우치", 20);

		student2.name = "손오공";
//		private 변수는 다른 클래스에서 직접적으로 접근할 수 없음
//		student2.age = 10;
//		private변수는 public으로 만들어진 getter, setter메서드로 사용할 수 있음
		student2.setAge(-10);
		int age = student2.getAge();
		System.out.printf("%s의 나이는 %d살입니다. \n", student2.name, age);
	}

}









