package practice;

import java.util.Scanner;

public class q3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		������ �Է¹޾� ¦���̸� ��¦��, Ȧ���̸� ��Ȧ���� ����ϴ� ���α׷��� �ۼ��϶�. ����ڰ� ������ �Է����� �ʴ� ��쿡�� ���α׷��� �����϶�. 
		while(true) {
			System.out.println("������ �Է��ϼ���.(�Է����� �ʰ� ���͸� ������ ����˴ϴ�.)");
			String input = sc.nextLine();
			
			if(input.isEmpty()) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
			
			try {
				int num = Integer.parseInt(input);
				if(num % 2 == 0) {
					System.out.println("¦");
				}else {
					System.out.println("Ȧ");
				}
			} catch (NumberFormatException e) {
				System.out.println("��ȿ�� ������ �Է��ϼ���.");
			}
		} sc.close();

//		����ó������ �ϴ� ���.
//		System.out.print("������ �Է��ϼ���>>");
//		String str = sc.next();
		// ���ڿ��� ������ŭ �ݺ�
//		for(int i = 0; i < str.length(); i++) {
			// charAt()�� �̿��Ͽ� �ѱ��ھ� char�� ����
//			char c = str.charAt(i);
			// ���ڰ� �������� �ƴ��� Ȯ���ϴ� if��
//			if(c < '0' || c > '9') {
//				System.out.println("���� �Է����� �ʾ� ���α׷��� �����մϴ�.");
//				return;
//			}
//		}
		//��� ���ڰ� ���ڶ�� int�� ����
//		int num = Integer.parseInt(str);
		// ¦������ Ȧ������ Ȯ���ϴ� if��
//		if(num % 2 == 0) {
//			System.out.println("¦��");
//		}else {
//			System.out.println("Ȧ��");
//		}
		
	}
}
