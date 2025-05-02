package Ifpractice;

import java.util.Scanner;

public class q3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		x 값을 입력받아 y = x²-3x+7 식을 계산하여 y 값을 출력하는 프로그램을 작성하라.
//		x 값을 입력하세요>>5 
		System.out.print("x의 값을 입력하세요>> ");
		int a = sc.nextInt();
		System.out.println("y의 값은 "+((a*a)-3*a+7)+" 입니다.");
	}

}
