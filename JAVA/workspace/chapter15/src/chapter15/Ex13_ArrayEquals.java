package chapter15;

import java.util.Arrays;  // 배열 관련 편리한 메서드를 제공하는 Arrays 클래스 import

public class Ex13_ArrayEquals {

    public static void main(String[] args) {
        // 크기가 5인 정수 배열 arr1을 선언하고 값 {1,2,3,4,5}로 초기화
        int[] arr1 = {1, 2, 3, 4, 5};

        // Arrays.copyOf를 사용해서 arr1을 복사, arr1 길이만큼 복사해서 arr2에 저장
        // Arrays.copyOf(원본배열, 복사길이)
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        // 이 시점에서 arr2 = {1, 2, 3, 4, 5} 가 됨

        // 두 배열이 같은지 비교하는 코드
        boolean bCheck = Arrays.equals(arr1, arr2);
        // equals 메서드는 배열의 길이와 값이 모두 같으면 true, 다르면 false를 반환함

        // 비교 결과 출력
        System.out.println(bCheck);  // arr1과 arr2가 값도 같고 길이도 같으므로 true 출력
    }
}
