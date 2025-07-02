package selenium2;

import java.io.*;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class AlioDetailTest {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<Map<String, String>> resultList = new ArrayList<>();

        String listUrl = "https://job.alio.go.kr/recruit.do?pageNo=1&s_date=2025.05.02&e_date=2025.07.02&work_type=R1060&work_type=R1070&order=REG_DATE&sort=DESC";
        driver.get(listUrl);

        // ✅ 변경된 셀렉터 적용
        List<WebElement> links = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("td.left a")));

        for (WebElement link : links) {
            try {
                String onclick = link.getAttribute("href"); // javascript:viewsub('287858')
                String idx = onclick.replaceAll("\\D+", "");

                // 상세페이지 이동
                String detailUrl = "https://job.alio.go.kr/recruitview.do?idx=" + idx +
                        "&s_date=2025.05.02&e_date=2025.07.02&work_type=R1060&work_type=R1070&order=REG_DATE&sort=DESC";
                driver.get(detailUrl);

                Map<String, String> data = new LinkedHashMap<>();
                data.put("기관명", getText(driver, "div.topInfo > h2"));
                data.put("공고제목", getText(driver, "div.topInfo .titleH2"));
                data.put("공고URL", getAttr(driver, "div.topInfo .infoLink a", "href"));

                // 상세 테이블
                List<WebElement> rows = driver.findElements(By.cssSelector("div.detailTxt table tr"));
                for (WebElement tr : rows) {
                    List<WebElement> ths = tr.findElements(By.tagName("th"));
                    List<WebElement> tds = tr.findElements(By.tagName("td"));
                    for (int i = 0; i < ths.size(); i++) {
                        data.put(ths.get(i).getText(), tds.get(i).getText());
                    }
                }

                data.put("응시자격", getText(driver, "#tab-1 h4:nth-of-type(1) + p"));
                data.put("결격사유", getText(driver, "#tab-1 h4:nth-of-type(2) + p"));
                data.put("우대내용", getText(driver, "#tab-1 h4:nth-of-type(3) + p"));
                data.put("전형절차", getText(driver, "#tab-1 h4:nth-of-type(4) + p"));

                resultList.add(data);
                System.out.println("수집 완료: " + data.get("공고제목"));
                Thread.sleep(300);

            } catch (Exception e) {
                System.out.println("에러: " + e.getMessage());
            }
        }

        driver.quit();
        saveToCSV(resultList, "alio_detail_test.csv");
    }

    private static String getText(WebDriver driver, String selector) {
        try {
            return driver.findElement(By.cssSelector(selector)).getText().replace(",", " ").replace("\n", " ");
        } catch (Exception e) {
            return "";
        }
    }

    private static String getAttr(WebDriver driver, String selector, String attr) {
        try {
            return driver.findElement(By.cssSelector(selector)).getAttribute(attr);
        } catch (Exception e) {
            return "";
        }
    }

    private static void saveToCSV(List<Map<String, String>> dataList, String filename) throws IOException {
        if (dataList.isEmpty()) return;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
        Map<String, String> first = dataList.get(0);
        List<String> keys = new ArrayList<>(first.keySet());
        writer.write(String.join(",", keys));
        writer.newLine();

        for (Map<String, String> map : dataList) {
            List<String> row = new ArrayList<>();
            for (String key : keys) {
                String value = map.getOrDefault(key, "").replace(",", " ").replace("\n", " ");
                row.add("\"" + value + "\"");
            }
            writer.write(String.join(",", row));
            writer.newLine();
        }

        writer.close();
        System.out.println("CSV 저장 완료: " + filename);
    }
}
