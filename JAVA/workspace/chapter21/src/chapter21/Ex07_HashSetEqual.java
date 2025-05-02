package chapter21;

import java.util.HashSet;

// 학생 클래스 정의
class Student {
	private String name;
	private int age;

	// 생성자: 이름과 나이를 초기화
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// 객체를 문자열로 표현할 때 사용 (예: 홍길동:20)
	@Override
	public String toString() {
		return name + ":" + age;
	}

	// --------------------------
	// hashCode() 버전 1: age % 3으로 단순 계산 (비교용)
	// 중복 가능성이 높음 (충돌 많음)
	// --------------------------
//	@Override
//	public int hashCode() {
//		int num = age % 3;
//		System.out.println(num);  // 디버깅: 해시값 확인용 출력
//		return num;
//	}

	// --------------------------
	// hashCode() 버전 2: name과 age 기반으로 해시 생성
	// 실무에서는 이 방식처럼 고유 식별 값을 기준으로 해시 생성해야 정확함
	// --------------------------
	@Override
	public int hashCode() {
		int num = java.util.Objects.hash(name, age);
		System.out.println(num);  // 디버깅: 해시값 확인용 출력
		return num;
	}
//	버전1은 해시 충돌이 많이 나서 equals가 자주 호출
//	버전2는 더 고르게 분산되어 비교 횟수 줄어듦
	
	

	// equals(): 두 객체가 같은지 비교하는 메서드
	// 현재는 '나이(age)'만 같으면 같은 객체로 간주함
	@Override
	public boolean equals(Object obj) {
		System.out.println("비교를 합니다.");  // 비교 시 출력

		// 비교 대상 객체를 Student 타입으로 형 변환
		if (age == ((Student) obj).age) {
			return true;  // 나이가 같으면 true
		} else {
			return false; // 나이가 다르면 false
		}
	}
}

public class Ex07_HashSetEqual {

	public static void main(String[] args) {

		// HashSet은 중복을 허용하지 않음
		// 중복 여부는 hashCode() → equals() 순서로 판단됨
		HashSet<Student> set = new HashSet<>();

		// 동일한 age를 가진 서로 다른 객체들을 추가해보기
		// equals()에서 age만 비교하므로 "홍길동(20)"과 "전우치(20)"는 같은 객체로 판단됨
		set.add(new Student("홍길동", 20));
//		나이가 같기때문에 전우치는 저장되지 않음
		set.add(new Student("전우치", 20)); // 중복 처리될 가능성 있음
//		나이가 다르기 때문에 저장함
		set.add(new Student("홍길동", 25));

		// 저장된 객체 수 확인
		System.out.println("객체 수: " + set.size());

		// 저장된 학생 정보 출력
		for (Student s : set) {
			System.out.print(s.toString() + "\t");
		}
		System.out.println();
	}
}
