package practice;

import java.util.Scanner;

public class q5 {

    public static void main(String[] args) {
//       지불할 금액을 정수로 입력 받아 화폐단위가 각각 몇 개씩 필요한지 출력하는 프로그램을 작성하시
//    	오. 입력은 최소 10만원 단위로 입력, 화폐단위는 50000, 10000, 5000, 1000, 500, 100, 50, 10,  5, 1 10
//    	가지이며, 가능한 큰 화폐단위로 지불, 입력이 236,873이면 50000원권 4매, 10000원권 3매, 5000원권 1
//    	매, 1000원권 1매, 500원 1매, 100원권 3매, 50원권 1매, 10원권 2매, 5원권 0매, 1원권 3개로 출력하라.
//    	단 화폐단위는 배열에 저장한다.
        int[] Moneys = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};

        Scanner sc = new Scanner(System.in);

        System.out.print("지불할 금액을 입력하세요 (예: 236873): ");
        int amount = sc.nextInt();

        System.out.println("\n필요한 화폐 단위:");

        for (int i = 0; i < Moneys.length; i++) {
            int money = Moneys[i];
            int count = amount / money;
            amount = amount % money;
            System.out.println(money + "원권: " + count + "개");
        }
        sc.close();
    }
}
