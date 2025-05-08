package test2;

import java.util.HashMap;
import java.util.Scanner;

public class q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> countries = new HashMap<>();

        System.out.println("나라 이름과 인구를 5개 입력하세요. (예: Korea 50000000)");

        int count = 0;
        while (count < 5) {
            System.out.print("나라 이름, 인구 >> ");
            String line = sc.nextLine().trim();
            String[] input = line.split(" ");

            if (input.length != 2) {
                System.out.println("입력 형식이 잘못되었습니다. (예: Korea 50000000)");
                continue;
            }

            String name = input[0];
            String numberText = input[1];

            if (name.matches("[0-9]+")) {
                System.out.println("나라 이름은 숫자가 아닌 문자로 입력하세요.");
                continue;
            }

            int people;
            try {
                people = Integer.parseInt(numberText);
            } catch (NumberFormatException e) {
                System.out.println("인구는 숫자로 입력해야 하며 21억 이하만 입력 가능합니다.");
                continue;
            }

            countries.put(name, people);
            count++;
        }

        String minName = null;
        int minPeople = Integer.MAX_VALUE;

        for (String name : countries.keySet()) {
            int p = countries.get(name);
            if (p < minPeople) {
                minPeople = p;
                minName = name;
            }
        }

        System.out.println("제일 인구가 적은 나라는 (" + minName + ", " + minPeople + ")");
        sc.close();
    }
}
