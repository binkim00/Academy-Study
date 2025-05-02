package chapter21;

import java.util.Set;
import java.util.TreeSet;

// 사용자 정의 클래스 Student3
// TreeSet에서 정렬을 하려면 Comparable 인터페이스를 반드시 구현해야 함!
// Comparable 인터페이스 : 클래스에 오름차순 내림차순 정렬시 사용하는 
// 						compareto메서드를 추가해주는 인터페이스
// 클래스마다 1가지 방식으로만 정렬가능
// 클래스를 수정(상속)해야만 정렬방식을 설정할 수 있음
// 결론 : 기본 정렬방식을 설정
class Student3 implements Comparable<Student3> {
	private String name;  // 학생 이름
	private int age;      // 학생 나이

	// 생성자: 이름과 나이를 받아서 객체 생성
	public Student3(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// 객체를 문자열로 출력할 때 사용할 형식 지정
	@Override
	public String toString() {
		return name + "," + age;  // 예: 홍길동,30
	}

	// compareTo 메서드는 TreeSet에서 자동 정렬 기준을 정의하는 데 필요함
	// 여기서는 나이(age)를 기준으로 오름차순 정렬
	@Override
	public int compareTo(Student3 o) {
		return this.age - o.age;  // 나이가 작을수록 먼저 나옴(오름차순)
		// return o.age - this.age; // 반대로 하면 내림차순
		// return this.name.compareTo(o.name); // 이름 오름차순 정렬
		// return o.name.compareTo(this.name); // 이름 내림차순 정렬
	}
	
}

public class Ex09_Comparable {

	public static void main(String[] args) {
		// TreeSet은 중복을 허용하지 않고, 자동으로 정렬되는 Set
		// Student3 객체를 저장하려면 정렬 기준(compareTo)을 정의해야 함
		Set<Student3> tree = new TreeSet<>();

		// Student3 객체들을 TreeSet에 추가 (자동으로 나이순 정렬됨)
		tree.add(new Student3("홍길동", 30));
		tree.add(new Student3("전우치", 40));
		tree.add(new Student3("손오공", 20));

		// 저장된 객체들을 출력 (오름차순 정렬된 상태로 출력됨)
		for (Student3 s : tree) {
			System.out.println(s);  // toString()에 의해 "이름,나이" 형식으로 출력됨
		}
	}
}
