package selenium2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class YouthPolicySelenium {
    public static void main(String[] args) {
        // 크롬드라이버 경로 설정
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");

        // 웹드라이버 실행
        WebDriver driver = new ChromeDriver();

        // 테스트용 페이지 접속 (청년정책 페이지 등)
        driver.get("https://www.youthcenter.go.kr/go/ythip/getPlcy");

        // 페이지 제목 출력해보기
        System.out.println("Title: " + driver.getTitle());

        // 종료
        driver.quit();
    }
}