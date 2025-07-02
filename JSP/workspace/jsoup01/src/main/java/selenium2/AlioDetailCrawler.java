package selenium2;

import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class AlioDetailCrawler {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try (PrintWriter writer = new PrintWriter(new FileWriter("alio_details.csv", false))) {
            // CSV 헤더 출력
            writer.println(String.join(",",
                "기관명","공고제목","공고URL","표준직무(NCS)","학력정보",
                "근무분야","채용구분","고용형태","대체인력여부","근무지",
                "급여정보","채용인원","우대조건","채용기간","등록일",
                "응시자격","결격사유","우대내용","전형절차"));

            for (int page = 1; page <= 30; page++) {
                String listUrl = String.format(
                    "https://job.alio.go.kr/recruit.do?pageNo=%d&s_date=2025.05.02&e_date=2025.07.02&work_type=R1060&work_type=R1070&order=REG_DATE&sort=DESC",
                    page
                );
                driver.get(listUrl);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td.left a[id^=view_]")));

                List<WebElement> links = driver.findElements(By.cssSelector("td.left a[id^=view_]"));
                for (int i = 0; i < links.size(); i++) {
                    links = driver.findElements(By.cssSelector("td.left a[id^=view_]"));
                    WebElement link = links.get(i);
                    String href = link.getAttribute("href"); // javascript:viewsub('287858')
                    String idx = href.replaceAll("\\D+", "");

                    String detailUrl = "https://job.alio.go.kr/recruitview.do?idx=" + idx +
                                       "&s_date=2025.05.02&e_date=2025.07.02&work_type=R1060&work_type=R1070&order=REG_DATE&sort=DESC";
                    driver.get(detailUrl);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.detailTxt table")));

                    Map<String,String> data = new LinkedHashMap<>();
                    data.put("기관명", text(driver, "div.topInfo > h2"));
                    data.put("공고제목", text(driver, "div.topInfo .titleH2"));
                    data.put("공고URL", attr(driver, "div.topInfo .infoLink a", "href"));

                    List<WebElement> rows = driver.findElements(By.cssSelector("div.detailTxt table tr"));
                    for (WebElement tr : rows) {
                        List<WebElement> ths = tr.findElements(By.tagName("th"));
                        List<WebElement> tds = tr.findElements(By.tagName("td"));
                        for (int j = 0; j < ths.size(); j++) {
                            data.put(ths.get(j).getText(), tds.get(j).getText());
                        }
                    }

                    data.put("응시자격", text(driver, "#tab-1 h4:nth-of-type(1) + p"));
                    data.put("결격사유", text(driver, "#tab-1 h4:nth-of-type(2) + p"));
                    data.put("우대내용", text(driver, "#tab-1 h4:nth-of-type(3) + p"));
                    data.put("전형절차", text(driver, "#tab-1 h4:nth-of-type(4) + p"));

                    writer.println(csvLine(data));
                    writer.flush();

                    System.out.printf("페이지 %d - %s\n", page, data.get("공고제목"));

                    driver.navigate().back();
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td.left a[id^=view_]")));
                }
            }

            System.out.println("✅ 수집 완료: alio_details.csv");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static String text(WebDriver d, String sel) {
        try {
            return d.findElement(By.cssSelector(sel)).getText().replaceAll("[\\r\\n,]+"," ").trim();
        } catch (Exception e) {
            return "";
        }
    }

    private static String attr(WebDriver d, String sel, String attr) {
        try {
            return d.findElement(By.cssSelector(sel)).getAttribute(attr).trim();
        } catch (Exception e) {
            return "";
        }
    }

    private static String csvLine(Map<String,String> map) {
        return String.join(",", map.values()
            .stream().map(v -> "\""+v.replace("\"","'")+"\"")
            .toArray(String[]::new)
        );
    }
}
