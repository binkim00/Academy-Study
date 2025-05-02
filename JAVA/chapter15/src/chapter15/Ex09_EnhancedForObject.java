package chapter15;

// House라는 '집' 클래스를 만든다!
class House {
    private int houseNum;    // 집 번호를 저장하는 변수
    private String name;     // 주인 이름을 저장하는 변수

    // House 객체를 만들 때 필요한 값을 넣는 생성자
    House(int houseNum, String name) {
        this.houseNum = houseNum;  // 입력받은 번호를 이 객체의 houseNum에 저장
        this.name = name;          // 입력받은 이름을 이 객체의 name에 저장
    }

    // houseNum 값을 외부에서 읽을 수 있게 해주는 메소드
    public int getHouseNum() {
        return houseNum;
    }

    // name 값을 외부에서 읽을 수 있게 해주는 메소드
    public String getName() {
        return name;
    }
}
    
public class Ex09_EnhancedForObject {

    public static void main(String[] args) {

        // House 객체를 저장할 배열을 5개 크기로 만든다.
        House[] arr = new House[5]; 

        // House 객체를 만들고, 배열에 차례로 넣는다.
        arr[0] = new House(101, "홍길동");  // 첫 번째 집: 101번, 주인 이름은 홍길동
        arr[1] = new House(102, "전우치");  // 두 번째 집: 102번, 주인 이름은 전우치
        arr[2] = new House(103, "손오공");  // 세 번째 집: 101번, 주인 이름은 손오공
        arr[3] = new House(104, "해리포터"); // 네 번째 집: 101번, 주인 이름은 해리포터
        arr[4] = new House(105, "멀린");    // 다섯 번째 집: 101번, 주인 이름은 멀린

        // 향상된 for문 (for-each문)을 이용해 배열을 차례대로 반복
        for (House e : arr) {  
            // 배열에서 꺼낸 e라는 House 객체의 houseNum이 102인지 확인
            if (e.getHouseNum() == 102 || e.getHouseNum() == 105) {  
                // houseNum이 102면 주인 이름을 출력
                System.out.println(e.getName());  
            }
        }
    }
}
