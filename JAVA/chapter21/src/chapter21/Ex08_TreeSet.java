package chapter21;

import java.util.Set;
import java.util.TreeSet;

public class Ex08_TreeSet {
	public static void main(String [] args) {
		
		// -------------------------------
		// 문자열(String)을 TreeSet에 저장
		// -------------------------------
		
		// Set은 중복을 허용하지 않음
		// TreeSet은 자동으로 오름차순 정렬까지 해줌
		Set<String> tree = new TreeSet<>();

		// 다양한 문자 데이터 추가
		// 문자 : 한글(가~다), 영어, 숫자 포함
		tree.add("홍길동");
		tree.add("전우치");
		tree.add("손오공");
		tree.add("멀린");
		tree.add("홍길동"); // 중복이므로 무시됨
		tree.add("가");     // 한글 초성
		tree.add("나");
		tree.add("다");
		tree.add("1");      // 숫자 문자열
		tree.add("10");
		tree.add("apple");  // 영어 소문자
		tree.add("orange");

		// TreeSet은 자동으로 정렬해줌 (문자 기준 오름차순)
		// 정렬 기준: 유니코드 순서 → 숫자 → 영어 → 한글
		for(String t : tree) {
			System.out.print(t + "\t");
		}
		System.out.println();

		// -------------------------------
		// 정수(Integer)를 TreeSet에 저장
		// -------------------------------
		
		Set<Integer> tree2 = new TreeSet<>();

		// 숫자를 순서 없이 추가
		tree2.add(6);
		tree2.add(2);
		tree2.add(7);
		tree2.add(1);
		tree2.add(4);
		tree2.add(8);
		tree2.add(3);
		tree2.add(5);

		// TreeSet은 정수도 자동으로 오름차순 정렬
		for(int t : tree2) {
			System.out.print(t + "\t");
		}
		System.out.println();
	}
}
