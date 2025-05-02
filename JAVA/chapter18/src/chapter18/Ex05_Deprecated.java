package chapter18;

interface Unit5{
	
//	JavaDoc 주석 : 자바 도큐먼트 주석
	/**
	 * 언젠가는 삭제될 메서드 입니다.
	 * 대신 run 메서드를 사용해 주세요.
	 * @param str
	 */
	@Deprecated
	public void move(String str);
	
	public void run(String str);
}
class Human5 implements Unit5{
	
	@SuppressWarnings("deprecation")
	@Override
	public void move(String str) {
		System.out.println(str);
	}
	@Override
	public void run(String str) {
		System.out.println(str);
	}
	
	/**
	 * 매개변수인 str을 Integer타입으로 반환하는 메서드 입니다.
	 * @param str
	 * @return str을 Integer로 변경하여 반환
	 * @author 임혜동
	 * @see 참고자료
	 * @throws inputMismatchException
	 * @exception inputMismatchException
	 */
	public int test(String str) {
		return Integer.parseInt(str);
	}
}


public class Ex05_Deprecated {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Unit5 unit = new Human5();
		unit.move("인간형 유닛이 이동합니다.");
		unit.run("인간형 유닛이 이동합니다.");
		Human5 h5 = new Human5();
		int num = h5.test("5");
	}

}








