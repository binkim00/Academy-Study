// ✅ selenium2라는 패키지 이름 지정 (클래스 파일이 속한 논리적 그룹)
package selenium2;

// ✅ 필요한 자바 라이브러리 import
import java.io.*;               // 입출력 관련 클래스 (InputStream, OutputStream 등)
import java.net.URL;            // URL 클래스 사용 (이미지 다운로드용)
import java.util.ArrayList;     // 리스트 자료구조 (가변 배열)
import java.util.List;          // List 인터페이스

// ✅ Selenium에서 제공하는 클래스 import
import org.openqa.selenium.By;              // HTML 요소를 찾기 위한 선택자 클래스
import org.openqa.selenium.WebDriver;       // 브라우저를 제어하는 핵심 인터페이스
import org.openqa.selenium.WebElement;      // HTML 요소를 의미하는 클래스
import org.openqa.selenium.chrome.ChromeDriver; // Chrome 브라우저 드라이버 클래스

// ✅ 영화 정보를 저장할 Movie 클래스 정의
class Movie {
    // 🎬 영화 이름, 소개 문구, 이미지 주소를 저장할 변수들
    private String name;
    private String sub_text;
    private String imgAddr;

    // 🎬 생성자: Movie 객체를 만들 때 필요한 값을 전달받아 초기화
    public Movie(String name, String sub_text, String imgAddr) {
        this.name = name;
        this.sub_text = sub_text;
        this.imgAddr = imgAddr;
    }

    // 🎬 getter 메서드: 외부에서 필드 값 읽기 용도
    public String getName() { return name; }
    public String getSub_text() { return sub_text; }
    public String getImgAddr() { return imgAddr; }

    // ✅ 포스터 이미지를 다운로드하여 저장하는 메서드
    public void save() throws IOException {
        // 파일명에 사용할 수 없는 문자 제거하여 안전한 파일명 생성
        String fileName = name.replaceAll("[\\\\/:*?\"<>|]", "_") + ".jpg";

        // 이미지 주소로부터 파일 다운로드 준비
        URL url = new URL(imgAddr);             // 이미지 주소를 URL 객체로 생성
        InputStream is = url.openStream();      // 이미지 읽어올 입력 스트림 열기
        OutputStream os = new FileOutputStream("img/" + fileName); // img 폴더에 저장

        // 버퍼를 이용한 데이터 복사
        byte[] b = new byte[2048];  // 한번에 2KB씩 복사
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length); // 읽은 만큼만 쓰기
        }

        // 사용한 스트림 닫기 (자원 해제)
        is.close();
        os.close();

        // 다운로드 완료 메시지 출력
        System.out.println("💾 이미지 저장 완료: " + fileName);
    }

    // ✅ 객체 내용을 보기 좋게 출력할 때 사용할 문자열 정의
    @Override
    public String toString() {
        return "🎬 " + name + "\n👥 " + sub_text + "\n🖼️ 포스터: " + imgAddr + "\n-----------------------------";
    }
}

// ✅ 메인 클래스: 크롤링 실행
public class selenium22 {
    public static void main(String[] args) {
        // ✅ 크롬 브라우저 실행 (WebDriver 설정 필요)
        WebDriver driver = new ChromeDriver();

        // ✅ 네이버 박스오피스 검색 결과 페이지로 이동
        driver.get("https://search.naver.com/search.naver?query=박스오피스");

        // ✅ 현재 브라우저 탭의 제목 출력
        System.out.println(driver.getTitle());

        // ✅ 페이지가 완전히 로드되도록 3초 대기
        try {
            Thread.sleep(3000); // 3000ms = 3초
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ✅ 이미지 저장용 폴더 생성 (없으면 새로 생성됨)
        new File("img").mkdirs();

        // ✅ 영화 데이터를 저장할 리스트 생성
        List<Movie> movieList = new ArrayList<>();

        try {
            // ✅ 박스오피스 정보를 담고 있는 HTML 영역 찾기
            WebElement panel = driver.findElement(By.cssSelector("._panel"));

            // ✅ 패널 내부의 li 태그들을 모두 찾음 (각 영화 정보)
            List<WebElement> listLi = panel.findElements(By.cssSelector("li"));

            // ✅ 각 영화 정보를 반복해서 추출
            for (WebElement li : listLi) {
                // 영화 제목
                String name = li.findElement(By.cssSelector(".name")).getText();

                // 영화 부제목 또는 소개
                String sub_text = li.findElement(By.cssSelector(".sub_text")).getText();

                // 포스터 이미지 주소
                String imgAddr = li.findElement(By.tagName("img")).getAttribute("src");

                // 영화 객체 생성 후 리스트에 추가
                Movie mv = new Movie(name, sub_text, imgAddr);
                movieList.add(mv);
            }

        } catch (Exception e) {
            // ❗ 패널을 찾지 못하거나 오류 발생 시 예외 처리
            System.out.println("❗ 박스오피스 패널을 찾을 수 없습니다.");
            e.printStackTrace();
        } finally {
            // ✅ 크롬 브라우저 닫기
            driver.quit();
        }

        // ✅ 수집된 영화 정보 출력 + 이미지 저장
        for (Movie mv : movieList) {
            System.out.println(mv); // 영화 정보 출력

            try {
                mv.save();         // 포스터 이미지 저장
            } catch (IOException e) {
                System.out.println("❗ 이미지 저장 실패: " + mv.getName());
            }
        }
    }
}
