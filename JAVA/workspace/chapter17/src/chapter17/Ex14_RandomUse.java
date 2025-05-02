package chapter17;

import java.util.Random;

public class Ex14_RandomUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Random 클래스는 랜덤한 값을 편하게 구하기 위한 클래스
		Random rand1 = new Random();
		for(int i=0; i<10; i++) {
//			0~9까지의 랜덤한 int값을 반환하는 함수
			System.out.print(rand1.nextInt(10)+" ");
		}
		System.out.println();
		
		Random rand2 = new Random(50);
		for(int i=0; i<10; i++) {
			System.out.print(rand2.nextInt(10)+" ");
		}
		System.out.println();
	}

}













