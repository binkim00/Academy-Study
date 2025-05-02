package chapter24;

import java.io.File;
import java.util.Scanner;

public class Ex14_Scanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner 장점
//		텍스트 데이터를 읽을 때 여러가지 기능을 사용할 수 있음
//		공백, 줄단위, 숫자, 단어, 단위로 읽고 싶을 때
//		파싱 기능이 뒤어남
//		어떤 큰 자료에서 원하는 정보만 가공하고 싶을 때
//		뽑아서 원하는 때에 불러올 수 있게 하는 것
		try(Scanner sc = new Scanner(new File("log.txt"))){
			while(sc.hasNext()) {
//				nextLine() 파일의 내용을 한줄 씩 읽을때
//				System.out.println(sc.nextLine());
				System.out.println(sc.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Scanner sc = new Scanner(new File("year.txt"))){
			while(sc.hasNext()) {
//				nextLine() 파일의 내용을 한줄 씩 읽을때
//				System.out.println(sc.nextLine());
				System.out.println(sc.nextInt());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try(Scanner sc = new Scanner(new File("새 텍스트 문서.txt"))){
			while(sc.hasNext()) {
				String name = sc.next();
				int age = sc.nextInt();
				int toll = sc.nextInt();
				int weight = sc.nextInt();
				String location = sc.next();
				Student s = new Student(name,age,toll,weight,location);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

class Student {
	String name;
	int age;
	int toll;
	int weight;
	String location;
	public Student(String name, int age, int toll, int weight, String location) {
		super();
		this.name = name;
		this.age = age;
		this.toll = toll;
		this.weight = weight;
		this.location = location;
	}
	
}









