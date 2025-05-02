package chapter15;

public class Ex10_ForInFor {

    public static void main(String[] args) {

        // 3�� 3��¥�� 2���� �迭�� �����.
        int[][] arr = new int[3][3];

        // �迭�� �� ���ڸ� 1���� ����
        int num = 1;

        // ù ��° for��: ���� ���鼭
        for (int i = 0; i < 3; i++) {
            // �� ��° for��: ���� ���鼭
            for (int j = 0; j < 3; j++) {
                arr[i][j] = num;  // �迭 ĭ�� num �� ����
                num++;            // num�� 1�� ���� (���� ���� �غ�)
            }
        }

        // ������ �迭�� ���� �� ä������,
        // �̹����� ����ϴ� �ݺ���!
        for (int i = 0; i < 3; i++) {      // ���� ���ʴ�� �ݺ�
            for (int j = 0; j < 3; j++) {  // ���� ���ʴ�� �ݺ�
                System.out.print(arr[i][j] + "\t");  
                // �迭�� ����� ���ڸ� ����ϰ� ������ ���
            }
            System.out.println();  // �� �� ��� ������ �� �ٲ�
        }
    }
}
