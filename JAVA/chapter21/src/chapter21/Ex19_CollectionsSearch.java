package chapter21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex19_CollectionsSearch {

	public static void main(String[] args) {
		// -------------------------------
		// 1. List에서 이진 검색(binarySearch) 사용
		// -------------------------------
		List<String> list = new ArrayList();  // 문자열 리스트 생성
		list.add("홍길동");
		list.add("전우치");
		list.add("손오공");

		// 이진 검색을 하기 위해서는 반드시 정렬이 되어 있어야 함
		Collections.sort(list);  // 사전순(오름차순) 정렬
		System.out.println(list);  // 정렬된 리스트 출력

		// binarySearch(리스트, 검색값) : 이진 검색
		// 해당 값이 존재하면 인덱스를 반환함
		int idx1 = Collections.binarySearch(list, "손오공");
		System.out.println("손오공의 인덱스값: " + idx1);

		// 찾는 값이 없을 경우에는 음수 반환
		// 반환값 = -(삽입할 위치) - 1
		int idx2 = Collections.binarySearch(list, "멀린");
		System.out.println("멀린 데이터가 없을 경우: " + idx2);
		// → 예: "멀린"은 리스트에 없고 삽입할 위치가 1이라면, 반환값은 -2

		// -------------------------------
		// 2. Map에서 키를 이용한 검색
		// -------------------------------

		Map<String, Integer> map = new HashMap<>();
		map.put("홍길동", 1);
		map.put("전우치", 2);
		map.put("손오공", 3);

		// get(key) : key에 해당하는 value 값을 반환
		System.out.println(map.get("홍길동"));  // 출력: 1
		// → Map은 이진 탐색이 아니라 해시 테이블을 사용하므로 key를 통해 빠르게 검색 가능
	}
}
