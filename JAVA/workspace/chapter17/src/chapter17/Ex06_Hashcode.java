package chapter17;

// Car라는 클래스 선언
class Car {
    Long id;  // 차의 고유 id
    String name;  // 차 이름 (현재 사용하지 않음)

    // hashCode 메서드 오버라이딩
    @Override
    public int hashCode() {
        // id 값을 기반으로 해시 코드(정수)를 반환
        // Long 타입의 id를 int로 변환하여 반환
        return id.intValue();
    }

    // toString 메서드 오버라이딩
    @Override
    public String toString() {
        // id 값을 문자열로 변환하여 반환
        // 기본적으로 메모리 주소를 출력하는 대신 id만 출력
        return id.toString();
    }

    // equals 메서드 오버라이딩
    @Override
    public boolean equals(Object obj) {
        // obj와 현재 객체의 id 값이 같은지 비교
        // id.hashCode()가 같은지 확인
        return id.hashCode() == id.hashCode();
        // 실제로는 자식 객체와 부모 객체의 id를 비교해야 하므로, 이 부분은 잘못된 코드
        // 올바르게 작성하려면 obj도 Car 타입으로 형변환하고 비교해야 함.
    }
}

public class Ex06_Hashcode {

    public static void main(String[] args) {
        // 두 개의 Car 객체를 생성
        Car car1 = new Car();
        car1.id = 1L;  // 첫 번째 차의 id 설정
        Car car2 = new Car();
        car2.id = 2L;  // 두 번째 차의 id 설정

        // hashCode : 객체의 메모리 주소에 기반하여 10진수로 숫자를 반환
        System.out.println(car1.hashCode());  // car1의 해시 코드 출력
        System.out.println(car2.hashCode());  // car2의 해시 코드 출력

        // toString() : 객체를 출력할 때, 기본적으로 객체의 메모리 주소를 출력하지만,
        // 여기서는 id를 문자열로 출력하도록 오버라이딩 했기 때문에 id 값만 출력
        System.out.println(car1);  // car1의 toString() 결과 출력
        System.out.println(car2);  // car2의 toString() 결과 출력

        // == : 두 객체가 메모리에서 같은 주소를 가리키는지 확인
        // car1과 car2는 다른 객체이므로 false 출력
        System.out.println(car1 == car2);  // false 출력

        // equals() : 두 객체가 내용이 같은지 비교
        // 이 코드에서는 hashCode() 비교만 했기 때문에 정확한 비교는 안 됨.
        // 실제로는 두 객체의 id 값을 비교해야 한다.
        System.out.println(car1.equals(car2));  // false 출력
    }
}
