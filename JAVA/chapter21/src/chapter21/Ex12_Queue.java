package chapter21;

import java.util.LinkedList;
import java.util.Queue;

public class Ex12_Queue
{
    public static void main(String[] args)
    {
        // Queue 인터페이스는 FIFO 구조 (First In First Out, 먼저 넣은 게 먼저 나감)
        // LinkedList는 Queue 인터페이스를 구현한 대표적인 클래스
        Queue<String> que = new LinkedList<>(); 

        // 데이터 저장    
        // offer : 데이터 추가 메서드
        que.offer("A");  // 큐에 "A" 추가
        que.offer("B");  // 큐에 "B" 추가
        que.offer("C");  // 큐에 "C" 추가
        // size : 저장된 데이터 개수 출력
        System.out.println(que.size()); // 현재 큐의 크기 출력 (3)

        // 무엇이 다음에 나올지 확인
        // que.peek : 데이터를 출력
        System.out.println("next: " + que.peek()); // 큐의 맨 앞(A)을 꺼내지 않고 확인

        // 첫 번째 객체 꺼내기
        // que.poll : 데이터를 출력한 후 삭제
        System.out.println(que.poll()); // 큐에서 "A" 꺼냄 (제거됨)
        System.out.println(que.size()); // 큐의 크기 출력 (2)

        // 무엇이 다음에 나올지 확인
        System.out.println("next: " + que.peek()); // 다음은 "B"

        // 두 번째 객체 꺼내기
        System.out.println(que.poll()); // 큐에서 "B" 꺼냄
        System.out.println(que.size()); // 큐의 크기 출력 (1)

        // 무엇이 다음에 나올지 확인
        System.out.println("next: " + que.peek()); // 다음은 "C"

        // 마지막 객체 꺼내기
        System.out.println(que.poll()); // 큐에서 "C" 꺼냄
        System.out.println(que.size()); // 큐가 비어서 0 출력
    }
}
