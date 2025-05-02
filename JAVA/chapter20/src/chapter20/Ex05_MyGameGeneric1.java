package chapter20;
class Tank5{
	@Override
	public String toString() {
		return "This is a Tank5";
	}
}
class Npc5{
	@Override
	public String toString() {
		return "This is a Npc5";
	}
}
//제네릭 설정하기
// 1. 클래스이름 뒤에 <T> 작성하기, T대신 다른 대문자도 가능
// 보편적으로 사용하는 문자는 E,K,N,T,V
// 2. 어떤 자료형이 들어올지 모르는 부분에 T를 넣어 코드 작성
class Camp5<T>{
//	unit의 자료형(타입)을 T로 설정
	private T unit;
//	unit의 데이터 설정시 필요한 매개변수의 타입을 T로 설정
	public void set(T unit) {
		this.unit = unit;
	}
//	unit변수를 반환할때 사용할 반환타입으로 T를 설정
	public T get() {
		return unit;
	}
}
public class Ex05_MyGameGeneric1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camp5<Npc5> human = new Camp5<Npc5>();
		Camp5<Tank5> machine = new Camp5<>();
		Camp5<String> str = new Camp5<>();
//		제네릭에는 기초자료형은 사용할 수 없음 => int,double,long 등은 사용 불가
//		Camp5<int> i = new Camp5<>(); int라서 에러 발생
//		래퍼 클래스는 사용할 수 있음 => Integer, Double, Long은 사용 가능
		Camp5<Integer> i = new Camp5<>(); // Integer는 사용 가능
		
		human.set(new Npc5());
//		human.set("난 공룡"); Npc5가 아닌 객체는 저장할 수 없도록 설정
		machine.set(new Tank5());
//		machine.set("난 우주인"); Tank5가 아닌 객체는 저장할 수 없도록 설정
		
		System.out.println(human.get());
		System.out.println(machine.get());
	}

}











