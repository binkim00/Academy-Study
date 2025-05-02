package chapter15;

import java.util.Arrays;  // �迭 ���� ���� �޼��带 �����ϴ� Arrays Ŭ���� import
import java.util.Collections;

public class Ex14_ArraySort {

    public static void main(String[] args) {
        // ������ �迭 arr1�� �����ϰ� �� �ʱ�ȭ
        int[] arr1 = {1, 3, 2, 4};
        // �Ǽ��� �迭 arr2�� �����ϰ� �� �ʱ�ȭ
        Double[] arr2 = {4.4, 3.3, 2.2, 1.1};
        // ���ڿ� �迭 arr3�� �����ϰ� �� �ʱ�ȭ
        String[] arr3 = {"ȫ�浿", "����ġ", "�տ���", "�ָ�"};

        // int �迭�� ��� �������� �ۿ� �� �� ����
        // Integer �迭�� ��� �� �� ��� ����
        // double�� Double�� ��밡��
        // Ŭ���� �ڷ����� �����Ÿ� �Ѵ� ����. �����ڷ����� ���������� ����
        
        // int �迭�� ������������ ���� (���� �� �� ū ��)
        Arrays.sort(arr1);
        // double �迭�� ������������ ����
        Arrays.sort(arr2, Collections.reverseOrder());
        // String �迭�� ������������ ���� (�����ڵ� ����, ������ ��)
        Arrays.sort(arr3);

        // ���� for���� �̿��ؼ� arr1 ���
        for(int n : arr1)
            System.out.print(n + "\t");  // ���� ���
        System.out.println();  // �ٹٲ�

        // arr2 ��� (�Ǽ� �迭)
        for(double d : arr2)
            System.out.print(d + "\t");  // �Ǽ� ���
        System.out.println();  // �ٹٲ�

        // arr3 ��� (���ڿ� �迭)
        for(String s : arr3)
            System.out.print(s + "\t");  // ���ڿ� ���
        System.out.println();  // �ٹٲ�
    }
}
