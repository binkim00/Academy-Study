package chapter15;

import java.util.Arrays;  // �迭 ���� ���� �޼��带 �����ϴ� Arrays Ŭ���� import

public class Ex13_ArrayEquals {

    public static void main(String[] args) {
        // ũ�Ⱑ 5�� ���� �迭 arr1�� �����ϰ� �� {1,2,3,4,5}�� �ʱ�ȭ
        int[] arr1 = {1, 2, 3, 4, 5};

        // Arrays.copyOf�� ����ؼ� arr1�� ����, arr1 ���̸�ŭ �����ؼ� arr2�� ����
        // Arrays.copyOf(�����迭, �������)
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        // �� �������� arr2 = {1, 2, 3, 4, 5} �� ��

        // �� �迭�� ������ ���ϴ� �ڵ�
        boolean bCheck = Arrays.equals(arr1, arr2);
        // equals �޼���� �迭�� ���̿� ���� ��� ������ true, �ٸ��� false�� ��ȯ��

        // �� ��� ���
        System.out.println(bCheck);  // arr1�� arr2�� ���� ���� ���̵� �����Ƿ� true ���
    }
}
