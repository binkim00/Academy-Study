package chapter23;

import java.util.Arrays;
import java.util.List;

public class Ex07_Reduce {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("홍길동","전우치","손오공");
//		stream이 실행되고 난 후 데이터를 저장할 변수 설정
		String name1 = list1.stream()
//				         기본값    첫번째, 두번째
				.reduce("이순신", ( s1,    s2 )->
//				                   >    >=
//				첫번째 실행의 결과 : 홍길동 이순신
//				두번째 실행의 결과 : 전우치 이순신
//				세번째 실행의 결과 : 손오공 이순신
				s1.length() >= s2.length() ? s1 : s2);
		System.out.println(name1);
		
		List<String> list2 = Arrays.asList("홍길동","멀린","해리포터");
		String name2 = list2.stream()
				.reduce("이순신", (s1,s2)->
//				첫번째 : 이순신
//				두번째 : 이순신
//				세번째 : 해리포터
				s1.length() >= s2.length() ? s1 : s2);
		System.out.println(name2);
	}

}








