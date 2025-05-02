package practice1;

import java.util.ArrayList; // 값을 여러 개 저장할 수 있는 리스트를 만들기 위해 import
import java.util.List;       // 리스트 타입을 쓰기 위해 import

public class Q1 {

    public static void main(String[] args) {
        
        // -------------------------------
        // 1. 1~100까지 숫자 중에서 3의 배수만 저장
        // -------------------------------

        List<Integer> multiplesOf3 = new ArrayList<>();  // 정수를 저장할 수 있는 리스트 만들기

        for (int i = 1; i <= 100; i++) {  // 1부터 100까지 숫자 하나씩 반복
            if (i % 3 == 0) {             // 3으로 나눠서 나머지가 0이면 = 3의 배수
                multiplesOf3.add(i);      // 리스트에 그 숫자를 추가
            }
        }

        // 리스트 안에 저장된 3의 배수 출력하기
        System.out.println("1~100까지 3의 배수: " + multiplesOf3);


        // -------------------------------
        // 2. 1900년부터 2025년까지의 윤년만 저장
        // -------------------------------

        List<Integer> leapYears = new ArrayList<>(); // 윤년을 저장할 리스트 만들기

        for (int year = 1900; year <= 2025; year++) {  // 1900년부터 2025년까지 반복
            // 윤년 조건:
            // 1. 4로 나누어 떨어지고, 100으로 나누어 떨어지지 않거나
            // 2. 400으로 나누어 떨어지는 해
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                leapYears.add(year); // 윤년이면 리스트에 추가
            }
        }

        // 리스트 안에 저장된 윤년들 출력하기
        System.out.println("1900~2025년 윤년: " + leapYears);
    }
}
