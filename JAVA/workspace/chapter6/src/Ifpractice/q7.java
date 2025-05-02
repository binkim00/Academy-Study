package Ifpractice;

import java.util.Scanner;

public class q7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		학점이 A, B이면 “Excellent”, 학점이 C, D이면 “Good”, 학점이 F이면 “Bye”라고 
//		출력하는 프로그램을 작성하라. switch와 break를 활용
//		학점을 입력하세요>>B 
		System.out.print("학점을 입력하세요>> ");
		String HAK = sc.next();
		
		
		switch(HAK) {
		case "A" :
		case "B" :
			System.out.println("Excellent!!");
			break;
		case "C" :
		case "D" :
			System.out.println("Good!");
			break;
		case "F" :
			System.out.println("Bye...");
			break;
		default :
			System.out.println("학점을 기입해주세요.");
		}
		
		
		
	}

}
