package chapter23;

import java.util.Arrays;
import java.util.List;

public class Ex08_MethodReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Method Reference, 메서드 참조 : 람다식을 간편하게 출력하는 방식
//		매개변수를 생략하고 필요한 메서드를 바로 적는 방식 
//			-> 매개변수가 자동으로 메서드에 설정되어 실행
		List<Integer> numbers = Arrays.asList(-3, -1, 0, 2, 5, -10, 7);
		numbers.stream()
		.filter(n -> n>0)
//		.forEach(n->System.out.print(n));
		.forEach(System.out::print);
//		표현식                  의미
//		System.out::print      n->System.out.print(n)
//		Math::abs              n->Math.abs(n)
//		String::toUpperCase	   s->str.toUpperCase()
//		User::new              c->new User(c)
//		String[]:new           size->new String[size]
		
	}

}






