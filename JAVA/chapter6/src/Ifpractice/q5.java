package Ifpractice;

import java.util.Scanner;

public class q5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		.다음과 같이 AND와 OR의 논리 연산을 입력받아 결과를 출력하는 프로그램을 작성하라.  
//		예를 들어 ‘true AND false’의 결과로 false를, ‘true OR false’의 결과로 true를 출력하면 된
//		다. if문 대신 switch문을 이용하라.
//		논리 연산을 입력하세요>>true OR false 
		System.out.print("논리 연산을 입력하세요>> ");
		boolean a = sc.nextBoolean();
        String op = sc.next();
        boolean b = sc.nextBoolean();

        boolean result;

        switch (op) {
            case "AND":
                result = a && b;
                break;
            case "OR":
                result = a || b;
                break;
            default:
                System.out.println("잘못된 연산자입니다. 'AND' 또는 'OR'을 입력하세요.");
                return;
        }

//        System.out.println(result);
//        
//        System.out.print("논리 연산을 입력하세요>>");
//		boolean a = scanner.nextBoolean();  // 첫 번째 논리 값 읽기 
//		String op = scanner.next();   
//		// 논리 연산자(AND 또는 OR)를 문자열로 읽기 
//		boolean b = scanner.nextBoolean();  // 두 번째 논리 값 읽기 
//		switch(op) { 
//			case "AND": case "and": case "a":
//				System.out.println(a && b);
//				break;
//			case "OR": case "or": case "o":
//				System.out.println(a || b);
//				break;

	}

}
