package chapter21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Ex11_ConvertExt
{
    public static void main(String[] args)
    {
        // 고정 크기 리스트 생성 (Arrays.asList 사용 시 크기 고정)
        List<String> fixedSizeList = 
        		Arrays.asList("홍길동", "전우치", "전우치", "손오공");
        		// Arrays.asList(...) : 간단하게 리스트를 생성하지만, 크기 변경 불가
        // ArrayList에 복사 → 수정 가능한 리스트로 변경
        ArrayList<String> list = new ArrayList<>(fixedSizeList);

        // 중복 제거 전 리스트 출력
        for(String s : list)
            System.out.print(s.toString() + '\t');
        System.out.println();

        // new HashSet<>(list) : 중복 제거
        // HashSet은 중복을 허용하지 않는 자료구조
        // list를 HashSet으로 변경하여 중복 데이터를 제거
        // 리스트를 HashSet에 넣으면 중복된 "전우치"가 한 번만 남음
        HashSet<String> set = new HashSet<>(list);

        // new ArrayList<>(set)
        // 중복이 제거된 Set을 다시 ArrayList 리스트로 변환 (순서 필요할 경우 사용)
        list = new ArrayList<>(set);

        // 중복 제거 후 저장된 리스트 데이터 출력하는 for문
        for(String s : list)
            System.out.print(s.toString() + '\t');
        System.out.println();
    }
}
