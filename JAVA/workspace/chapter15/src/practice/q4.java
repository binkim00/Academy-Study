package practice;

import java.util.Arrays;
import java.util.Scanner;

public class q4 {

	public static void main(String[] args) {
//		정수를 10개 입력받아 배열에 저장하고 증가 순으로 정렬하여 출력하라. 
		Scanner sc = new Scanner(System.in);
		int[] numbers = new int[10];

		System.out.println("정수 10개를 입력하세요: ");

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextInt();
        }

        // 오름차순 정렬 (버블 정렬)
        for (int i = 0; i < numbers.length - 1; i++) {
            
            // 안쪽 for문: 실제로 옆에 있는 두 수를 비교해서 자리 바꾸기
            // 매번 끝에 가장 큰 수가 하나씩 정렬되니까 비교 횟수를 줄여감
            for (int j = 0; j < numbers.length - 1 - i; j++) {

                // 앞 숫자가 뒷 숫자보다 크면 자리를 바꾼다 (오름차순 정렬)
                if (numbers[j] > numbers[j + 1]) {

                    // 두 값의 자리를 바꾸기 위해 임시 변수 temp 사용
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

//      정렬 사용해서 만들기
//      Scanner sc = new Scanner(System.in);
//		int[] numbers = new int[10];
//
//		System.out.println("정수 10개를 입력하세요: ");
//
//		for (int i = 0; i < numbers.length; i++) {
//			numbers[i] = sc.nextInt();
//		}
//		
//		Arrays.sort(numbers);
//		
//		System.out.print("오름차순 정렬 결과: ");
//		for(int num : numbers) {
//			System.out.print(num + " ");
//		}
//		sc.close();
	}
}
