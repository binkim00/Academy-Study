package chapter21;

// 필요한 여러 클래스들을 불러오기 (import)
import java.util.ArrayList;   // ArrayList 사용
import java.util.Arrays;      // Arrays.asList() 메소드 사용
import java.util.Iterator;    // Iterator 사용
import java.util.LinkedList;  // LinkedList 사용
import java.util.List;        // List 인터페이스 사용

public class Ex04_Convert {

    public static void main(String[] args) {

        // 문자열 배열 대신 Arrays.asList()를 사용하여 고정된 크기의 리스트 만들기
        List<String> list = Arrays.asList("홍길동", "전우치", "손오공", "전우치");

        // 위에서 만든 고정 크기 리스트를 ArrayList로 복사해서, 수정 가능하도록 함
        list = new ArrayList<>(list);  // 이제 add() 같은 메소드 사용 가능!

        // 리스트에 새로운 요소 추가
        list.add("해리포터");

        // Iterator를 사용해서 리스트 요소들을 하나씩 출력
        for (Iterator<String> itr = list.iterator(); itr.hasNext(); ) {
            System.out.println(itr.next() + "\t");  // 한 줄에 하나씩 출력
        }
        System.out.println(); // 줄 바꿈

        // ArrayList 형태였던 list를 LinkedList로 바꾸기
        list = new LinkedList<>(list);  // 같은 내용으로 LinkedList 생성

        // 향상된 for문을 사용해서 리스트 요소들 출력
        for (String s : list) {
            System.out.println(s + "\t");  // 한 줄에 하나씩 출력
        }
        System.out.println(); // 줄 바꿈
    }
}
