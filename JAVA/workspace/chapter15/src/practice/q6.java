package practice;

import java.util.Scanner;

public class q6 {

	public static void main(String[] args) {
//		임의의 수자를 입력하여 369게임을 간단하게 작성. 1-99까지 정수를 입력하고 3,6,9 중 하나가 있는 
//		경우 ‘박수짝’을 출력하고, 두 개 있는 경우 ‘박수짝짝’을 출력하는 프로그램을 작성하라.
		Scanner sc = new Scanner(System.in);
		System.out.print("1~999까지 정수를 입력하세요.");
		int number = sc.nextInt();
		
		if (number < 1 || number > 999) {
            System.out.println("잘못된 입력입니다. 1부터 999까지의 숫자만 입력하세요.");
        } else {
            int clapCount = 0;
            int huns = number / 100;
            int tens = (number / 10) % 10;
            int ones = number % 10;

            if (huns == 3 || huns == 6 || huns == 9) {
            	clapCount++;
            }
            if (tens == 3 || tens == 6 || tens == 9) {
                clapCount++;
            }
            if (ones == 3 || ones == 6 || ones == 9) {
                clapCount++;
            }

            if (clapCount == 1) {
                System.out.println("박수 짝");
            } else if (clapCount == 2) {
                System.out.println("박수 짝짝");
            } else if (clapCount == 3) {
            	System.out.println("박수 짝짝짝");
            } else {
            	System.out.println("박수 없음");
            	}
        }
		
//		다른 방법
//		String str = sc.next();
//		int count = 0;
//		for(int i=0; i<str.length(); i++) {
//			char c = str.charAt(i);
//			if(c=='3' || c=='6' || c=='9') {
//				if(count>0) {
//					System.out.print("짝");
//				}else {
//					System.out.print("박수짝");
//					count++;
//				}
//				
//			}
//		}
		
		
		
	}

}
