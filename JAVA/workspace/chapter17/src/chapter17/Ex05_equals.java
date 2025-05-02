package chapter17;

// Book2 라는 책 정보를 담는 클래스 만들기!
class Book2 {

    // id: 책을 구분하는 고유 번호 (예: 주민등록번호, 학번 같은 역할)
    Long id;
    String name;    // 책 제목
    String author;  // 저자 이름

    // 생성자: 책을 만들 때 제목과 저자를 적어주면 저장!
    Book2(String name, String author) {
        this.name = name;
        this.author = author;
    }

    // toString: 객체를 출력할 때 "책제목,저자" 형식으로 보여주기
    @Override
    public String toString() {
        return name + "," + author;
    }

    // equals: 두 책이 같은 책인지 비교하는 메서드
    @Override
    public boolean equals(Object obj) {
        // 내 책의 제목 + 저자를 str 변수에 저장
        String str = this.toString();

        // obj.toString() = 비교할 책의 제목 + 저자 문자열
        // name.equals() = 책 제목끼리 같은지 확인
        return name.equals(obj.toString());
        // 이 코드는 실제로 "책 제목"만 비교해서 같으면 true!
        // 저자 이름, id는 비교하지 않음!
    }
}

public class Ex05_equals {

    public static void main(String[] args) {

        // equals: 자바의 Object 클래스 기본 메서드
        // 객체끼리 "같다"를 판단하는 방법!
        // 오버라이딩하지 않으면 ==와 똑같이 "주소" 비교.

        Book2 book1 = new Book2("자바 프로그래밍", "이재환");  // 책 하나 생성
        Book2 book2 = new Book2("HTML", "이재환");            // 또 다른 책 생성
        Book2 book3 = new Book2("자바 프로그래밍", "이재환");  // book1과 같은 제목, 저자!

        // == 는 주소 비교!
        // new로 만든 객체는 메모리 위치가 다르기 때문에 false!
        System.out.println(book1 == book3);  // false 출력 (서로 다른 객체)

        // equals는 오버라이딩한 메서드 사용!
        // 책 제목이 같으면 true!
        System.out.println(book1.equals(book3));  // true 출력
    }
}
