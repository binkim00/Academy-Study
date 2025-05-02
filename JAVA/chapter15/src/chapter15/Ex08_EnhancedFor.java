package chapter15;

public class Ex08_EnhancedFor {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
//		for ~ each문, 순서대로 읽고 사용하는 용도만 가능
//		for(배열의 자료형 변수이름 : 배열)
		// 배열 요소 전체 출력
		for(int e : arr) {
			System.out.print(e + " ");
		}
		System.out.println();
		
		int sum = 0;
		// 배열 요소의 전체 합 출력
		for(int e : arr) {
			sum = sum + e;
		}
		System.out.println("sum: " + sum);

		String[] strArr = {"a", "b", "c"};
		for(String str : strArr) {
			System.out.print(str);
		}
		
	}

}
