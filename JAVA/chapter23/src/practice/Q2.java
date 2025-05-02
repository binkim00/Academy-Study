package practice;

import java.util.Arrays;
import java.util.List;

public class Q2 {

	public static void main(String[] args) {
		// 1. 리스트의 숫자 합계 출력하기
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
	
		int sum = numbers.stream()
		                 .reduce(0, (a, b) -> a + b);
		System.out.println("합계: " + sum);
		
//---------------------------------------------------------------------------		
		// 2. 문자열 연결해서 출력하기
		String[] strArr = {"Java", "Stream", "API"};
		
		String str1 = Arrays.stream(strArr)
                .reduce("", (a, b) -> a + b);
		System.out.println("공백 없는 연결: " + str1);
		
		String str2 = Arrays.stream(strArr)
							   .reduce("", (a,b) -> a + " " + b);
		System.out.println("공백 있는 연결: " + str2);
		
		
		
//---------------------------------------------------------------------------		
		// 3. 리스트의 숫자를 모두 곱한 값 출력하기
		List<Integer> numList = Arrays.asList(5,10,15);

		int result = numList.stream()
		                     .reduce(1, (a, b) -> a * b);
		System.out.println("곱셈 결과: " + result);
		
	}

}
