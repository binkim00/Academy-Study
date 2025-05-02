package chapter15;

import java.util.Arrays;  // 배열 관련 편리한 메서드를 제공하는 Arrays 클래스 import
import java.util.Collections;

public class Ex14_ArraySort {

    public static void main(String[] args) {
        // 정수형 배열 arr1을 선언하고 값 초기화
        int[] arr1 = {1, 3, 2, 4};
        // 실수형 배열 arr2를 선언하고 값 초기화
        Double[] arr2 = {4.4, 3.3, 2.2, 1.1};
        // 문자열 배열 arr3을 선언하고 값 초기화
        String[] arr3 = {"홍길동", "전우치", "손오공", "멀린"};

        // int 배열의 경우 오름차순 밖에 할 수 없음
        // Integer 배열의 경우 둘 다 사용 가능
        // double도 Double로 사용가능
        // 클래스 자료형이 오름매림 둘다 가능. 기초자료형은 오름차순만 가능
        
        // int 배열을 오름차순으로 정렬 (작은 값 → 큰 값)
        Arrays.sort(arr1);
        // double 배열을 오름차순으로 정렬
        Arrays.sort(arr2, Collections.reverseOrder());
        // String 배열을 오름차순으로 정렬 (유니코드 순서, 가나다 순)
        Arrays.sort(arr3);

        // 향상된 for문을 이용해서 arr1 출력
        for(int n : arr1)
            System.out.print(n + "\t");  // 정수 출력
        System.out.println();  // 줄바꿈

        // arr2 출력 (실수 배열)
        for(double d : arr2)
            System.out.print(d + "\t");  // 실수 출력
        System.out.println();  // 줄바꿈

        // arr3 출력 (문자열 배열)
        for(String s : arr3)
            System.out.print(s + "\t");  // 문자열 출력
        System.out.println();  // 줄바꿈
    }
}
