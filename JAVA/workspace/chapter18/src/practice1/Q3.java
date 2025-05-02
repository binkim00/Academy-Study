package practice1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		4. 다음과 같이 +로 연결된 덧셈식을 입력받아 덧셈 결과를 출력하는 프로그램을 작성하라. 
//		StringTokenizer와 Integer.parseInt(), String의 trim()을 활용하라. [목적 StringTokenizer, 
//		String trim(), Integer.parseInt() 활용] [난이도 중] 
//		----------------------------------------------------------------------------------- 
//		2 + 5 + 6 + 10 
//		합은 23 
//		-----------------------------------------------------------------------------------
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str,"+");
		int sum = 0;
		while(st.hasMoreTokens()) {
//			String numStr = st.nextToken().trim();
//			int num = Integer.parseInt(numStr);
//			sum += num;
			sum += Integer.parseInt(st.nextToken().trim());
		}
		System.out.println("합은 "+sum);
		
	}

}












