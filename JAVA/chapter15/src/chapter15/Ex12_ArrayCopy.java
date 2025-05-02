package chapter15;

import java.util.Arrays; // Arrays Ŭ������ ����ϱ� ���� import

public class Ex12_ArrayCopy {

    public static void main(String[] args) {
        int[] arr1 = new int[10];  // ũ�Ⱑ 10�� ������ �迭 arr1 ����
        int[] arr2 = new int[8];   // ũ�Ⱑ 8�� ������ �迭 arr2 ����
        
        // Arrays.fill�� ����� arr1 �迭�� ��� ���� 3���� ä��
        Arrays.fill(arr1, 3);  // arr1 �迭�� 3�� ä���� -> {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}

        // arr1�� 0�� �ε������� arr2�� 3�� �ε����� 4���� ���� ����
        System.arraycopy(arr1, 0, arr2, 3, 4);
        // arr1[0], arr1[1], arr1[2], arr1[3]�� ���� arr2[3], arr2[4], arr2[5], arr2[6]�� �����
        
        // arr1 �迭 ���
        for (int i = 0; i < arr1.length; i++)  // arr1 �迭�� ���̸�ŭ �ݺ�
            System.out.print(arr1[i] + " ");    // �� ���Ҹ� ���
        System.out.println();  // ��� �� �� �� �ٲ�
        
        // arr2 �迭 ���
        for (int i = 0; i < arr2.length; i++)  // arr2 �迭�� ���̸�ŭ �ݺ�
            System.out.print(arr2[i] + " ");    // �� ���Ҹ� ���
        System.out.println();  // ��� �� �� �� �ٲ�
        
        // Arrays.copyOfRange �޼��带 ����ؼ� arr2 �迭���� 2�� �ε������� 5�� �ε������� ����
        int[] arr3 = Arrays.copyOfRange(arr2, 2, 5);
        // arr2[2], arr2[3], arr2[4]�� ���� ���ο� �迭 arr3�� �����
        
        // arr3 �迭 ���
        for (int i = 0; i < arr3.length; i++)  // arr3 �迭�� ���̸�ŭ �ݺ�
            System.out.print(arr3[i] + " ");    // �� ���Ҹ� ���
        System.out.println();  // ��� �� �� �� �ٲ�
        
//        �迭 ����� �ż��尡 �ʿ��� ����
//        �迭�� ��ü�̱� �빮�� �׳� �����ϸ� �޸� �ּҸ� 
//        �����ϰ� �����ʹ� �Ѵ� �Ȱ��� ����ϰԵ�
          int[] arr4 = {1, 2, 3, 4};
          // �迭�� �ٸ� �迭�� ����
          int[] arr5 = arr4;
          System.out.println(Arrays.toString(arr4));
          System.out.println(Arrays.toString(arr5));
          // ������ �迭�� �����͸� ����
          arr4[0] = 1000;
          arr5[3] = 2000;
          System.out.println(Arrays.toString(arr4));
          System.out.println(Arrays.toString(arr5));
//        �迭�� = �� �����ϸ� �����Ͱ� ����Ǵ� �� �ƴ϶� �޸� �ּ�(������)�� ����
          
          // �ż��� ��� ��, ���� �忡�� ����
          int[] arr6 = {1, 2, 3, 4};  
          int[] arr7 = Arrays.copyOf(arr6, arr6.length);  // ������ ����

          arr6[0] = 1000;      // arr1 �� ����
          arr7[3] = 2000;      // arr2 �� ����

          System.out.println(Arrays.toString(arr6));  // [1000, 2, 3, 4]
          System.out.println(Arrays.toString(arr7));  // [1, 2, 3, 2000]
          
//        String�� ��� �����Ͽ��� �޸� �ּҸ� �����ϴ°� �ƴ� �����͸� ����
          String a = "a";
          String b = a;
          System.out.println(a);
          System.out.println(b);
          b = "b";
          System.out.println(a);
          System.out.println(b);

          
    }

}
