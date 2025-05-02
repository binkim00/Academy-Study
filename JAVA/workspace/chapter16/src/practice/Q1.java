package practice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int win = 0;
        int lose = 0;
        int tie = 0;

        // 승리가 5번 되기 전까지 무한 반복
        while (win < 5) {
            try {
                // 사용자에게 입력을 요구
                System.out.println("가위(1), 바위(2), 보(3) 중에 선택해주세요>>");
                int player = sc.nextInt();  // 사용자 입력 (숫자 기대)

                // 만약 1~3 사이 숫자가 아니면 경고 후 다시 입력받기
                if (player < 1 || player > 3) {
                    System.out.println("1~3 사이의 숫자를 입력해주세요!");
                    continue;  // while문의 처음으로 돌아감
                }

                // 컴퓨터가 1~3 사이 무작위 숫자 선택
                int computer = (int) (Math.random() * 3) + 1;

                // 플레이어와 컴퓨터의 차이 계산
                int result = player - computer;

                // 결과를 저장할 문자열
                String resultStr = "";
                if (result == 0) {
                    resultStr = "비김";
                    tie++;  // 비겼을 때
                } else if (result == -2 || result == 1) {
                    resultStr = "승리";
                    win++;  // 이겼을 때
                } else if (result == -1 || result == 2) {
                    resultStr = "패배";
                    lose++;  // 졌을 때
                }

                // 결과 출력
                System.out.println("플레이어:" + checkNum(player) + " vs " + checkNum(computer) + ":컴퓨터 " + resultStr);
                System.out.println("승리:" + win + "/5 , 패배:" + lose + " , 비김:" + tie);
            	} catch (InputMismatchException e) {
	                // ✋ try 블록 안에서 숫자 대신 문자를 입력하면
	                //    프로그램이 에러를 만나고 실행이 여기로 점프함!
	
	                System.out.println("1~3 사이의 숫자를 입력해주세요!");
	
	                // 잘못된 입력을 비워서 다음 입력을 준비
	                sc.nextLine();  // 입력 버퍼를 비움
	                // 비우지 않으면 엔터가 남아 있어서 에러가 무한반복이 실행됨
            	}
        }

        // 승리 5번 달성 후 게임 종료
        System.out.println("가위바위보가 종료되었습니다.");
    }

    // 숫자를 가위, 바위, 보 문자열로 바꿔주는 함수
    public static String checkNum(int num) {
        String result = "";
        if (num == 1) {
            result = "가위";
        } else if (num == 2) {
            result = "바위";
        } else {
            result = "보";
        }
        return result;
        
//        흐름 				| 설명
//        try 실행 			| 사용자의 입력을 받아 int player = sc.nextInt(); 를 시도
//        입력 오류 발생		| 사용자가 숫자가 아닌 문자를 입력하면 에러 발생
//        catch 이동 			| 프로그램이 멈추지 않고 catch 블록으로 점프
//        에러 처리 			| "1~3 사이의 숫자를 입력해주세요!" 메시지 출력
//        입력 버퍼 정리 		| sc.nextLine()으로 잘못된 입력을 비움
//        while 반복 			| 다시 숫자를 입력받음

//        [ 게임 시작! ]
//                ↓
//        [ while(win < 5) ]
//                ↓
//        [ 숫자 입력 요청 : 가위(1) 바위(2) 보(3) >> ]
//                ↓
//        ┌──────────────────────────────┐
//        │        try {                 │
//        │   ➡️ 사용자가 입력값 입력!     	 │
//        │   ➡️ player = sc.nextInt();  │
//        └──────────────────────────────┘
//                ↓
//         [ 입력값 검사 ]
//          ┌─────────────┐
//          │ 1~3 사이인가?  │
//          └─────────────┘
//                 │
//         ┌─────────────┐          ┌─────────────┐
//         │    YES!     │ ◀─────┐  	│    NO!      │
//         │ 가위/바위/보 계산 	   │    │ 1~3 숫자만 입력 │
//         │ 승패 판정 & 카운트 	   │  	│ continue 	  │
//         └─────────────┘     │ 	└─────────────┘
//                ↓            │
//        [ 승리: 5점 도달? ]     │
//               ↓            │
//           YES → 게임 종료 🎉  │
//           NO → 반복 ↩️─────────┘
//
//
//        에러 발생 시:
//        ┌────────────────────────────────────┐
//        │ catch(InputMismatchException e)    │
//        │ "숫자만 입력하세요!" 출력                 │
//        │ sc.nextLine(); ← 잘못 입력된 문자 제거   │
//        │ → while문 처음으로 돌아감               │
//        └────────────────────────────────────┘
        
    }
}
