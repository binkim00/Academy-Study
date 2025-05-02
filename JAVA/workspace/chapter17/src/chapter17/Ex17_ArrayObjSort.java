package chapter17;

import java.util.Arrays;

class Person implements Comparable{
	private String name;
	private int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(Object o) {
//		캐스팅을 통해 자식 클래스인 Person으로 변경 => name,age를 사용할 수 있음
		Person p = (Person)o;
////		String 클래스의 compareTo를 이용하여 문자열의 순서를 비교
//		int nNum = this.name.compareTo(p.name);
//		return nNum;
		
		if(this.age > p.age) {
			return 1;
		}else if(this.age < p.age) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public String toString() {
		return name + ":" + age;
	}
}

public class Ex17_ArrayObjSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person[] arr = new Person[3];
		arr[0] = new Person("홍길동", 29);
		arr[1] = new Person("전우치", 15);
		arr[2] = new Person("손오공", 37);
		
		Arrays.sort(arr);
		for(Person p : arr) {
			System.out.println(p);
		}
	}

}







