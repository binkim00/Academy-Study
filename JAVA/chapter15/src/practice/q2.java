package practice;

import java.util.Scanner;

public class q2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		������ 10�� �Է¹޾� �迭�� ������ ��, �迭�� �˻��Ͽ� 3�� ����� ��� ����ϴ� ���α׷��� �ۼ��϶�.
		int[] numbers = new int[10];
		System.out.println("���� 10���� �Է��ϼ���.");
		for (int i = 0; i < numbers.length; i++){
			numbers[i] = sc.nextInt();
		}
		System.out.println("3�� ���: ");
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 3 == 0){
				System.out.print(numbers[i] + " ");
			}
		}
		sc.close();
	}
}
