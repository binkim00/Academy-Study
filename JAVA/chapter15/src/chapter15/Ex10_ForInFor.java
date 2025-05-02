package chapter15;

public class Ex10_ForInFor {

    public static void main(String[] args) {

        // 3행 3열짜리 2차원 배열을 만든다.
        int[][] arr = new int[3][3];

        // 배열에 들어갈 숫자를 1부터 시작
        int num = 1;

        // 첫 번째 for문: 행을 돌면서
        for (int i = 0; i < 3; i++) {
            // 두 번째 for문: 열을 돌면서
            for (int j = 0; j < 3; j++) {
                arr[i][j] = num;  // 배열 칸에 num 값 저장
                num++;            // num을 1씩 증가 (다음 숫자 준비)
            }
        }

        // 위에서 배열에 값을 다 채웠으니,
        // 이번에는 출력하는 반복문!
        for (int i = 0; i < 3; i++) {      // 행을 차례대로 반복
            for (int j = 0; j < 3; j++) {  // 열을 차례대로 반복
                System.out.print(arr[i][j] + "\t");  
                // 배열에 저장된 숫자를 출력하고 탭으로 띄움
            }
            System.out.println();  // 한 줄 출력 끝나면 줄 바꿈
        }
    }
}
