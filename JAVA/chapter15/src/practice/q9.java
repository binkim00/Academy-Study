package practice;

import java.util.Arrays;

public class q9 {

	public static void main(String[] args) {
		int[] original = {15, 7, 2, 9, 10, 12, 17, 11, 20, 5};

		// ���� ����
		int[] selectionArr = Arrays.copyOf(original, original.length);
		selectionSort(selectionArr);
		System.out.println("���� ���� ���: " + Arrays.toString(selectionArr));

		// ���� ����
		int[] insertionArr = Arrays.copyOf(original, original.length);
		insertionSort(insertionArr);
		System.out.println("���� ���� ���: " + Arrays.toString(insertionArr));

		// ���� ����
		int[] bubbleArr = Arrays.copyOf(original, original.length);
		bubbleSort(bubbleArr);
		System.out.println("���� ���� ���: " + Arrays.toString(bubbleArr));
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
		
//		��������
		int[] arr1 = {5, 3, 8, 4, 2};  // ������ �迭

        // �迭 ���̺��� 1 ���� ��ŭ �ݺ�
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;  // ���� ��ġ�� �ּҰ� ��ġ��� ����

            // i �ڿ� �ִ� ����� ���ϸ� ��¥ �ּҰ� ã��
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {  // �� ���� �� �߰�
                    minIndex = j;  // �ּҰ� ��ġ ������Ʈ
                }
            }

            // �ּҰ��� ���� ��ġ�� ���� ��ȯ
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        // ���� ��� ���
        for (int num : arr) {
            System.out.print(num + " ");
        }
		
		
		
//		���� ����
        int[] arr2 = {5, 3, 8, 4, 2};  // ������ �迭

        // �� ��° ��Һ��� ����
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];  // ���� ������ �� ����
            int j = i - 1;

            // ���� ����� ���ؼ� key���� ū ������ ���������� �� ĭ �̵�
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // �� �б�
                j--;
            }

            // ���ڸ��� key ����
            arr[j + 1] = key;
        }

        // ���� ��� ���
        for (int num : arr) {
            System.out.print(num + " ");
        }
		
        
//      ���� ����
        int[] arr3 = {5, 3, 8, 4, 2};  // ������ �迭

        // �迭 ���� -1 ��ŭ �ݺ�
        for (int i = 0; i < arr.length - 1; i++) {

            // �� �ڷ� ū ���� "����ó��" ������
            for (int j = 0; j < arr.length - 1 - i; j++) {

                // �̿��� �� ���� ���ؼ� ū ���� ���������� ����
                if (arr[j] > arr[j + 1]) {
                    // �� ��ȯ
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // ���� ��� ���
        for (int num : arr) {
            System.out.print(num + " ");
        }
		
		
		
		
	}
}
