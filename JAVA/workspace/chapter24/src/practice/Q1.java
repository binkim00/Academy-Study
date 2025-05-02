package practice;

import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) throws IOException {
		
		// 1. gugudan.txt 파일에 1~9단까지의 구구단을 출력해보자
		Instant start0 = Instant.now();
		
		try (PrintWriter pw = new PrintWriter
				(new FileOutputStream("gugudan.txt"))) 
		{
			for (int dan = 1; dan <= 9; dan++) 
			{
				pw.println("[--- " + dan + " 단---]");
				for (int i = 1; i <= 9; i++) 
				{
					pw.println(dan + " x " + i + " = " + (dan * i));
				}
				pw.println();
			}
		}
		
		Instant end0 = Instant.now();
		
		Duration duration0 = Duration.between(start0, end0);
		System.out.println("실행 시간: " + duration0.toMillis() + "ms");
		
		//--------------------------------------------------------------------------
		// 2. year.txt 파일에 1900년도부터 2025년 까지의 윤년을 출력해보자
		// 4로 나누어 떨어지고 백으로 나눠 떨어지지 않음
		// 또는 400으로 나누어 떨어짐
		Instant start1 = Instant.now();

		try (PrintWriter pw = new PrintWriter
		        (new FileOutputStream("year.txt"))) 
		{
			pw.println("--------- 1900년부터 2025년 사이 윤년 목록 ---------");
		    for (int year = 1900; year <= 2025; year++) 
		    {
		        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) 
		        {
		            pw.println("1900년부터 2025년 사이의 윤년은 " + year + "년이 있습니다.");
		        }
		    }
		}

		Instant end1 = Instant.now();

		Duration duration1 = Duration.between(start1, end1);
		System.out.println("실행 시간: " + duration1.toMillis() + "ms");

		
		//--------------------------------------------------------------------------

		// 3. 가위바위보 게임의 결과를 log.txt파일에 출력해보자
		Scanner sc = new Scanner(System.in);
		Random random = new Random();

		int win = 0, lose = 0, draw = 0;

		for (int round = 1; round <= 5; round++) {
			System.out.print("[" + round + "번째] 가위(0), 바위(1), 보(2) 중 하나를 선택하세요: ");
			int user = sc.nextInt();

			int com = random.nextInt(3);
			String[] hands = {"가위", "바위", "보"};
			String userHand = hands[user];
			String comHand = hands[com];

			String result;

			if (user == com) {
				result = "비김";
				draw++;
			} else if ((user == 0 && com == 2)
					|| (user == 1 && com == 0)
					|| (user == 2 && com == 1)) {
				result = "승리";
				win++;
			} else {
				result = "패배";
				lose++;
			}

			System.out.println(" → 당신: " + userHand 
							+ " / 컴퓨터: " + comHand 
							+ " → 결과: " + result);
		}

		Instant start2 = Instant.now();

		try (PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt", true))) 
		{
		    pw.println("[5판 결과] 승리: " + win + ", 패배: " + lose + ", 비김: " + draw);
		}

		Instant end2 = Instant.now();
		Duration duration2 = Duration.between(start2, end2);
		System.out.println("기록 저장 시간: " + duration2.toMillis() + "ms");

		sc.close();

	}
}


