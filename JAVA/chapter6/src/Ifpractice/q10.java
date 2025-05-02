package Ifpractice;

import java.util.Scanner;

public class q10 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 윤년의 조건
        // 400으로 나누어 나머지가 0이면 윤년입니다.
        // 100으로 나누어 나머지가 0이면 윤년이 아닙니다.
        // 4로 나누어 나머지가 0이면 윤년입니다.
		//  그 이외에 연도는 모두 윤년이 아닙니다.
		// 1. if문으로 작성하기
		System.out.print("연도를 입력해주세요>>");
		int year = sc.nextInt();
		if (year % 400 == 0) {
		    System.out.println("윤년입니다.");
		} else if (year % 100 == 0) {
		    System.out.println("윤년이 아닙니다.");
		} else if (year % 4 == 0) {
		    System.out.println("윤년입니다.");
		} else {
		    System.out.println("윤년이 아닙니다.");
		}
		// 예시
//		if(year%400==0 
//				|| (year%4==0 && year%100!=0)) {
//				System.out.println(year + "년은 윤년 입니다.");
//			}else {
//				System.out.println(year + "년은 윤년이 아닙니다.");
//			}

		// 2. switch로 작성하기
		int year2 = sc.nextInt();

		int y4 = year2 % 4;
        int y100 = year2 % 100;
        int y400 = year2 % 400;
        // 4로 나누어 떨어지면 윤년인데
        // 100으로 나누어떨어지면 평년 
        // 400으로 나누어떨어지면 윤년, 아니면 평년
        // 100으로는 안 나누어지면 윤년
        // 4로도 안 나누어지면 평년
        switch (y4) {
        case 0:
            switch (y100) {
                case 0:
                    switch (y400) {
                        case 0:
                            System.out.println(year2 + "년은 윤년입니다.");
                            break;
                        default:
                            System.out.println(year2 + "년은 윤년이 아닙니다.");
                            break;
                    }
                    break;
                default:
                    System.out.println(year2 + "년은 윤년입니다.");
                    break;
            }
            break;
        default:
            System.out.println(year2 + "년은 윤년이 아닙니다.");
            break;
    }

		
		
	}
	}

