package practice;

public class q8 {

    public static void main(String[] args) {
//    	 ũ�Ⱑ 10�� �迭�� �ۼ��ϰ� Math.random()�Լ��� ����Ͽ� 1~100������ ���ڸ� �����ϰ� ������
//    	 �� ������ ���ڰ� 3�� ����� �͸� �迭�� �����ϴ� ���α׷��� �ۼ��϶�. (�� �迭�� �� ��Ҵ� ���� 
//    	 �ٸ� ���� ����ǵ��� �Ѵ�
        int[] arr = new int[10];
        int count = 0;         

        while (count < 10) {
            int num = (int)(Math.random() * 100) + 1;

            if (num % 3 != 0) {
                continue;
            }

            for (int i = 0; i < count; i++) {
                if (arr[i] == num) {
                    num = 0;
                    break;
                }
            }

            if (num != 0) {
                arr[count] = num;
                count++;
            }
        }

        System.out.println("3�� ����� ������ �迭 (�ߺ� ����):");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
