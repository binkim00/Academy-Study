package chapter23;

import java.util.Arrays;
import java.util.List;

public class Ex04_Sorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = Arrays.asList("홍길동","멀린","해리포터");
		
		list.stream() // 컬렉션의 경우 stream()메서드로 Stream을 생성
			.sorted() // 오름차순 정렬이 기본 설정
			.forEach(n->System.out.print(n+"\t")); // 데이터 출력
		System.out.println();
		
		list.stream()
			// 정렬 방식을 변경하고 싶을 경우 Comparator와 같이 람다식을 사용하여 변경
			.sorted((s1,s2)->s1.length() - s2.length()) //글자수를 기준으로 오름차순 정렬
			.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		
	}

}










