package chapter21;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ex15_HashMapKeySet
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> map = new HashMap<>();
        // key: 이름(String), value: 나이(Integer)

        // put() 메서드를 이용하여 key-value 데이터 추가
        map.put("홍길동", 20);
        map.put("전우치", 25);
        map.put("손오공", 27);

        // map.keySet()	: Map에서 key만 꺼내서 Set으로 반환
        // keySet() : Map에 들어있는 모든 key를 Set 자료구조로 출력하는 메서드
        // → Map은 전체 반복이 어렵기 때문에, keySet()을 이용해 key만 따로 꺼내서 처리 가능
        Set<String> ks = map.keySet();

        // KeySet은 Set자료구조로 만들었기 때문에 인덱스는 사용할 수 없음
        // → 리스트가 아니기 때문에 get(0) 같은 접근은 안 되고, 반복문으로만 순회 가능
        // for-each	Set을 순회할 수 있는 일반적인 방법
        for(String s : ks)
            System.out.print(s + '\t'); // key(이름) 출력
        System.out.println();

        for(String s : ks)
            System.out.print(map.get(s).toString() + '\t'); // key를 통해 value(나이) 출력
        System.out.println();
        // map.get(key) :	key로부터 value(값)를 꺼내는 방법

        // Iterator를 이용한 순회
        // Set은 인덱스가 없기 때문에, 순서대로 처리하려면 Iterator를 사용함
        for(Iterator<String> itr = ks.iterator(); itr.hasNext(); )
            System.out.print(map.get(itr.next()).toString() + '\t'); // key를 통해 value 꺼냄
        System.out.println();
    }
}
