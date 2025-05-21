package practice;

// Selenium에서 제공하는 클래스들 임포트
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

// WebDriverManager는 크롬 드라이버를 자동으로 설치 및 관리해줌
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class practiceSelenium1 {
    public static void main(String[] args) throws InterruptedException {
        // ✅ 1. 크롬 드라이버 자동 설치 및 설정
        // 사용 중인 Chrome 브라우저 버전에 맞는 드라이버를 자동으로 다운로드하고 등록해줌
        WebDriverManager.chromedriver().setup();

        // ✅ 2. Chrome 브라우저 실행
        // 크롬 브라우저를 실제로 켜서 사람이 브라우저를 여는 것처럼 자동화할 수 있게 만듦
        WebDriver driver = new ChromeDriver();

        // ✅ 3. Bing 이미지 검색 페이지 접속
        // 검색어: 강아지 → 이미지 검색 결과 페이지로 이동
        driver.get("https://www.bing.com/images/search?q=강아지");

        // ✅ 4. 이미지 로딩 시간 대기 (2초)
        // 브라우저에 이미지들이 모두 나타날 때까지 기다려야 하기 때문에 sleep() 사용
        // 너무 빨리 실행하면 아직 이미지가 로딩되지 않아서 못 가져올 수도 있음
        Thread.sleep(2000);

        // ✅ 5. 이미지 태그 추출
        // 이미지 검색 결과에 있는 이미지들을 HTML 태그 중 class="mimg"인 <img> 요소로 선택
        // 결과는 WebElement 리스트로 저장됨
        List<WebElement> images = driver.findElements(By.cssSelector("img.mimg"));

        // ✅ 6. 상위 10개 이미지의 URL 출력
        int count = 0;  // 가져온 이미지 수 카운트
        for (WebElement img : images) {
            if (count >= 10) break;  // 10개까지만 출력하고 반복 종료

            // 이미지 주소는 보통 src 속성에 들어있음
            String src = img.getAttribute("src");

            // 일부 이미지들은 data-src 속성에 있을 수도 있으니, 거기서도 한 번 더 시도
            if ((src == null || src.isEmpty()) && img.getAttribute("data-src") != null) {
                src = img.getAttribute("data-src");
            }

            // http로 시작하는 유효한 이미지 주소만 출력
            if (src != null && src.startsWith("http")) {
                System.out.println((count + 1) + "번 이미지 URL: " + src);
                count++;  // 출력한 개수 1 증가
            }
        }

        // ✅ 7. 브라우저 종료
        // 작업이 끝나면 크롬 브라우저를 닫아줘야 리소스 낭비가 없음
        driver.quit();
    }
}
