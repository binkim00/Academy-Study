package chapter15;

public class Ex08_EnhancedFor {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
//		for ~ each��, ������� �а� ����ϴ� �뵵�� ����
//		for(�迭�� �ڷ��� �����̸� : �迭)
		// �迭 ��� ��ü ���
		for(int e : arr) {
			System.out.print(e + " ");
		}
		System.out.println();
		
		int sum = 0;
		// �迭 ����� ��ü �� ���
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
