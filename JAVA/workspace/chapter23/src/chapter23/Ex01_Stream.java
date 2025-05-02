package chapter23;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Ex01_Stream {

	public static void main(String[] args) {
//		스트림 : 데이터를 선언형으로 처리하는 도구
//		코드가 훨씬 간결하고, 의도를 명확하게 표현
//		간결성, 가독성, 병렬처리 용이성
//		스트림은 일회용이다. 한 번 사용하면 다시 사용할 수 없음
//		재사용하려면 새 스트림 생성 필요
		
//		사용하는 상황
//		데이터를 필터링, 변환, 정렬 등 여러 작업을 연속으로 처리할 때
//		복잡한 for문, 중첩 if문 등을 단순화하고 싶을 때
//		데이터가 크고 병렬 처리로 성능 향상이 필요할 때

//		스트림에 사용할 배열 선언
		int [] arr = {1,2,3,4,5};
//		스트림 생성
		IntStream stm1 = Arrays.stream(arr);
//		중간연산중 하나인 filter를 실행
//		filter(매개변수->조건식) : 조건식에서 true가 나오는 데이터만 스트림에 저장
//		filter는 중간 연산이기 때문에 데이터 저장 후 스트림을 반환 
//		1,3,5 가 저장되어있는 스트림이 생성됨
		IntStream stm2 = stm1.filter( n -> n%2 == 1);
//		최종연산 : 메서드에 맞는 결과값을 출력
//		sum() : 스트림안에 있는 데이터를 모두 더하여 총합을 출력
		int sum = stm2.sum();
		
		System.out.println(sum);
		
//		파이프라인 : . 을 이용하여 메서드의 실행을 계속해서 하는 방식
		int sum2 = Arrays.stream(arr).filter( n -> n%2 == 1).sum();
		System.out.println("파이프라인으로 실행한 결과:"+sum2);
		
		
//		스트림의 구성 요소
//		1. 데이터 소스 생성
//		리스트, 배열, 셋, 맵 등
//		예: list.stream() 또는 Arrays.stream(arr)
//
//		2. 중간 연산 (Intermediate Operation)
//		스트림을 변형(필터링, 정렬, 매핑 등)
//		항상 다시 스트림을 반환
//		예: filter(), map(), sorted(), distinct()
//
//		3. 최종 연산 (Terminal Operation)
//		결과 도출 (출력, 합계, 수집 등)
//		스트림을 소비하고 닫음
//		예: forEach(), collect(), count(), sum()
	}

}







