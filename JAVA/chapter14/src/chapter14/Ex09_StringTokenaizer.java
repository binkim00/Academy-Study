package chapter14;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex09_StringTokenaizer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		StringTokenizer(원본 문자열) : 공백을 기준으로 문자열을 분리함(공백생략)
//		StringTokenizer(원본 문자열,구분문자) : 구분문자를 기준으로 분리함(구분문자 생략)
//		StringTokenizer(원본 문자열,구분문자,true):구분문자도 함께 분리
		StringTokenizer st1 = new StringTokenizer("a,b,c",",",true);
//		hasMoreTokens() : 다음 글자가 있는 확인하고 있으면 true 없으면 false를 출력
		while(st1.hasMoreTokens()) {
//			nextToken() : 다음 글자를 출력
			System.out.println(st1.nextToken());
		}
//		iterater 클래스
//		has메서드로 확인하고 next메서드로 데이터를 꺼내는 클래스
		
//		split(구분문자) : StringTokenizer처럼 문자열을 분리하는 메서드
		String str = "010-1234-5678";
//		split은 구분자를 저장하는 기능이 없음
//		대신 정규표현식을 사용하여 더욱 유연하게 구분 가능
		String[] parts = str.split("-");
		System.out.println(Arrays.toString(parts));
		for(int i=0; i<parts.length; i++) {
			System.out.println(parts[i]);
		}
	}

}








