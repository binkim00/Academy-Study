package chapter20;

class MyData{
//	             제네릭  반환타입  메서드이름    매개변수타입  매개변수이름
	public static <T>     T     showData (     T        data) {
		if(data instanceof String) {
			System.out.println("String");
		}else if(data instanceof Integer) {
			System.out.println("Integer");
		}else if(data instanceof Double) {
			System.out.println("Double");
		}
		return data;
	}
}

public class Ex09_GenericMethod {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyData.<String>showData("Hello World!");
		MyData.showData(1);
		MyData.showData(1.0);
	}

}









