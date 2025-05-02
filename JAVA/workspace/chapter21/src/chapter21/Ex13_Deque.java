package chapter21;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Ex13_Deque
{
    public static void main(String[] args)
    {
    	// Stack 클래스는 옛날방식으로 현재는 권장하지 않음
    	// Deque 보다 처리가 느림, Vector 기반의 자료구조라서 스레드 실행시 안전
        Stack<String> stack = new Stack<>();
    	
        // 위의 Stack대신 사용하도록 권장하는 클래스
        // Stack보다 빠르다, List기반의 자료구조를 사용하고 스레드 실행시 안전하지 않음
        // queue의 기능과 stack의 기능을 모두 사용할 수 있는 자료구조
        Deque<String> deq = new ArrayDeque<>(); 
//        Deque<String> deq = new LinkedList<>(); 

        // 앞으로 넣고    
        // offerFirst : 첫 번째에 데이터를 추가
        deq.offerFirst("A");
        deq.offerFirst("B");
        deq.offerFirst("C");

        // 앞에서 꺼내기
        // pollFirst : 첫 번째 데이터를 출력한 후 삭제
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());  

        System.out.println("-------------------------------");
      
        // 뒤로 넣고     
        // offerLast : 제일 뒤에 데이터를 추가
        deq.offerLast("A");
        deq.offerLast("B");
        deq.offerLast("C");

        // 뒤에서 꺼내기
        // pollLast : 제일 뒤 데이터를 출력한 후 삭제
        System.out.println(deq.pollLast());
        System.out.println(deq.pollLast());
        System.out.println(deq.pollLast());  

        System.out.println("-------------------------------");
  
        // Queue를 Deque로 구현
        // 뒤로 넣고       
        deq.offerLast("A");
        deq.offerLast("B");
        deq.offerLast("C");

        // 앞에서 꺼내기
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());  
        
        System.out.println("Stack========================");
		// Push : 마지막에 추가
        deq.push("A");
		deq.push("B");
		deq.push("C");
		// pop : 마지막 데이터를 출력한 후 삭제
		System.out.println(deq.pop());
		System.out.println(deq.pop());
		System.out.println(deq.pop());
		
		
		System.out.println("Queue========================");
		// offer : 마지막에 데이터를 추가
		deq.offer("A");
		deq.offer("B");
		deq.offer("C");
		// poll : 첫 번째 데이터를 출력한 후 삭제
		System.out.println(deq.poll());
		System.out.println(deq.poll());
		System.out.println(deq.poll());
    }
}
