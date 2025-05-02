package chapter15;

class BoxA4{
	public String name;
	public int age;
	BoxA4(String name,int age){
		this.name = name;
		this.age = age;
	}
}

public class Ex04_BoxArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoxA4[] ar = new BoxA4[4];
		
//		데이터 저장시 반드시 new를 이용하여 저장
		ar[0] = new BoxA4("홍길동",20);
		ar[1] = new BoxA4("전우치",25);
		ar[2] = new BoxA4("손오공",30);
//		new를 이용하여 인스턴스를 생성하지 않으면 에러가 발생
//		하지만 코드 상으로는 에러가 보이지 않음
//		ar[3].name = "이순신";
//		ar[3].age = 35;
		
		System.out.println(ar[0].name+","+ar[0].age);
		System.out.println(ar[1].name+","+ar[1].age);
		System.out.println(ar[2].name+","+ar[2].age);
//		System.out.println(ar[3].name+","+ar[3].age);
	}

}









