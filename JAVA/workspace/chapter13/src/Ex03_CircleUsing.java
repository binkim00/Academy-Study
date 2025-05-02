import com.company.area.Circle;

public class Ex03_CircleUsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1 = new Circle(3.5);
		System.out.println("반지름 3.5의 원의 넓이 " + c1.getArea());
		
		com.company.circumference.Circle c2 = new com.company.circumference.Circle(3.5);
		System.out.println("반지름 3.5의 원 둘레 "+c2.getCircumference());
	}

}
