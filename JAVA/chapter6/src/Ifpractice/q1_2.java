package Ifpractice;

public class q1_2 {

	public static void main(String[] args) {
//		구구단을 짝수단만 출력하도록 프로그램을 만들어 보세요
		for (int dan = 2; dan <= 9; dan++) {
            if (dan % 2 != 0) {
                continue;
            }
            System.out.println("\n[" + dan + "단]");
            for (int i = 1; i <= 9; i++) {
                System.out.println(dan + " x " + i + " = " + (dan * i));
            }
        }

//		구구단을 단보다 곱하는 수가 작거나 같은 경우까지만 출력하는 프로그램을 만들어 보세요
		for(int dan = 2; dan <= 9; dan++) {
			System.out.println("\n[" + dan + "단]");
			for(int i = 1; i <= dan; i++) {
				System.out.println(dan + " x " + i + " = " + (dan*i));
	}
		}}}
