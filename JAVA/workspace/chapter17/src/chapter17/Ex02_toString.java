package chapter17;

// Book이라는 클래스 선언
class Book {
    // 책 이름과 저자를 저장할 변수
    String name;
    String author;

    // Object 클래스의 toString() 메서드를 오버라이딩!
    @Override
    public String toString() {
        // 이 객체를 출력할 때 책 이름과 저자가 나오도록 설정
        return this.name + "," + this.author;
    }
}

public class Ex02_toString {

    public static void main(String[] args) {

        // 자바의 기본 클래스 설명:
        // java.lang → 자바에서 자주 쓰는 클래스들이 모여있는 패키지명
        // Object → 모든 클래스의 부모! 기본 메서드 제공 (toString, equals 등)

        // Ex02_toString 클래스의 객체 생성
        Ex02_toString ex02 = new Ex02_toString();

        // 객체를 출력할 때 자동으로 toString()이 실행됨
        // toString을 오버라이딩하지 않았으므로 → 패키지명.클래스명@16진수주소가 출력됨
        System.out.println(ex02);
        System.out.println(ex02.toString());

        // Object 클래스로 만든 객체
        Object obj = new Object();
        // Object는 모든 클래스가 상속받고 있고,
        // 어떤 클래스든 Object의 메서드를 사용할 수 있음!

        // String 클래스는 이미 toString()을 오버라이딩해서,
        // 문자열을 출력할 때 실제 문자열 내용이 나오게 되어있음!
        String name = "홍길동";
        System.out.println(name);             // "홍길동" 출력
        System.out.println(name.toString());  // "홍길동" 출력

        // Book 클래스의 객체 생성
        Book book1 = new Book();
        book1.name = "자바 프로그래밍";  // 책 제목 입력
        book1.author = "이재환";         // 저자 입력

        // book1을 출력하면 toString() 메서드가 자동 실행되어
        // 책 이름과 저자 출력됨
        System.out.println(book1.toString());  // "자바 프로그래밍,이재환"
        System.out.println(book1);             // toString() 생략해도 결과 같음

    }
}
