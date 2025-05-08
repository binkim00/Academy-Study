package test2;

import java.util.Scanner;

public class q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();

            if (line.equals("exit")) {
                System.out.println("종료합니다...");
                break;
            }

            String[] words = line.split(",");

            System.out.println("어절 개수는 " + words.length);
        }

        sc.close();
    }
}
