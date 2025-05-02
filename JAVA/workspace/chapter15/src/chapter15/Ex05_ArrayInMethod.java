package chapter15;

import java.util.Arrays;

public class Ex05_ArrayInMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		메서드의 결과를 배열로 반환
		int[] arr = makeIntArray(5);
//		메서드의 매개변수로 배열을 사용
		int sum = sumOfArray(arr);
		System.out.println(sum);
		
		String str = "010-1234-5678";
		String[] strSplit = str.split("-");
		System.out.println(Arrays.toString(strSplit));
	}
	
	public static int[] makeIntArray(int len) {
//		len 매개변수로 arr배열의 길이 설정
		int[] arr = new int[len];
//		0부터 len까지의 반복문 실행
		for(int i=0; i<len; i++) {
//			0부터 len까지의 배열에 i 데이터 저장
			arr[i] = i;
		}
//		반복에서 저장한 배열을 return으로 돌려줌
		return arr;
	}
	public static int sumOfArray(int[] arr) {
		int sum = 0;
//		매개변수 arr의 배열 길이만큼 반복문을 실행
		for(int i=0; i<arr.length; i++) {
//			sum변수에 arr의 첫번째 값부터 모두 더하기
			sum = sum + arr[i];
		}
//		arr배열의 모든 값을 더한 후 return
		return sum;
	}

}







