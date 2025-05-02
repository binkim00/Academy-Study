package practice1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 학점(‘A’, ‘B’, ‘C’, ‘D’, ‘F’)을 컬렉션에 저장하라. 그러고 나서 컬렉션을 검색하여 학점을 점수
//		(A=4.0, B=3.0, C=2.0, D=1.0, F=0.0)로 변환하여 출력하는 프로그램을 작성하라. 
//		1) ArrayList 컬렉션을 이용
		List<String> scoreList = new ArrayList<>();
		scoreList.add("A");
		scoreList.add("B");
		scoreList.add("C");
		scoreList.add("D");
		scoreList.add("F");
		String input = "A";
		for(int i=0; i<scoreList.size(); i++) {
			if(scoreList.get(i).equals("A")){
				System.out.println(4.0);
			}else if(scoreList.get(i).equals("B")) {
				System.out.println(3.0);
			}else if(scoreList.get(i).equals("C")) {
				System.out.println(2.0);
			}else if(scoreList.get(i).equals("D")) {
				System.out.println(1.0);
			}else if(scoreList.get(i).equals("F")) {
				System.out.println(0.0);
			}
		}
//		2) HashMap 컬렉션을 이용 
		HashMap<String, Double> map = new HashMap<>();
		map.put("A", 4.0);
		map.put("B", 3.0);
		map.put("C", 2.0);
		map.put("D", 1.0);
		map.put("F", 0.0);
		
		for(String key : map.keySet()) {
			System.out.println(key+"="+map.get(key));
		}
	}

}












