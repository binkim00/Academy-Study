package practice;

import java.util.Scanner;

public class q5 {

    public static void main(String[] args) {
//       ������ �ݾ��� ������ �Է� �޾� ȭ������� ���� �� ���� �ʿ����� ����ϴ� ���α׷��� �ۼ��Ͻ�
//    	��. �Է��� �ּ� 10���� ������ �Է�, ȭ������� 50000, 10000, 5000, 1000, 500, 100, 50, 10,  5, 1 10
//    	�����̸�, ������ ū ȭ������� ����, �Է��� 236,873�̸� 50000���� 4��, 10000���� 3��, 5000���� 1
//    	��, 1000���� 1��, 500�� 1��, 100���� 3��, 50���� 1��, 10���� 2��, 5���� 0��, 1���� 3���� ����϶�.
//    	�� ȭ������� �迭�� �����Ѵ�.
        int[] Moneys = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};

        Scanner sc = new Scanner(System.in);

        System.out.print("������ �ݾ��� �Է��ϼ��� (��: 236873): ");
        int amount = sc.nextInt();

        System.out.println("\n�ʿ��� ȭ�� ����:");

        for (int i = 0; i < Moneys.length; i++) {
            int money = Moneys[i];
            int count = amount / money;
            amount = amount % money;
            System.out.println(money + "����: " + count + "��");
        }
        sc.close();
    }
}
