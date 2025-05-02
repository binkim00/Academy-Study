package chapter15;

import java.util.Arrays; // Arrays 클래스를 사용하기 위해 import

public class Ex12_ArrayCopy {

    public static void main(String[] args) {
        int[] arr1 = new int[10];  // 크기가 10인 정수형 배열 arr1 생성
        int[] arr2 = new int[8];   // 크기가 8인 정수형 배열 arr2 생성
        
        // Arrays.fill을 사용해 arr1 배열의 모든 값을 3으로 채움
        Arrays.fill(arr1, 3);  // arr1 배열에 3이 채워짐 -> {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}

        // arr1의 0번 인덱스부터 arr2의 3번 인덱스로 4개의 값을 복사
        System.arraycopy(arr1, 0, arr2, 3, 4);
        // arr1[0], arr1[1], arr1[2], arr1[3]의 값이 arr2[3], arr2[4], arr2[5], arr2[6]에 복사됨
        
        // arr1 배열 출력
        for (int i = 0; i < arr1.length; i++)  // arr1 배열의 길이만큼 반복
            System.out.print(arr1[i] + " ");    // 각 원소를 출력
        System.out.println();  // 출력 후 한 줄 바꿈
        
        // arr2 배열 출력
        for (int i = 0; i < arr2.length; i++)  // arr2 배열의 길이만큼 반복
            System.out.print(arr2[i] + " ");    // 각 원소를 출력
        System.out.println();  // 출력 후 한 줄 바꿈
        
        // Arrays.copyOfRange 메서드를 사용해서 arr2 배열에서 2번 인덱스부터 5번 인덱스까지 복사
        int[] arr3 = Arrays.copyOfRange(arr2, 2, 5);
        // arr2[2], arr2[3], arr2[4]의 값이 새로운 배열 arr3에 복사됨
        
        // arr3 배열 출력
        for (int i = 0; i < arr3.length; i++)  // arr3 배열의 길이만큼 반복
            System.out.print(arr3[i] + " ");    // 각 원소를 출력
        System.out.println();  // 출력 후 한 줄 바꿈
        
//        배열 복사시 매세드가 필요한 이유
//        배열은 객체이기 대문에 그냥 대입하면 메모리 주소만 
//        저장하고 데이터는 둘다 똑같이 사용하게됨
          int[] arr4 = {1, 2, 3, 4};
          // 배열을 다른 배열에 대입
          int[] arr5 = arr4;
          System.out.println(Arrays.toString(arr4));
          System.out.println(Arrays.toString(arr5));
          // 각각의 배열에 데이터를 변경
          arr4[0] = 1000;
          arr5[3] = 2000;
          System.out.println(Arrays.toString(arr4));
          System.out.println(Arrays.toString(arr5));
//        배열은 = 로 복사하면 데이터가 복사되는 게 아니라 메모리 주소(참조값)만 복사
          
          // 매세드 사용 ↓, 다음 장에서 설명
          int[] arr6 = {1, 2, 3, 4};  
          int[] arr7 = Arrays.copyOf(arr6, arr6.length);  // 데이터 복사

          arr6[0] = 1000;      // arr1 값 수정
          arr7[3] = 2000;      // arr2 값 수정

          System.out.println(Arrays.toString(arr6));  // [1000, 2, 3, 4]
          System.out.println(Arrays.toString(arr7));  // [1, 2, 3, 2000]
          
//        String의 경우 대입하여도 메모리 주소를 복사하는게 아닌 데이터를 복사
          String a = "a";
          String b = a;
          System.out.println(a);
          System.out.println(b);
          b = "b";
          System.out.println(a);
          System.out.println(b);

          
    }

}
