package chapter23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex03_CollectionVsStream
{
    public static void main(String[] args)
    {
        int[] arr = {1, 5, 3, 2, 4};
        List<Integer> list = new ArrayList<>();

        // 컬렉션 프레임워크를 이용한 방식
        for (int i : arr)
        {
            if (i%2 == 1)
            {list.add(i);}
        }

        Collections.sort(list);
        
        for (int i : list)
        {System.out.print(i + "\t");}
        System.out.println();
        
        // Stream 을 이용한 방식
        Arrays.stream(arr)
            .filter(n -> n%2 == 1) // 조건에 맞는 데이터만 통과
            .sorted() // 정렬
            .forEach(n -> System.out.print(n + "\t")); // 각 요소를 반복 수행

        System.out.println();
        
//      컬렉션은 데이터를 저장하는 컨테이너
//      스트림은 컬렉션의 데이터를 가공하고 처리하는 도구
//      함께 쓰면 데이터를 저장하고 → 스트림으로 가공하는 이중 구조로 사용 가능
        
//| 항목 				| **컬렉션(Collection)** 							| **스트림(Stream)** 					|
//|-----------------|-----------------------------------------------|-----------------------------------|
//| 🔍 목적 			| 데이터를 **저장** 								| 데이터를 **처리(가공)** 				|
//| 🔄 동작 방식 		| **외부 반복** (개발자가 `for`, `while` 등 직접 제어) 	| **내부 반복** (스트림이 알아서 처리) 		|
//| 🔄 처리 방식 		| 요소 하나씩 수동 처리								| 함수형 방식으로 **선언형 처리** 			|
//| 🔁 재사용 			| **재사용 가능** (필요할 때마다 꺼내 씀) 				| **1회성** (최종 연산 후 소멸) 			|
//| 🧠 지연 연산 		| 없음 (바로 실행됨) 								| 있음 (최종 연산이 있어야 실행됨) 			|
//| ⚙️ 중간 연산 체인 	| 불가능 											| 가능 (`filter → map → sorted`) 		|
//| ⚡ 병렬 처리 		| 직접 쓰레드 관리 									| `.parallelStream()`으로 간편 병렬 처리 	|
//| 💾 저장 여부 		| 데이터를 저장하는 구조체 (예: `List`, `Set`) 			| 저장 기능 없음. **흘러가는 데이터 흐름** 	|
//| 🛠 API 패키지 		| `java.util` 									| `java.util.stream` 				|
//| 🎯 주요 사용 목적 	| 데이터를 담고 보관 									| 데이터를 필터, 매핑, 집계 등 처리 			|
        
        
    }
}
