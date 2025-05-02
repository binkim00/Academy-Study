package chapter21;

import java.util.ArrayList;
import java.util.List;

public class Ex01_ArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List는 인터페이스이기 떄문에 List를 상속받는 자료구조는 모두 저장할 수 있음.
		List<String> list = new ArrayList<>();
//		add(저장할 데이터) : 리스트에 데이터를 저장하는 메서드
		list.add("orange");
		list.add("apple");
		list.add("apple");
		list.add("banana");
//		size() : 리스트에 저장되어 있는 데이터의 개수를 반환
//		length : 배열에 설정된 길이를 반환, 저장한 데이터와 관련 없음
		for(int i=0; i<list.size(); i++) {
//			get(인덱스) : list의 데이터를 반환하는 메서드
			System.out.print(list.get(i) + "\t");
		}
		System.out.println();
//		remove(인덱스) : 인덱스번째의 데이터를 삭제
		list.remove(0);
		
//		foreach(향상된for문) 을 사용한 데이터 출력
//리스트 안 데이터의 타입  변수이름    반복할 리스트
		for(String    str    :   list) {
//			get()을 사용하지 않고 데이터를 출력할 수 있음
			System.out.print(str + "\t");
		}
		System.out.println();
		
	}

}









