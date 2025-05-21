package practice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.net.URL;
import java.util.List;

public class practiceSelenium {
    public static void main(String[] args) throws Exception {
        // ✅ 1. 크롬 드라이버 자동 설치 및 설정
        WebDriverManager.chromedriver().setup();

        // ✅ 2. Chrome 브라우저 실행
        WebDriver driver = new ChromeDriver();

        // ✅ 3. Bing 이미지 검색 페이지 접속
        driver.get("https://www.bing.com/images/search?q=강아지");

        // ✅ 4. 이미지 로딩 시간 대기
        Thread.sleep(2000);

        // ✅ 5. 이미지 태그 추출
        List<WebElement> images = driver.findElements(By.cssSelector("img.mimg"));

        // ✅ 6. 저장할 폴더가 없으면 생성
        File folder = new File("img");
        if (!folder.exists()) {
            folder.mkdir();  // "img" 폴더 생성
        }

        // ✅ 7. 상위 10개 이미지 다운로드 및 저장
        int count = 0;
        for (WebElement img : images) {
            if (count >= 10) break;

            String src = img.getAttribute("src");
            if ((src == null || src.isEmpty()) && img.getAttribute("data-src") != null) {
                src = img.getAttribute("data-src");
            }

            if (src != null && src.startsWith("http")) {
                System.out.println((count + 1) + "번 이미지 저장 중: " + src);

                // ✅ 이미지 저장
                try {
                    URL imageUrl = new URL(src);
                    InputStream in = imageUrl.openStream();
                    OutputStream out = new FileOutputStream("img/dog" + (count + 1) + ".jpg");

                    byte[] buffer = new byte[2048];
                    int length;
                    while ((length = in.read(buffer)) != -1) {
                        out.write(buffer, 0, length);
                    }

                    in.close();
                    out.close();

                    System.out.println("→ 저장 완료: dog" + (count + 1) + ".jpg\n");
                    count++;

                } catch (IOException e) {
                    System.out.println("❌ 저장 실패: " + src);
                }
            }
        }

        // ✅ 8. 브라우저 종료
        driver.quit();
    }
}
