package practice;

import java.util.Arrays;

public class q9 {

	public static void main(String[] args) {
		int[] original = {15, 7, 2, 9, 10, 12, 17, 11, 20, 5};

		// 선택 정렬
		int[] selectionArr = Arrays.copyOf(original, original.length);
		selectionSort(selectionArr);
		System.out.println("선택 정렬 결과: " + Arrays.toString(selectionArr));

		// 삽입 정렬
		int[] insertionArr = Arrays.copyOf(original, original.length);
		insertionSort(insertionArr);
		System.out.println("삽입 정렬 결과: " + Arrays.toString(insertionArr));

		// 버블 정렬
		int[] bubbleArr = Arrays.copyOf(original, original.length);
		bubbleSort(bubbleArr);
		System.out.println("버블 정렬 결과: " + Arrays.toString(bubbleArr));
	}

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			// swap
			int temp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
		}
	}

	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
//		선택정렬
		int[] arr1 = {5, 3, 8, 4, 2};  // 정렬할 배열

        // 배열 길이보다 1 적은 만큼 반복
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;  // 현재 위치를 최소값 위치라고 가정

            // i 뒤에 있는 값들과 비교하며 진짜 최소값 찾기
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {  // 더 작은 값 발견
                    minIndex = j;  // 최소값 위치 업데이트
                }
            }

            // 최소값과 현재 위치의 값을 교환
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        // 정렬 결과 출력
        for (int num : arr) {
            System.out.print(num + " ");
        }
		
		
		
//		삽입 정렬
        int[] arr2 = {5, 3, 8, 4, 2};  // 정렬할 배열

        // 두 번째 요소부터 시작
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];  // 현재 삽입할 값 저장
            int j = i - 1;

            // 앞쪽 값들과 비교해서 key보다 큰 값들은 오른쪽으로 한 칸 이동
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // 값 밀기
                j--;
            }

            // 빈자리에 key 삽입
            arr[j + 1] = key;
        }

        // 정렬 결과 출력
        for (int num : arr) {
            System.out.print(num + " ");
        }
		
        
//      버블 정렬
        int[] arr3 = {5, 3, 8, 4, 2};  // 정렬할 배열

        // 배열 길이 -1 만큼 반복
        for (int i = 0; i < arr.length - 1; i++) {

            // 맨 뒤로 큰 값을 "버블처럼" 보내기
            for (int j = 0; j < arr.length - 1 - i; j++) {

                // 이웃한 두 값을 비교해서 큰 값을 오른쪽으로 보냄
                if (arr[j] > arr[j + 1]) {
                    // 값 교환
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // 정렬 결과 출력
        for (int num : arr) {
            System.out.print(num + " ");
        }
		
		
		
		
	}
}
