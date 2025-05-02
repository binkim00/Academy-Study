package practice1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student{
	String name;
	String dept;
	int dno;
	double score;
	public Student(String name, String dept, int dno, double score) {
		this.name = name;
		this.dept = dept;
		this.dno = dno;
		this.score = score;
	}
	@Override
	public String toString() {
		return this.name+","+this.dept+","+this.dno+","+this.score;
	}
}

public class Q4 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		4. 학생정보를 나타내는 Student클래스에 이름, 학과, 학번, 학점을 저장하는 필드를 작성하라.  
//		(1) 
//		학생 객체를 생성하고 5명을 학생정보를 ArrayList<Student>컬렉션에 저장한 후에, 
//		ArrayList<Student>의 모든학생(5명) 정보를 출력하고 학생의 이름을 입력받아 해당 학생의 학생정
//		보를 출력하는 프로그램을 작성하라. 
//		(2) ArrayList<Student> 대신, HashMap<String, Student> 해시맵을 이용하여 다시 작성하라. 해시
//		맵의 키(key)는 학생이름으로 한다. 
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> studentList = new ArrayList<>();
		HashMap<String,Student> studentMap = new HashMap<>();
		System.out.println("--------------------------------------------");
		System.out.println("학생이름, 학과, 학번, 학점을 입력하세요.");
		while(studentList.size()<5) {
			System.out.print(">>");
			String name = sc.next();
			String dept = sc.next();
			int dno = sc.nextInt();
			double score = sc.nextDouble();
			studentList.add(new Student(name, dept, dno, score));
			studentMap.put(name, new Student(name, dept, dno, score));
		}
//		for(Student s : studentList) {
//			System.out.println("-----------------------------");
//			System.out.println("이름 : "+s.name);
//			System.out.println("학과 : "+s.dept);
//			System.out.println("학번 : "+s.dno);
//			System.out.println("학점 : "+s.score);
//		}
		for(String key : studentMap.keySet()) {
			System.out.println("-----------------------------");
			Student s = studentMap.get(key);
			System.out.println("이름 : "+s.name);
			System.out.println("학과 : "+s.dept);
			System.out.println("학번 : "+s.dno);
			System.out.println("학점 : "+s.score);
		}
		while(true) {
			System.out.print("학생 이름 >>");
			String input = sc.next();
			if(input.equals("그만")) {
				System.out.println("End");
				break;
			}
//			list 데이터 찾기
			for(Student stu : studentList) {
				if(stu.name.equals(input)) {
					System.out.println(stu);
				}
			}
//			map 데이터 찾기
			Student stu = studentMap.get(input);
			System.out.println(stu);
		}
//		-------------------------------------------- 
//		학생이름, 학과, 학번, 학점을 입력하세요.  
//		>> 홍길동 모바일 1 4.1  
//		>> 이재문 안드로이드 2 3.9 
//		>> 김남윤 웹공학 3 3.5 
//		>> 최찬미 빅데이터 4 4.25 
//		>> 정영웅 컴퓨터공학 5 3.0 
//		---------------------------------------- 
//		이름 : 홍길동 
//		학과 : 모바일 
//		학번 : 1 
//		학점 : 4.1 
//		----------------------------- 
//		이름 : 이재문 
//		학과 : 안드로이드  
//		학번 : 2  
//		학점 : 3.9 
//		------------------------------ 
//		이름 : 김남윤 
//		학과 : 웹공학 
//		학번 : 3 
//		학점 : 3.5 
//		---------------------------- 
//		이름 : 최찬미 
//		학과 : 빅데이터 
//		학번 : 4 
//		학점 : 4.25 
//		---------------------------- 
//		이름 : 정영웅 
//		학과 : 컴퓨터공학 
//		학번 : 5 
//		학점 : 3.0 
//		---------------------------- 
//		학생 이름 >> 최찬미 
//		최찬미, 빅데이터, 4, 4.25 
//		학생 이름 >> 이재문 
//		이재문, 안드로이드, 2, 3.9 
//		학생 이름 >> 그만
	}
}
