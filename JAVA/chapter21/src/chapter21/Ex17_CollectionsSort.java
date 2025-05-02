package chapter21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 문자열 길이를 기준으로 정렬하는 Comparator 구현 클래스
class StringLength implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		// 문자열의 길이 차이로 정렬 (오름차순)
		return o1.length() - o2.length();
	}
}

public class Ex17_CollectionsSort
{
    public static void main(String[] args)
    {
        // 리스트 생성: 고정 크기 리스트 → ArrayList로 변환해서 수정 가능하게 만듦
        List<String> list = Arrays.asList("홍길동", "전우치", "손오공", "멀린");
        list = new ArrayList<>(list);

        // 정렬 이전 출력       
        System.out.println(list);

        // 리스트 정렬
        // sort(리스트 변수) : 오름차순 정렬 : 직접 만든 클래스는 Comparable을 상속받아야 사용가능
        // → 기본적으로 문자열은 Comparable 구현되어 있어서 바로 정렬 가능
        Collections.sort(list);  // 가나다순 (유니코드 기준 정렬)
        System.out.println(list);

        // sort(리스트 변수, Collections.reverseOrder()) : 내림차순 정렬
        // → 정렬 기준을 반대로 뒤집어서 내림차순으로 정렬
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
        
        // sort(리스트 변수, 직접만든 Comparator 클래스) : 정렬 방식을 직접 설정
        // → 사용자 정의 클래스 StringLength 를 사용해서 길이 오름차순 정렬
        Collections.sort(list, new StringLength());
        System.out.println(list);
        
        // sort(리스트 변수, 람다식으로 만든 Comparator 클래스) : 정렬 방식을 직접 설정
        // → 람다식을 이용해 길이 내림차순 정렬 (간결하게 표현 가능)
        Collections.sort(list, (a, b) -> b.length() - a.length());
        System.out.println(list);
    }
}
