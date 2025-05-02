package practice;  // 이 코드는 practice라는 패키지(폴더)에 들어 있어요

// 데스크탑 클래스
class DeskTop {
    // toString() 메소드를 오버라이딩해서
    // 이 객체를 출력하면 아래 문장이 나오게 해요
    @Override
    public String toString() {
        return "데스크탑을 실행합니다.";
    }
}

// 노트북 클래스
class NoteBook {
    @Override
    public String toString() {
        return "노트북을 실행합니다.";
    }
}

// 폰 클래스
class Phone {
    @Override
    public String toString() {
        return "폰을 실행합니다.";
    }
}

// 태블릿 클래스
class Tablet {
    @Override
    public String toString() {
        return "태블릿을 실행합니다.";
    }
}

// 제네릭을 사용하는 컴퓨터 클래스
// T는 자료형(타입)을 나중에 정하겠다는 뜻이에요
class Computer<T> {
//    T는 'Type(타입)'의 약자야.
//    제네릭에서는 자료형(예: String, Integer, Phone, ...)을 나중에 넣겠다는 뜻	
    T computer;  // T타입의 기기를 담을 변수 (예: DeskTop, Phone 등)

    // 기기를 상자에 넣는 메소드
    public void set(T computer) {
        this.computer = computer;
    }

    // 기기를 상자에서 꺼내는 메소드
    public T get() {
        return computer;
    }
}

// 메인 클래스: 실행하는 곳
public class GenericTest {

    public static void main(String[] args) {
        // 컴퓨터 상자들을 각각 만들어요 (제네릭 타입 사용)
        // <> 안에는 어떤 기기 타입을 담을 건지 정해줘요

        Computer<DeskTop> deskTop = new Computer<>();   // 데스크탑 전용 상자
        Computer<NoteBook> noteBook = new Computer<>(); // 노트북 전용 상자
        Computer<Phone> phone = new Computer<>();       // 폰 전용 상자
        Computer<Tablet> tablet = new Computer<>();     // 태블릿 전용 상자

        // 각 상자에 기기를 넣어요 (객체 생성해서 넣기)
        deskTop.set(new DeskTop());
        noteBook.set(new NoteBook());
        phone.set(new Phone());
        tablet.set(new Tablet());

        // 상자에서 꺼낸 기기를 출력해요
        // 출력하면 각 기기의 toString() 메소드가 실행돼요
        System.out.println(deskTop.get());   // "데스크탑을 실행합니다."
        System.out.println(noteBook.get());  // "노트북을 실행합니다."
        System.out.println(phone.get());     // "폰을 실행합니다."
        System.out.println(tablet.get());    // "태블릿을 실행합니다."
    }
}
