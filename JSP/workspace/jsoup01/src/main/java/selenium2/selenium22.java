package selenium2;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// ✅ Movie 클래스
class Movie {
    private String name;
    private String sub_text;
    private String imgAddr;

    public Movie(String name, String sub_text, String imgAddr) {
        this.name = name;
        this.sub_text = sub_text;
        this.imgAddr = imgAddr;
    }

    public String getName() { return name; }
    public String getSub_text() { return sub_text; }
    public String getImgAddr() { return imgAddr; }

    // ✅ 이미지 저장 메서드
    public void save() throws IOException {
        String fileName = name.replaceAll("[\\\\/:*?\"<>|]", "_") + ".jpg";  // 파일명 안전하게
        URL url = new URL(imgAddr);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("img/" + fileName);  // img 폴더 안에 저장

        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
        System.out.println("💾 이미지 저장 완료: " + fileName);
    }

    @Override
    public String toString() {
        return "🎬 " + name + "\n👥 " + sub_text + "\n🖼️ 포스터: " + imgAddr + "\n-----------------------------";
    }
}

public class selenium22 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://search.naver.com/search.naver?query=박스오피스");

        // ✅ 타이틀 확인
        System.out.println(driver.getTitle());

        // ✅ 로딩 대기
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ✅ 저장 폴더 생성 (없으면 새로 만듦)
        new File("img").mkdirs();

        // ✅ 영화 리스트 수집
        List<Movie> movieList = new ArrayList<>();

        try {
            WebElement panel = driver.findElement(By.cssSelector("._panel"));
            List<WebElement> listLi = panel.findElements(By.cssSelector("li"));

            for (WebElement li : listLi) {
                String name = li.findElement(By.cssSelector(".name")).getText();
                String sub_text = li.findElement(By.cssSelector(".sub_text")).getText();
                String imgAddr = li.findElement(By.tagName("img")).getAttribute("src");

                Movie mv = new Movie(name, sub_text, imgAddr);
                movieList.add(mv);
            }

        } catch (Exception e) {
            System.out.println("❗ 박스오피스 패널을 찾을 수 없습니다.");
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        // ✅ 영화 정보 출력 + 이미지 저장
        for (Movie mv : movieList) {
            System.out.println(mv);

            try {
                mv.save();
            } catch (IOException e) {
                System.out.println("❗ 이미지 저장 실패: " + mv.getName());
            }
        }
    }
}
