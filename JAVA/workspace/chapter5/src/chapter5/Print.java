package chapter5;

public class Print {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// println : 문자열 출력 후 엔터를 쳐주는 메서드
		System.out.println("출력하고 싶은 문자열");
		System.out.println("두번째 줄이지비");
		// print : 문자열을 출력ㅎ고 엔터를 치지 않음
		System.out.print("안녕하지비? ");
		System.out.print("오늘은 4월 10일이지비");
		System.out.println();
		
		String name = "홍길동";
		int age = 20;
		double height = 175.5;
		// 더하기 연산자를 이용한 변수 출력
		System.out.println(name + "의 나이는 "+age+"이고, 키는 "+height+"입니다.");
		
		// printf : 포맷을 이용한 변수 출력 방식 책 p.117
		// %s : 문자열 string
		// %d : 정수, int
		// %f : 실수, double 
		System.out.printf("%s의 나이는 %d이고, 키는 %f 입니다.\n", name, age, height);
		System.out.printf("%s의 나이는 %d이고, 키는 %.1f 입니다.", name, age, height); 
		// .1f : 출력하고 싶은 소수점 자리
		
		
	}

}
