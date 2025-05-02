package chapter14;

import java.util.Scanner;

public class Ex04_StringMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		indexOf("찾고싶은 문자열") : 문자열의 위치를 찾아서 돌려주는 메서드
		String str = "AppleBananaOrange";
		System.out.println(str.indexOf("a")); // 6
		System.out.println(str.indexOf("Banana")); // 5
		System.out.println(str.indexOf("na")); // 7
//		indexOf("문자열",시작위치) : 시작위치부터 문자열을 찾아서 돌려주는 메서드
		System.out.println(str.indexOf("na",7)); // 7
		System.out.println(str.indexOf("na",8)); // 9
		if(str.indexOf("Berry")>0) {
			System.out.println("문자열에 안에 Berry가 있습니다.");
		}else {
			System.out.println("문자열에 Berry가 없습니다.");
		}
//		substring(시작위치, 종료위치): 시작위치부터 종료위치 까지의 문자열을 돌려주는 메서드
		System.out.println(str.substring(0,5)); // Apple
//		substring(시작위치) : 시작위치부터 문자열의 끝까지 돌려주는 메서드
		System.out.println(str.substring(5)); // BananaOrange

//		length() : 문자열의 안에 있는 글자 개수를 돌려주는 메서드
		System.out.println(str.length());
//		charAt(문자위치) : 문자위치에 맞는 문자를 돌려주는 메서드
		System.out.println(str.charAt(0));
		System.out.println(str.charAt(1));
		System.out.println(str.charAt(3));
//		String.valueOf(값) : 다른 타입의 자료형을 String로 바꿔주는 메서드 
		double e = 2.718281;
//		다른 타입의 변수를 String에 저장하는 경우에 사용하는 메서드
		String se = String.valueOf(e);
		Double e2 = 2.718281;
		String se2 = e2.toString();
		System.out.println(se);
		System.out.println(se2);
//		toLowerCase() : 대문자를 소문자로
		System.out.println(str.toLowerCase());
//		toUpperCase() : 소문자를 대문자로
		System.out.println(str.toUpperCase());
//		trim() : 양쪽 끝의 공백을 삭제하여 돌려주는 메서드
		System.out.println("  tr  im  ".trim());
		
//		문자열 연산하기
//		concat(더하고 싶은 문자열):문자열을 더해주는 메서드
		System.out.println(str.concat("Berry"));
//		문자열에 +, += 을 사용하면 자동으로 concat메서드를 실행한다.
		System.out.println(str + "Melon");
		str += "Melon";
		
	}

}









