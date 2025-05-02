package practice;

import java.util.Scanner;

public class q1 {

    public static void main(String[] args) {
// 		1. 영문 소문자를 하나 입력받고 그 문자보다 알파벳 순위가 낮은 모든 문자를 출력하는 프로그램을 작성하라
        Scanner sc = new Scanner(System.in);
        System.out.print("소문자 하나 입력: ");
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
