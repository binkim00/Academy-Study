package chapter15;

// House��� '��' Ŭ������ �����!
class House {
    private int houseNum;    // �� ��ȣ�� �����ϴ� ����
    private String name;     // ���� �̸��� �����ϴ� ����

    // House ��ü�� ���� �� �ʿ��� ���� �ִ� ������
    House(int houseNum, String name) {
        this.houseNum = houseNum;  // �Է¹��� ��ȣ�� �� ��ü�� houseNum�� ����
        this.name = name;          // �Է¹��� �̸��� �� ��ü�� name�� ����
    }

    // houseNum ���� �ܺο��� ���� �� �ְ� ���ִ� �޼ҵ�
    public int getHouseNum() {
        return houseNum;
    }

    // name ���� �ܺο��� ���� �� �ְ� ���ִ� �޼ҵ�
    public String getName() {
        return name;
    }
}
    
public class Ex09_EnhancedForObject {

    public static void main(String[] args) {

        // House ��ü�� ������ �迭�� 5�� ũ��� �����.
        House[] arr = new House[5]; 

        // House ��ü�� �����, �迭�� ���ʷ� �ִ´�.
        arr[0] = new House(101, "ȫ�浿");  // ù ��° ��: 101��, ���� �̸��� ȫ�浿
        arr[1] = new House(102, "����ġ");  // �� ��° ��: 102��, ���� �̸��� ����ġ
        arr[2] = new House(103, "�տ���");  // �� ��° ��: 101��, ���� �̸��� �տ���
        arr[3] = new House(104, "�ظ�����"); // �� ��° ��: 101��, ���� �̸��� �ظ�����
        arr[4] = new House(105, "�ָ�");    // �ټ� ��° ��: 101��, ���� �̸��� �ָ�

        // ���� for�� (for-each��)�� �̿��� �迭�� ���ʴ�� �ݺ�
        for (House e : arr) {  
            // �迭���� ���� e��� House ��ü�� houseNum�� 102���� Ȯ��
            if (e.getHouseNum() == 102 || e.getHouseNum() == 105) {  
                // houseNum�� 102�� ���� �̸��� ���
                System.out.println(e.getName());  
            }
        }
    }
}
