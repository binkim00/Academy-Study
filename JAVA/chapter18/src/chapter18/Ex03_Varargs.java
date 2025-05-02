package chapter18;

public class Ex03_Varargs {
//	                                  String 가변 인수 설정
	public static void helloEveryBody(String... vargs) {
		for(String s : vargs) {
			System.out.print(s + "\t");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		helloEveryBody(new String[]{"홍길동"});
		helloEveryBody("홍길동","전우치");
		helloEveryBody("홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공");
		String[] strArr= {"홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공","홍길동","전우치","손오공"};
		helloEveryBody(strArr);
	}

}





