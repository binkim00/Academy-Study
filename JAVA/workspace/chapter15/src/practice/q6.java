package practice;

import java.util.Scanner;

public class q6 {

	public static void main(String[] args) {
//		������ ���ڸ� �Է��Ͽ� 369������ �����ϰ� �ۼ�. 1-99���� ������ �Է��ϰ� 3,6,9 �� �ϳ��� �ִ� 
//		��� ���ڼ�¦���� ����ϰ�, �� �� �ִ� ��� ���ڼ�¦¦���� ����ϴ� ���α׷��� �ۼ��϶�.
		Scanner sc = new Scanner(System.in);
		System.out.print("1~999���� ������ �Է��ϼ���.");
		int number = sc.nextInt();
		
		if (number < 1 || number > 999) {
            System.out.println("�߸��� �Է��Դϴ�. 1���� 999������ ���ڸ� �Է��ϼ���.");
        } else {
            int clapCount = 0;
            int huns = number / 100;
            int tens = (number / 10) % 10;
            int ones = number % 10;

            if (huns == 3 || huns == 6 || huns == 9) {
            	clapCount++;
            }
            if (tens == 3 || tens == 6 || tens == 9) {
                clapCount++;
            }
            if (ones == 3 || ones == 6 || ones == 9) {
                clapCount++;
            }

            if (clapCount == 1) {
                System.out.println("�ڼ� ¦");
            } else if (clapCount == 2) {
                System.out.println("�ڼ� ¦¦");
            } else if (clapCount == 3) {
            	System.out.println("�ڼ� ¦¦¦");
            } else {
            	System.out.println("�ڼ� ����");
            	}
        }
		
//		�ٸ� ���
//		String str = sc.next();
//		int count = 0;
//		for(int i=0; i<str.length(); i++) {
//			char c = str.charAt(i);
//			if(c=='3' || c=='6' || c=='9') {
//				if(count>0) {
//					System.out.print("¦");
//				}else {
//					System.out.print("�ڼ�¦");
//					count++;
//				}
//				
//			}
//		}
		
		
		
	}

}
