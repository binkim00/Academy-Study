package test2;

import java.util.HashMap;
import java.util.Scanner;

public class q4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> drinks = new HashMap<>();

        drinks.put("밀키스", 700);
        drinks.put("코카콜라", 800);
        drinks.put("펩시", 1000);
        drinks.put("칠성사이다", 1200);

        System.out.println("밀키스, 코카콜라, 펩시, 칠성사이다 있습니다.");

        while (true) {
            System.out.print("선택 >> ");
            String input = scanner.nextLine().trim();

            if (input.equals("그만")) {
                System.out.println("종료합니다...");
                break;
            }

            if (drinks.containsKey(input)) {
                int price = drinks.get(input);
                System.out.println(input + "는 " + price + "원 입니다.");
            } else {
                System.out.println("없는 음료입니다.");
            }
        }

        scanner.close();
    }
}
