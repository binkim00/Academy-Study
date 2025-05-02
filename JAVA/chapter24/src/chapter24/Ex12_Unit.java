package chapter24;

import java.io.Serializable;
// Serializable : 인스턴스를 파일에 저장하거나 네트워크로 전송하는 경우 설정하는 인터페이스
// serialVersionUID : 인스턴스에 설정되는 고유한 식별자

public class Ex12_Unit implements Serializable{
//	Serializable을 상속하면 serialVersionUID는 자동완성으로 작성하기
	private static final long serialVersionUID = 1L;
	private String name;
	public Ex12_Unit(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}








