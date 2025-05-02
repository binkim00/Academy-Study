package Ifpractice;

import java.util.Scanner;

public class q2 {

	public static void main(String[] args) {
//		한 층의 높이가 5m일 때, 건물이 몇 층인지 입력받아 높이를 출력하라.
//		몇 층인지 입력하세요>>155 
		Scanner sc = new Scanner(System.in);
		System.out.print("몇 층인지 입력하세요>> ");
		int a = sc.nextInt();
		System.out.println((a*5)+" m 입니다.");
	}

}
