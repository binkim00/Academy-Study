package chapter17;

public class Ex11_AutoBoxingUnboxing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		오토 박싱 : 자동으로 박싱이 실행됨
		Integer iObj = 10;
		Double dObj = 3.14;
//		오토 언박싱 : 자동으로 언박싱이 실행됨
		int num1 = iObj;
		double num2 = dObj;
		
		System.out.println(num1 + " : "+ iObj);
		System.out.println(num2 + " : "+ dObj);
		
		iObj = iObj+10;
		dObj = dObj+1.2;
		
		System.out.println(iObj);
		System.out.println(dObj);
	}

}













