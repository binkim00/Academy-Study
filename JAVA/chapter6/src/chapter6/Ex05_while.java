package chapter6;

import java.util.Scanner;

public class Ex05_while {

	public static void main(String[] args) {
		// while문 : 반복횟수 정해져있지 않음
//		while(조건식) {
//			증강식;
//			실행할 코드
//		}
		int start = 0;
		while(start<10) {
			start++;
			System.out.println(start+"번 실행했습니다.");
		}
		
//		do while문 : 실행문을 위에 조건식을 아래에 적는 방식
//		특징: 조건식이 false더라도 반드시 한번은 실행됨
		int start2 = 0;
		do {
			System.out.println(start2+"번 실행했습니다.");
			start2++;
		}while(start2 < -1); // false 값이지만 한번은 실행함
//		사용자 입력을 최초로 한번은 받아야 하는 경우
//		1. 로그인 : 아이디와 비번을 입력 받아 맞으면 반복이 끝나고 틀리면 계속반복
//		2. 가위바위보에서 이길 때까지 실행해야하는 경우
		
//		while 무한 반복
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("가위, 바위, 보 중에 입력해주세요.");
			System.out.println("게임을 종료하려면 종료 혹은 q라고 입력해주세요.");
			String inputStr = sc.next();
			if(inputStr.equals("종료") || inputStr.equals("q")) {
				System.out.println("게임이 종료되었습니다.");
				break;
			}
			System.out.println("아직 가위바위보 로직을 작성중입니다.");
		}
		
		
		
	}

}
