package chapter6;

public class Ex04_for {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for : 반복문, 정해져있는 횟수만큼 반복할 때 사용하는 반복문
		// 초기값 -> 조건식 -> 코드 -> 증강식 순서로 진행!!!!!!
//		for(초기값; 조건식; 증강식) {
//			실행할 코드;
//		}
		int total = 0;
		for(int i = 0; i < 10; i++) { //중괄호 안에 만든 내용은 안에서만 사용 가능!!
			System.out.println(i+"번 실행했습니다.");
			total += i;
		} System.out.println(total);
		
		// for문으로 5단 출력하기
		int dan = 5;
		for(int i=1; i<=9; i++) {
			System.out.println(dan+"*"+i+"="+(dan*i));
		}
		
		// 5단의 i가 짝수인 값만 출력하기
		// if문으로 확인하기 때문에 반복횟수가 9회
		for(int i=1; i<=9; i++) {
			if(i%2==0) {
			System.out.println(dan+"*"+i+"="+(dan*i));
			}
		}
//		증강식이 2씩 더하기 때문에 반복횟수가 4회
		for(int i=2; i<=9; i=i+2) {
			System.out.println(dan+"*"+i+"="+(dan*i));
		}
		
//		뭐라 부르지?
		for(int i=2; i<=9; i=i+2) {
			for(int j=1; j<=9; j=j+2) {
				if(j>7) {
					break;
				}
				if(j%3==0) {
					continue;
				}
				System.out.println(i+"*"+j+"="+(i*j));
			}
		}
		
	}

}
