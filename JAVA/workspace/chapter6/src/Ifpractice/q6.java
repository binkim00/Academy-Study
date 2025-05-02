package Ifpractice;

import java.util.Scanner;

public class q6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		돈의 액수를 입력받아 오만원권, 만원권, 천원권, 500원짜리 동전, 100원짜리 동전, 10원
//		짜리 동전, 1원짜리 동전 각 몇 개로 변환되는지 출력하라.
//		돈의 액수를 입력하세요>>65245
		System.out.print("돈의 액수를 입력하세요>> ");
		int n = sc.nextInt();
		int count;
		switch (n / 50000 > 0 ? 1 : 0) {
        case 1:
            count = n / 50000;
            System.out.println("오만원권: " + count + "개");
            n %= 50000;
		}

	    switch (n / 10000 > 0 ? 1 : 0) {
	        case 1:
	            count = n / 10000;
	            System.out.println("만원권: " + count + "개");
	            n %= 10000;
	    }
	    
	    switch (n / 5000 > 0 ? 1 : 0) {
        	case 1:
            count = n / 5000;
            System.out.println("오천원권: " + count + "개");
            n %= 5000;
	    }
	    
	    switch (n / 1000 > 0 ? 1 : 0) {
	        case 1:
	            count = n / 1000;
	            System.out.println("천원권: " + count + "개");
	            n %= 1000;
	    }
	
	    switch (n / 500 > 0 ? 1 : 0) {
	        case 1:
	            count = n / 500;
	            System.out.println("500원 동전: " + count + "개");
	            n %= 500;
	    }
	
	    switch (n / 100 > 0 ? 1 : 0) {
	        case 1:
	            count = n / 100;
	            System.out.println("100원 동전: " + count + "개");
	            n %= 100;
	    }
	
	    switch (n / 10 > 0 ? 1 : 0) {
	        case 1:
	            count = n / 10;
	            System.out.println("10원 동전: " + count + "개");
	            n %= 10;
	    }
	
	    switch (n > 0 ? 1 : 0) {
	        case 1:
	            System.out.println("1원 동전: " + n + "개");
	    }

        	

	}

}
