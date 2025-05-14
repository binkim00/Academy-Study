package selenium2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://search.naver.com/search.naver?query=박스오피스");

            // ✅ 패널 로딩 기다리기
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement pannel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("._panel")));

            // ✅ 영화 리스트 가져오기
            List<WebElement> listLi = pannel.findElements(By.cssSelector("li"));

            for (WebElement li : listLi) {
                try {
                    String name = li.findElement(By.cssSelector(".name")).getText();
                    String sub_text = li.findElement(By.cssSelector(".sub_text")).getText();

                    System.out.println("🎬 " + name + "\n 👥 " + sub_text);
                    System.out.println("------------------------");
                } catch (NoSuchElementException e) {
                    // 영화 항목이 비어있을 수 있음 → 생략
                }
            }

        } finally {
            driver.quit();
        }
    }
}
