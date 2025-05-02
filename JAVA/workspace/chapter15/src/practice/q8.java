package practice;

public class q8 {

    public static void main(String[] args) {
//    	 크기가 10인 배열을 작성하고 Math.random()함수를 사용하여 1~100사이의 숫자를 랜덤하게 생성하
//    	 고 생성된 숫자가 3의 배수인 것만 배열에 저장하는 프로그램을 작성하라. (단 배열의 각 요소는 서로 
//    	 다른 값만 저장되도록 한다
        int[] arr = new int[10];
        int count = 0;         

        while (count < 10) {
            int num = (int)(Math.random() * 100) + 1;

            if (num % 3 != 0) {
                continue;
            }

            for (int i = 0; i < count; i++) {
                if (arr[i] == num) {
                    num = 0;
                    break;
                }
            }

            if (num != 0) {
                arr[count] = num;
                count++;
            }
        }

        System.out.println("3의 배수로 구성된 배열 (중복 없음):");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
