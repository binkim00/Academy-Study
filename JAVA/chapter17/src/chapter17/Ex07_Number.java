package chapter17;

public class Ex07_Number {

	public static void main(String[] args) {
//		Integer.valueOf : 숫자를 Integer 자료형으로 변경
		Integer num1 = Integer.valueOf(20);
		Integer num3 = 20; // 요즘은 이렇게도 가능
//		intValue : 기초자료형 int로 변경
		System.out.println(num1.intValue());
//		doubleValue : 기초자료형 double로 변경
		System.out.println(num1.doubleValue());
		
		Double num2 = Double.valueOf(3.14);
		System.out.println(num2.intValue());
		System.out.println(num2.doubleValue());

	}

}
