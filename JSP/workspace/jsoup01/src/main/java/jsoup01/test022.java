package jsoup01;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test022 {

    public static void main(String[] args) {

        // 크롤링할 페이지 URL
        String url = "https://news.sbs.co.kr/news/newsSection.do?sectionType=02&plink=GNB&cooper=SBSNEWS";
        Document doc = null;

        try {
            // ❌ 기존 코드: user-agent 없음, timeout 없음
//            doc = Jsoup.connect(url).get();
            
            // ✅ 수정 코드: user-agent 설정 + timeout 추가
            doc = Jsoup.connect(url)
                       .userAgent("Mozilla/5.0") // 브라우저처럼 속이기
                       .timeout(5000)            // 최대 5초 대기
                       .get();
        } catch (IOException e) {
            System.out.println("웹 페이지를 불러오는 데 실패했습니다."); // 예외 메시지 출력
            e.printStackTrace();
            return; // 프로그램 종료
        }

        // ❌ 기존 코드: 클래스 이름 사이에 공백 → CSS 선택자 오류
//        Elements elements = doc.select(".w_news_list type_issue2 > ul > li");

        // ✅ 수정 코드: 클래스 이름이 두 개인 경우는 .a.b 형식으로 연결
        Elements elements = doc.select(".w_news_list.type_issue2 ul li");

        // 변수 선언
        String sub = "";
        String read = "";
        String date = "";

        // 반복문으로 데이터 추출 및 출력
        for (Element element : elements) {

            // ❌ 기존 방식: getElementsByClass()는 첫 번째 항목만 가져올 수 있음
//            sub = element.getElementsByClass("sub").text();
//            read = element.getElementsByClass("read").text();
//            date = element.getElementsByClass("date").text();

            // ✅ 수정 방식: select()를 사용하면 좀 더 안정적
            sub = element.select(".sub").text();
            read = element.select(".read").text();
            date = element.select(".date").text();

            // 출력
            System.out.println("제목: " + sub);
            System.out.println("조회수: " + read);
            System.out.println("날짜: " + date);
            System.out.println("--------------------------------------");
        }
    }
}
