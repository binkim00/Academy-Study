package practice;

public class q7 {

	public static void main(String[] args) {
//		다음 그림과 같이 위쪽과 왼쪽 숫자를 곱한 곱셈표를 출력하는 프로그램을 작성하시오.
		System.out.print("   | ");
		for (int i = 1; i <= 9; i++) {
            System.out.print(i+" ");
        }
		
        System.out.println();
        
        System.out.println("---+------------------------");
        
        for (int i = 1; i <= 9; i++) {
            System.out.print(" "+ i + " | ");
            for (int j = 1; j <= 9; j++) {
                System.out.print(i*j);
                if((i*j) >= 10) {
                	System.out.print(" ");
                }else {
                	System.out.print("  ");
                }
            }
            System.out.println();
        }

//      다른 방법
//        System.out.print("    |");
//        for (int i = 1; i <= 9; i++) {
//            System.out.printf("%4d", i);
//        }
//        System.out.println();
//        
//        System.out.println("----+------------------------------------");
//
//        for (int i = 1; i <= 9; i++) {
//            System.out.printf("%2d  |", i);
//            for (int j = 1; j <= 9; j++) {
//                System.out.printf("%4d", i * j);
//            }
//            System.out.println();
//        }

        
	}

}
