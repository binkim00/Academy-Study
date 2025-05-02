package chapter17;

public class Ex10_BoxingUnboxing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		박싱 : 기초자료형을 래퍼클래스로 변경
		Integer iObj = Integer.valueOf(10);
		Double dObj = Double.valueOf(3.14);
//		언박싱 : 래퍼클래스를 기초자료형으로 변경
		int num1 = iObj.intValue();
		double num2 = dObj.doubleValue();
		
//		실제 사용방식은 같음, 출력되는 내용은 같음
		System.out.println(num1 + " : " + iObj);
		System.out.println(num2 + " : " + dObj);
		System.out.println();
		
//		iObj.intValue()으로 기초자료형으로 변경 
//			=> iObj+10 
//				=> Integer.valueOf()로 래퍼클래스로 변경
//					=> iObj 변수에 저장
		iObj = Integer.valueOf(iObj.intValue() + 10);
		dObj = Double.valueOf(dObj.doubleValue()+1.2);
		
		System.out.println(iObj);
		System.out.println(dObj);
	}

}











