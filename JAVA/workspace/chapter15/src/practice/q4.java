package practice;

import java.util.Arrays;
import java.util.Scanner;

public class q4 {

	public static void main(String[] args) {
//		������ 10�� �Է¹޾� �迭�� �����ϰ� ���� ������ �����Ͽ� ����϶�. 
		Scanner sc = new Scanner(System.in);
		int[] numbers = new int[10];

		System.out.println("���� 10���� �Է��ϼ���: ");

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextInt();
        }

        // �������� ���� (���� ����)
        for (int i = 0; i < numbers.length - 1; i++) {
            
            // ���� for��: ������ ���� �ִ� �� ���� ���ؼ� �ڸ� �ٲٱ�
            // �Ź� ���� ���� ū ���� �ϳ��� ���ĵǴϱ� �� Ƚ���� �ٿ���
            for (int j = 0; j < numbers.length - 1 - i; j++) {

                // �� ���ڰ� �� ���ں��� ũ�� �ڸ��� �ٲ۴� (�������� ����)
                if (numbers[j] > numbers[j + 1]) {

                    // �� ���� �ڸ��� �ٲٱ� ���� �ӽ� ���� temp ���
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

//      ���� ����ؼ� �����
//      Scanner sc = new Scanner(System.in);
//		int[] numbers = new int[10];
//
//		System.out.println("���� 10���� �Է��ϼ���: ");
//
//		for (int i = 0; i < numbers.length; i++) {
//			numbers[i] = sc.nextInt();
//		}
//		
//		Arrays.sort(numbers);
//		
//		System.out.print("�������� ���� ���: ");
//		for(int num : numbers) {
//			System.out.print(num + " ");
//		}
//		sc.close();
	}
}
