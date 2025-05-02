package practice1;

class MyPoint{
	int x;
	int y;
	MyPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "MyPoint("+x+","+y+")";
	}
}

public class Q1_MyPointUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 다음 main()의 실행 결과 클래스명과 점 값을 연결하여 “MyPoint(3,20)”이 출력되도록 
//		MyPoint 클래스를 작성하라. [목적 toString() 만들기] [난이도 하].
		MyPoint a = new MyPoint(3,20);
		System.out.println(a);
		System.out.println(a.toString());
//		출력결과가 아래와 같이 나오도록 작성하기
//		MyPoint(3,20)
	}

}






