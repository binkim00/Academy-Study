package org.zerock.testproject;

import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll // 테스트 실행 전 한번 실행되는 메서드
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach // 테스트가 실행되기 전 실행, 테스트가 여러개라면 매번 실행
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll // 모든 테스트가 끝난 후 한번 실행
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach // 각 테스트 종료시 매번 실행 메서드
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}