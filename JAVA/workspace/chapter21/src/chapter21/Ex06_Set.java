package chapter21;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ex06_Set {

	public static void main(String[] args) {
		// Set으로 자료형을 설정하면 Set을 상속받는 클래스를 모두 저장할 수 있다.
		// → 즉, Set<String>으로 선언하면 HashSet<String>, TreeSet<String> 등을 모두 담을 수 있음
		Set<String> set = new HashSet<>();

		// 실행결과 : orange banana apple
		// 중복이 제거되고 저장된 순서가 아닌 다른 순서로 출력
		// → HashSet은 입력한 순서를 기억하지 않음. 그리고 "apple"은 중복이므로 하나만 저장됨.
		// hashCode() : 객체를 숫자로 변환하여 반환, orange를 hashCode로 변환하면 -1008851410
		// hashCode가 같으면 중복 데이터로 취급한다.
		System.out.println("orange".hashCode());
		set.add("apple");
		System.out.println("apple".hashCode());
		set.add("apple");
		System.out.println("apple".hashCode());
		set.add("banana");
		System.out.println("banana".hashCode());
		// set.get(인덱스) 사용할 수 없음
		// Set의 경우 인덱스가 존재하지 않기 때문에
		// → 리스트처럼 0번, 1번 요소를 직접 꺼내는 건 불가능함.

		// 현재 저장된 데이터 개수 출력 (중복은 제거된 상태로 계산됨)
		System.out.println("객체 수: " + set.size());

		// 인덱스를 이용한 출력이 불가능하기 때문에 Iterator나 foreach문으로 출력해야함
		// Iterator를 이용한 출력 방식
		// Iterator는 컬렉션을 반복해서 출력할 수 있는 도구
		for (Iterator<String> itr = set.iterator(); itr.hasNext();) {
			System.out.print(itr.next() + "\t");  // 하나씩 값을 꺼내서 출력
		}

		System.out.println();

		// remove(데이터) : 인덱스가 없기 때문에 데이터를 기준으로 삭제
		// → 리스트처럼 0번 인덱스를 삭제하는 게 아니라 "banana"라는 값을 삭제함
		set.remove("banana");

		// 향상된 for문을 사용한 출력 방식 (Iterator보다 간단)
		for (String s : set) {
			System.out.print(s + "\t");  // 하나씩 출력
		}
		System.out.println();
	}
}
