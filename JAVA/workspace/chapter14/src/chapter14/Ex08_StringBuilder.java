package chapter14;

public class Ex08_StringBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String의 단점
//		객체를 저장하는 클래스 자료형이고 String안의 내용을 변경할때마다 객체가 생성되어
//		메모리를 비효율적으로 사용하게됨 => 내용 변경시 연산속도가 느림
//		StringBuilder는 위의 문제점을 해결하여 연산속도가 빠름
		StringBuilder buf = new StringBuilder("동해물과");
		
//		append : 문자열을 더할때 사용하는 메서드
		buf.append("백두산이");
		System.out.println(buf);
		buf.append(12345);
		System.out.println(buf.toString());
//		delete(0, 4) : 0번위치에서 4번 위치까지 삭제하는 메서드
		buf.delete(0, 4);
		System.out.println(buf.toString());
//		buf.replace(시작위치, 종료위치, 변경할 문자열)
		buf.replace(4, 8, "ABC");
		System.out.println(buf.toString());
//		문자열을 거꾸로 설정하는 메서드
		buf.reverse();
		System.out.println(buf.toString());
		
//		1970년 1월1일부터 현재까지의 밀리세컨드 계산한 값
		long startTime = System.currentTimeMillis();
		String str = "";
		for(int i=0; i<100; i++) {
			str+="apple";//str변수에 apple을 천개 저장
		}
		long endTime = System.currentTimeMillis();
		System.out.println("String 연산시간:"+(endTime-startTime));
		
		startTime = System.currentTimeMillis();
		StringBuilder str2 = new StringBuilder("");
		for(int i=0; i<100; i++) {
			str2.append("apple");//str2변수에 apple을 천개 저장
		}
		endTime = System.currentTimeMillis();
		System.out.println("StringBuilder 연산시간:"+(endTime-startTime));
		
//		StringBuilder와 StringBuffer
//		두개의 클래스가 실행하는 내용은 같음
//		차이점은 스레드 사용시 
//		StringBuffer의 경우 내용이 정상적으로 출력= 스레드 동기화 기능으로 속도 늘림
//		StringBuilder의 경우 내용이 깨져서 출력= 스레드 동기화를 하지 않기 때문에 속도 빠름
	}

}











