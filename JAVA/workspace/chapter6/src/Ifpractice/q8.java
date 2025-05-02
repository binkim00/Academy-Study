package Ifpractice;

import java.util.Scanner;

public class q8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		음료수 종류와 잔 수를 입력받으면 가격을 알려주는 프로그램을 작성하라. 에스프레소
//		는 2000원, 아메리카노 2500원, 카푸치노 3000원, 카페라떼 3500원이다. 
		int total = 0;

        while (true) {
            System.out.print("커피 종류와 잔 수를 입력하세요 (끝내려면 '끝' 입력)>> ");
            String coffee = sc.next();

            if (coffee.equals("끝")) {
                break;
            }

            int cups = sc.nextInt();
            int price = 0;
            
            switch (coffee) {
            case "에스프레소":
                price = 2000;
                break;
            case "아메리카노":
                price = 2500;
                break;
            case "카푸치노":
                price = 3000;
                break;
            case "카페라떼":
                price = 3500;
                break;
            default:
                System.out.println("없는 메뉴입니다.");
                continue;
        }

            total += price * cups;
	    }
	
	    System.out.println("총 가격은 " + total + "원입니다.");
		
	    
		System.out.print("커피 주문하세요>>");
		String coffee = sc.next();
		int count = sc.nextInt();
		String str = "원 입니다.";
//		(1) if문을 활용하라.
		if(coffee.equals("에스프레소")) {
			System.out.println((count*2000)+str);
		}else if(coffee.equals("아메리카노")) {
			System.out.println((count*2500)+str);
		}else if(coffee.equals("카푸치노")) {
			System.out.println((count*3000)+str);
		}else if(coffee.equals("카페라떼")) {
			System.out.println((count*3500)+str);
		}else {
			System.out.println("에스프레서,아메리카노,카푸치노,카페라떼 중에 선택해주세요.");
		}
//		(2) switch문을 활용하라. 
		switch(coffee) {
		case "에스프레소":
			System.out.println((count*2000)+str);
			break;
		case "아메리카노":
			System.out.println((count*2500)+str);
			break;
		case "카푸치노":
			System.out.println((count*3000)+str);
			break;
		case "카페라떼":
			System.out.println((count*3500)+str);
			break;
		default : 
			System.out.println("에스프레서,아메리카노,카푸치노,카페라떼 중에 선택해주세요.");
		}    
        
	}

}
