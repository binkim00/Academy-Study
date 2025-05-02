package practice1;

import java.util.Random;
import java.util.Scanner;

public class Q4 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		6. Math.random()의 난수 발생기를 이용하여 사용자와 컴퓨터가 하는 가위바위보 게임
//		을 만들어보자. 가위, 바위, 보는 각각 1, 2, 3 키이다. 사용자가 1, 2, 3 키 중 하나를 
//		입력하면 동시에 프로그램에서 난수 발생기를 이용하여 1, 2, 3 중에 한 수를 발생시
//		켜 컴퓨터가 낸 것을 결정한다. 그리고 사용자와 컴퓨터 둘 중 누가 이겼는지를 판별
//		하여 승자를 출력한다. [목적 종합 응용연습] [난이도 상] 
//		------------------------------------------------------------------------------- 
//		가위(1), 바위(2), 보(3), 끝내기(4)>>1 
//		사용자 가위 : 컴퓨터 바위 
//		사용자가 졌습니다. 
//		가위(1), 바위(2), 보(3), 끝내기(4)>>3 
//		사용자 보 : 컴퓨터 바위 
//		사용자가 이겼습니다. 
//		가위(1), 바위(2), 보(3), 끝내기(4)>>4 
//		게임을 종료합니다… 
//		-------------------------------------------------------------------------------
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.print("가위(1), 바위(2), 보(3), 끝내기(4)>>");
				int player = sc.nextInt();
				if(player == 4) {
					System.out.println("게임을 종료합니다… ");
					break;
				}else if(player>4) {
					System.out.println("1~4까지만 입력해주세요.");
					continue;
				}
				int computer = (int)(Math.random()*3+1);
				Random r = new Random();
				computer = r.nextInt(3)+1;
				int result = player-computer;
				System.out.println("사람:"+player+"vs"+computer+":컴퓨터");
				if(result == 0) {
					System.out.println("비겼습니다.");
				}else if(result==-2 || result==1) {
					System.out.println("사용자가 이겼습니다.");
				}else {
					System.out.println("사용자가 졌습니다.");
				}
			}catch(Exception e) {
				System.out.println("1~4까지만 입력해주세요.");
				sc.nextLine();
			}
		}
	}
}









