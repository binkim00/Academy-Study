package Ifpractice;

import java.util.Scanner;

public class q11 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("가위, 바위, 보, 중에 입력해주세요>>");
		String player = sc.next();
		int computer = (int)(Math.random()*3);
		String com = "";
		
		if(computer == 0) {
			com = "가위";
		}else if (computer == 1) {
			com = "바위";
		}else if (computer == 2) {
			com = "보";
		}
		System.out.println("컴퓨터는 "+com+"를(을) 냈습니다.");
		
		String result = "";
		if(player.equals("가위")) {
			if(computer == 0) result = "비겼습니다.";
			else if(computer == 1) result = "졌습니다.";
			else if(computer == 2) result = "이겼습니다.";
		}else if(player.equals("바위")) {
			if(computer == 0) result = "이겼습니다.";
			else if(computer == 1) result = "비겼습니다.";
			else if(computer == 2) result = "졌습니다.";
		}else if(player.equals("보")) {
			if(computer == 0) result = "졌습니다.";
			else if(computer == 1) result = "이겼습니다.";
			else if(computer == 2) result = "비겼습니다.";
		}else {
			result = "잘못된 입력값입니다.";
		}
			
		 System.out.println(result);
		 
//		 System.out.print("가위, 바위, 보 중에 입력해주세요>>");
//			String player = sc.next();
////			0~2 사이의 랜덤 숫자를 저장
////			0:가위 1:바위 2:보
//			int computer = (int)(Math.random()*3);
//			int playNum = 0;
//			if(player.equals("가위")) {
//				playNum = 0;
//			}else if(player.equals("바위")) {
//				playNum = 1;
//			}else {
//				playNum = 2;
//			}
//			int result = playNum - computer;
////			졌을때 : -1 , 2
////			이겼을때 : -2 , 1 
//			if(playNum == computer) {
//				System.out.println("비겼습니다.");
//			}else if(result == -2 || result == 1) {
//					System.out.println("이겼습니다.");
//			}else if(result == -1 || result == 2) {
//					System.out.println("졌습니다.");
//			}

	}

}
