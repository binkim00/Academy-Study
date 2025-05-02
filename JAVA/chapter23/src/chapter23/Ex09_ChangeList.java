package chapter23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class MStudent{
	String name;
	int grade;
	MStudent(String name, int grade){
		this.name = name;
		this.grade = grade;
	}
}
class HStudent{
	String name;
	int grade;
	HStudent(String name, int grade){
		this.name = name;
		this.grade = grade;
	}
}

public class Ex09_ChangeList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<MStudent> ms = new ArrayList<>();
		ms.add(new MStudent("홍길동", 1));
		ms.add(new MStudent("전우치", 2));
		ms.add(new MStudent("손오공", 3));
		ms.add(new MStudent("해리포터", 3));
		ms.add(new MStudent("멀린", 3));
		
		List<HStudent> hs = ms.stream()
				.filter(s-> s.grade == 3 )
				.map(s->new HStudent(s.name, s.grade))
				// map에서 저장된 결과를 List로 변경하여 저장
				.collect(Collectors.toList());
		for(HStudent s : hs) {
			System.out.println(s.name+","+"s.grade");
		}
	}

}






