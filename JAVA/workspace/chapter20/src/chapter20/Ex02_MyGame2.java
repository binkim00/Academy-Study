package chapter20;

class Tank2{
	@Override
	public String toString() {
		return "This is a Tank2";
	}
}
class Npc2{
	@Override
	public String toString() {
		return "This is a Npc2";
	}
}
class Camp2{
	private Object unit;
	public void set(Object unit) {
		this.unit = unit;
	}
	public Object get() {
		return unit;
	}
}

public class Ex02_MyGame2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camp2 human = new Camp2();
		Camp2 machine = new Camp2();
		
//		human.set(new Npc1());
//		machine.set(new Tank1());
		human.set("난 공룡");
		machine.set("난 우주인");
		
//		Npc2 hUnit = (Npc2)human.get();
//		Tank2 mUnit = (Tank2)machine.get();
//		System.out.println(hUnit);
//		System.out.println(mUnit);
		
		System.out.println(human.get());;
		System.out.println(machine.get());
		
		
	}

}







