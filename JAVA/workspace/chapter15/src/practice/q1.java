package practice;

import java.util.Scanner;

public class q1 {

    public static void main(String[] args) {
// 		1. ���� �ҹ��ڸ� �ϳ� �Է¹ް� �� ���ں��� ���ĺ� ������ ���� ��� ���ڸ� ����ϴ� ���α׷��� �ۼ��϶�
        Scanner sc = new Scanner(System.in);
        System.out.print("�ҹ��� �ϳ� �Է�: ");
        char end = sc.next().charAt(0);

        for (int i = 0; i <= end - 'a'; i++) {
            for (char ch = (char)('a' + i); ch <= end; ch++) {
                System.out.print(ch);
            }
            System.out.println();
        }
        sc.close();
    }
}
