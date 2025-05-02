package practice;

import java.util.Scanner;

public class q2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		정수를 10개 입력받아 배열에 저장한 후, 배열을 검색하여 3의 배수만 골라 출력하는 프로그램을 작성하라.
		int[] numbers = new int[10];
		System.out.println("정수 10개를 입력하세요.");
		for (int i = 0; i < numbers.length; i++){
			numbers[i] = sc.nextInt();
		}
		System.out.println("3의 배수: ");
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 3 == 0){
				System.out.print(numbers[i] + " ");
			}
		}
		sc.close();
	}
}
