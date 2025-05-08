package test2;

import java.util.HashMap;
import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> account = new HashMap<>();

        System.out.println("<< 통장 관리 프로그램입니다. >>");

        while (true) {
            System.out.print("이름과 금액 입력 >>");
            String input = scanner.nextLine().trim();

            if (input.equals("exit")) {
                System.out.println("프로그램을 종료합니다...");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("잘못된 입력입니다. 이름과 금액만 입력해주세요.");
                continue;
            }

            String name = parts[0];

            if (name.matches("[0-9]+")) {
                System.out.println("이름은 숫자가 아닌 문자로 입력해주세요.");
                continue;
            }

            int amount;

            try {
                amount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("금액은 숫자 형식으로 입력해야 하며, 21억을 넘지 않아야 합니다.");
                continue;
            }

            int total = account.getOrDefault(name, 0) + amount;
            account.put(name, total);

            printAccounts(account);
        }

        scanner.close();
    }

    public static void printAccounts(HashMap<String, Integer> account) {
        for (String name : account.keySet()) {
            System.out.print("(" + name + " > " + account.get(name) + "원)");
        }
        System.out.println();
    }
}
