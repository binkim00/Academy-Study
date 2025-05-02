package chapter22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Student{
	int studentNo;
	String name;
	String dept;
	Student(int studentNo,String name,String dept ){
		this.studentNo =studentNo;
		this.name = name;
		this.dept = dept;
	}
}

class StudentList{
	private List<Student> students = new ArrayList<>();
	public StudentList() {
		this.students.add(new Student(2,"홍길동", "컴공"));
		this.students.add(new Student(3,"전우치","영어"));
		this.students.add(new Student(1,"손오공","중국어"));
	}
	public void showList() {
		for(Student stu : students) {
			System.out.println(stu.studentNo+","+stu.name+","+stu.dept);
		}
	}
	public void AscList() {
		students.sort(new SortComparator());
	}
	public void DescList() {
		students.sort(new DescComparator());
	}
//	private class : 외부에서는 부를 수 없는 클래스 생성
	private class SortComparator implements Comparator<Student>{
		@Override
		public int compare(Student o1, Student o2) {
			return o1.studentNo - o2.studentNo;
		}
	}
	private class DescComparator implements Comparator<Student>{
		@Override
		public int compare(Student o1, Student o2) {
			return o2.studentNo - o1.studentNo;
		}
	}
}

public class Ex01_Comparator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentList sl = new StudentList();
		sl.showList();
		
		sl.AscList();
		sl.showList();
		
		sl.DescList();
		sl.showList();
	}

}







