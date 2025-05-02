package chapter21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ex02_LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String> list = new LinkedList<>();
		list.add("orange");
		list.add("apple");
		list.add("apple");
		list.add("banana");
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + "\t");
		}
		System.out.println();
		list.remove(0);
		
		for(String str : list) {
			System.out.print(str + "\t");
		}
		System.out.println();
		
//		Iterator : 반복자
//		반복자 선언시 제네릭으로 리스트의 제네릭을 설정
		Iterator<String> itr = list.iterator();
//		hasNext() : 다음 데이터가 있으면 true 없으면 false를 반환하는 메서드
		while(itr.hasNext()) {
//			next() : 다음 데이터를 반환하는 메서드
//			다음 데이터가 없는 경우 에러 발생 후 프로그램 강제 종료
//			next는 반드시 hasNext와 세트로 사용해야함
			String str = itr.next();
			System.out.print(str + "\t");
//			데이터가 apple이라면 삭제하는 if문
			if(str.equals("apple")) {
//				iterator에서 remove를 이용하여 list의 내용을 삭제
				itr.remove();
			}
		}
		System.out.println();
		
		itr = list.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next() + "\t");
		}
		System.out.println();
	}

}















