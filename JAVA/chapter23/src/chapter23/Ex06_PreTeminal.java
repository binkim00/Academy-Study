package chapter23;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Ex06_PreTeminal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = Arrays.asList(1,3,5,6,9);
		int [] arr = {1,3,5,7,9};
//		최종연산 : 마지막에 한번만 사용 가능, 최종연산전의 중간연산의 경우 여러개 사용 가능
		int sum = Arrays.stream(arr)
				.sum();
		System.out.println("sum = " + sum);
//		count : 스트림안의 데이터 개수를 반환하는 메서드
		long cnt = IntStream.of(1,3,5,7,9)
				.count();
		System.out.println("count = " + cnt);
		
//		average : 데이터의 평균을 반환하는 메서드
		IntStream.of(1,3,5,7,9)
		.average()
//		ifPresent(매개변수 -> 실행할 코드) : 매개변수가 null이면 실행하지 않고 null아니면 코드를 실행
		.ifPresent(avg -> System.out.println("avg = "+avg));
		
		IntStream.of(1,3,5,7,9)
		.min()
		.ifPresent(min -> System.out.println("min = "+min));
		
		IntStream.of(1,3,5,7,9)
		.max()
		.ifPresent(max -> System.out.println("max = "+max));
	}

}








