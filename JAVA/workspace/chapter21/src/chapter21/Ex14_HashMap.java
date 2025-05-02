package chapter21;

import java.util.HashMap;

// 학생 정보를 담을 클래스 (데이터 객체 역할)
class Student5 {
	int studno;     // 학번
	String name;    // 이름
	String id;      // 아이디
	int grade;      // 학년
}

public class Ex14_HashMap
{
    public static void main(String[] args)
    {
    	// Map은 키와 value값을 가진 자료구조
    	// → 데이터를 키-값 쌍으로 저장 (예: "이름" : "전화번호")
    	
    	// key : value를 꺼내기 위해 사용하는 id, Primay Key의 데이터를 설정하는 경우가 많음
    	// → key는 주로 "유일한 식별자" 역할을 함. DB의 PK와 비슷한 개념.
    	
    	// value : 데이터, 테이블에 들어있는 데이터를 객체로 만들어 저장
    	// → key에 대응되는 실제 정보나 객체를 담는 부분

        HashMap<String, String> map = new HashMap<>();
        
        // Key-Value 기반 데이터 저장
        // put(key, value) : 데이터 추가 메서드
        map.put("홍길동", "010-1234-1443");
        map.put("전우치", "010-4321-1446");
        map.put("손오공", "010-9876-1443");
        // → 같은 key에 다시 put하면 기존 값이 덮어쓰기됨 (중복 허용 안 함)

        // 데이터 탐색
        // get(key) : value를 출력하는 메서드
        System.out.println("홍길동: " + map.get("홍길동"));
        System.out.println("전우치: " + map.get("전우치"));
        System.out.println("손오공: " + map.get("손오공"));
        System.out.println();

        // 데이터 삭제
        // remove(key) : 해당하는 key를 찾아서 key와 value 전부 삭제
        map.remove("손오공");

        // 데이터 삭제 확인
        // get 실행시 삭제된 데이터는 null을 반환
        System.out.println("손오공: " + map.get("손오공")); // null 출력

//      PK를 검색해서 데이터를 출력하는 경우가 가장 많기 때문에
//      PK를 기준으로 검색하는 속도를 Map을 사용하면 훨씬 빠르게 검색 가능
//      → HashMap은 내부적으로 해시 테이블 구조를 사용해서 O(1)에 가까운 빠른 검색 속도를 제공함

//      데이터의 추가 삭제가 빠름  
//      → ArrayList 등 순차 구조보다 특정 키를 기준으로 데이터 관리할 때 훨씬 효율적임

//      PK:studno
//      → 학생 정보를 저장할 때 학번(studno)을 키로 사용하면 유일하고 검색도 빠름
        HashMap<Integer, Student5> studMap = new HashMap<>();
        // → 이후에 studMap.put(학번, 학생객체) 형식으로 저장하면 학생 정보를 쉽게 다룰 수 있음
    }
}
