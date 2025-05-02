package chapter21;

import java.util.HashSet;
import java.util.Set;

class Student2{
	int studentNum;
	String name;
	int age;
	Student2(int studentNum, String name,int age){
		this.studentNum = studentNum;
		this.name = name;
		this.age = age;
	}
//	@Override
//	public int hashCode() {
////		hashcode를 studentNum으로 변경하여 반환
//		return this.studentNum;
//	}
	@Override
	public boolean equals(Object obj) {
		if(this.studentNum == ((Student2)obj).studentNum) {
			return true;
		}else {
			return false;
		}
	}
}

public class Ex07_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Student2> set = new HashSet<Student2>();
//		new로 만들어진 객체는 전부 다른 객체로 인식=>객체는 메모리 주소를 각자 가지고 있음
//		객체를 같은 내용으로 인식하게 바꾸려면 HashCode가 같도록 변경해야함
		set.add(new Student2(1,"홍길동",20));
		set.add(new Student2(1,"홍길동",20));
		set.add(new Student2(2,"전우치",25));
		
		System.out.println("저장된 객체의 수: " +set.size());
		for(Student2 data : set) {
			System.out.println(data.name + "," + data.age);
		}
	}

}
