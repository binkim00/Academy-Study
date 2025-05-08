package test1;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		int rannum = random.nextInt(100) + 1;
		
		int trynum = 0;
		
		System.out.println("숫자 맞추기 게임을 시작합니다!");
		System.out.println("컴퓨터가 숫자를 정했습니다!");
		
		if(rannum % 2 == 0) {
			System.out.println("힌트는 짝수입니다!");
		}else {
			System.out.println("힌트는 홀수입니다!");
		}
		
		while(true) {
			System.out.println("1~100 사이의 숫자를 입력하세요! >>>");
			int guessnum = sc.nextInt();
			trynum++;
			
			if(guessnum < rannum) {
				System.out.println("더 큰 수 입니다!");
			} else if(guessnum > rannum) {
				System.out.println("더 작은 수 입니다!");
			}else {
				System.out.println(trynum + "회 만에 맞췄습니다!");
				System.out.println("게임을 종료합니다!");
				break;
			}
		}
		sc.close();
	}

}
