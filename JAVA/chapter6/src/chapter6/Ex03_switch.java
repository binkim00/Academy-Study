package chapter6;

public class Ex03_switch {

	public static void main(String[] args) {
//		switch(변수) {
//		case 비교값:
//			실행할 코드;
//			break;
//		case 비교값:
//			실행할 코드;
//			break;
//		default :
//			실행할 코드;
//		}
		int n = 4;
		switch (n % 3) {
		case 1:
			System.out.println("나머지가 1");
			break;
		case 2:
			System.out.println("나머지가 2");
			break;
		default:
			System.out.println("나머지가 0");
		}
		
//		banana, apple, tomato, mango, melon, carrot, lemon
//		위의 과일과 야채 중에 banan, apple, mango, lemom, melon이면 과일,
//		tomato, carrot이라면 야채 출력
		String str = "water";
		switch (str) {
		case "banana":
		case "apple":
		case "mango":
		case "lemon":
		case "melon":
			System.out.println(str+"= 과일");
			break;
		case "tomato" :
		case "carrot":
			System.out.println(str+"= 야채");
			break;
		default:
			System.out.println(str+"= 과일도 야채도 아닙니다.");
		}
		
		
		
//		월별 날짜 출력하기
//		1, 3, 5, 7, 8, 10, 12월 31일
//		4, 6, 9, 11월은 30일
//		2월은 28일
//		을 출력하는 switch문 작성
		int month = 15;
		switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			System.out.println(month+"월은 31일까지 있습니다.");
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			System.out.println(month+"월은 30일까지 있습니다.");
			break;
		case 2:
			System.out.println(month+"월은 28일까지 있습니다.");
			break;			
		default:
			System.out.println("1~12월까지만 작성해주세요.");
		}
		
		
	}

}
