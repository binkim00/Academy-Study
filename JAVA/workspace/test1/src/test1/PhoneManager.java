package test1;

import java.util.HashMap;
import java.util.Scanner;

public class PhoneManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Phone> phoneMap = new HashMap<>();

        System.out.print("인원 수 >> ");
        int num = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < num; i++) {
            System.out.print("이름과 전화번호(번호는 연속적으로 입력하세요.), 주소 >> ");
            String name = sc.next();
            String tel = sc.next();
            String address = sc.next();
            sc.nextLine(); // 줄바꿈 제거

            phoneMap.put(name, new Phone(name, tel, address));
        }

        System.out.println("정보가 저장되었습니다.");

        while (true) {
            System.out.print("검색할 이름 >> ");
            String search = sc.nextLine().trim();

            if (search.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            Phone p = phoneMap.get(search);
            if (p != null) {
                System.out.println(p.getName() + "의 번호와 주소는 " +
                                   p.getTel() + " " + 
                                   p.getAddress() + "입니다.");
            } else {
                System.err.println(search + "은(는) 없습니다.");
            }
        }

        sc.close();
    }
}
