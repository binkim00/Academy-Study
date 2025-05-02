package chapter20;

// 제네릭을 두개 설정 T1, T2
class Camp7<T1, T2>{
	private T1 param1;
	private T2 param2;
	
	public void set(T1 o1, T2 o2) {
		param1 = o1;
		param2 = o2;
	}
	@Override
	public String toString() {
		return param1 + "&" + param2;
	}
}

public class Ex07_MultiParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		     <T1    , T2>
		Camp7<String, Integer> camp = new Camp7<>();
		camp.set("Apple", 25);
		System.out.println(camp);
	}

}








