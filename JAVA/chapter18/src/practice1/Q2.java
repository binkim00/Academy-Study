package practice1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		2. Scanner를 이용하여 한 라인을 읽고, 공백으로 분리된 어절이 몇 개인지 출력을 반복하는 
//		프로그램을 작성하라. “exit”이 입력되면 종료한다. 
//		[목적 StringTokenizer 활용] [난이도 하] 
//		----------------------------------------------------------------------------------- 
//		I Love Java 
//		어절 개수는 3 
//		나는 자바 프로그래밍을 정말 좋아합니다. 
//		어절 개수는 5 
//		exit 
//		종료합니다… 
//		----------------------------------------------------------------------------------- 
		Scanner sc = new Scanner(System.in);
		while(true) {
			String inputStr = sc.nextLine();
			if(inputStr.equals("exit")) {
				System.out.println("종료합니다...");
				break;
			}
			StringTokenizer st = new StringTokenizer(inputStr);
			System.out.println("어절의 개수는 "+ st.countTokens());
		}
		
	}

}













