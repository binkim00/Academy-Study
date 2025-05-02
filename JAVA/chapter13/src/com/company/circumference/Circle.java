package com.company.circumference;

public class Circle {
	//멤버 변수
	double rad;
	final double PI;
	// 생성자
	public Circle(double r) {
		rad = r;
		PI = 3.14;
	}
	//원의 넓이를 구하는 메서드
	public double getCircumference() {
		return (rad*2)*PI;
	}
}








