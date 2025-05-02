import com.study.Banana;

public class Ex02_PackageUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Apple apple = new Apple();
		apple.showName();
		
//		패키지명을 클래스 앞에 직접 적는 방식
//		com.study.Banana banana  = new com.study.Banana();
//		class 선언부 위에 import를 사용하여 패키지명을 적는 방식
		Banana banana  = new Banana();
		banana.showName();
	}

}








