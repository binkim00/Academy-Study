package practice;

public class q7 {

	public static void main(String[] args) {
//		���� �׸��� ���� ���ʰ� ���� ���ڸ� ���� ����ǥ�� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
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

//      �ٸ� ���
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
