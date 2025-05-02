package chapter21;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

// 문자열 길이 기준으로 정렬하는 Comparator 클래스
class MyStringComparator implements Comparator<String>
{
    public int compare(String s1, String s2)
    {
        // 길이가 동일한 데이터는 추가되지 않는다.
        // → TreeSet은 compare() 결과가 0이면 "같은 값"으로 인식해서 중복으로 판단함.
        // 즉, 문자열 자체는 달라도 길이가 같으면 저장되지 않음.
        return s1.length() - s2.length();  // 문자열 길이로 오름차순 정렬
    }
}

public class Ex10_Comparator
{
    public static void main(String[] args)
    {
//        Set<String> tree = new TreeSet<>(); // 멀린	전우치	해리포터	홍길동
//        → 기본 정렬: 문자열 사전순 정렬 (유니코드 기준)
    	
    	// MyStringComparator 클래스를 이용하여 글자수를 기준으로 비교
    	// 이미 만들어진 String 클래스의 정렬 기준을 변경할 수 있음
//        Set<String> tree = new TreeSet<>(new MyStringComparator()); // 멀린	홍길동 해리포터
        // → 위에서 만든 문자열 길이 비교 클래스를 정렬 기준으로 넘겨줌
        // 이 TreeSet은 문자열의 길이로 정렬되고, 길이가 같으면 중복으로 판단함
    	// Comparator.reverseOrder() 내림에 필요한 메서드가 설정되어있음
//        Set<String> tree = new TreeSet<>(Comparator.reverseOrder()); // 홍길동	해리포터	전우치	멀린
    	
    	// 중복해서 사용할 수 없고 한 번만 사용할 때
//    	Set<String> tree = new TreeSet<>(new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.length() - o2.length();
//			}
//		});
    	
    	
    	// 람다식 : 메서드를 더 쉽게 작성하는 방식
    									// ↓매개변수 -> 실행할 코드
    	Set<String> tree = new TreeSet<>((o1,o2)->o1.length()-o2.length());
    	
        tree.add("홍길동");       // 길이 3
        tree.add("전우치");       // 길이 3 → "홍길동"과 길이 같으므로 중복 처리됨 (저장 안 됨)
        tree.add("전우치");       // 길이 3 → 역시 저장 안 됨
        tree.add("멀린");        // 길이 2 → 저장됨
        tree.add("해리포터");     // 길이 5 → 저장됨

        for(String s : tree)
            System.out.print(s.toString() + '\t');
        // → TreeSet은 자동으로 정렬된 순서대로 저장되어 있어서,
        // 문자열 길이가 짧은 순서대로 출력됨: "멀린", "홍길동", "해리포터"

        System.out.println();
    }
}
