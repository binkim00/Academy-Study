package jsoup01;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test02 {

	public static void main(String[] args) {

		// ✅ 크롤링할 대상 URL (SBS 사회 뉴스 섹션 페이지)
		String url = "https://news.sbs.co.kr/news/newsSection.do?sectionType=02&plink=GNB&cooper=SBSNEWS";
		Document doc = null;  // HTML 문서를 저장할 변수
		
		try {
			// ✅ Jsoup으로 해당 URL에 접속해서 HTML 문서 가져오기
			doc = Jsoup.connect(url).get();  // 실제로는 HTTP GET 요청을 보냄
		} catch (IOException e) {
			e.printStackTrace();  // 페이지 접근 실패 시 에러 출력
		}

		// ✅ HTML 문서에서 뉴스 리스트 항목 선택
		// 선택자 의미: 클래스 이름이 'w_news_list'인 요소의 자식 ul > li 요소들
		// 실제 뉴스 기사들이 li 안에 담겨 있을 것으로 기대됨
		Elements elements = doc.select(".w_news_list > ul > li");

		// ✅ 뉴스 항목마다 추출할 텍스트 초기화
		String sub = "";
		String read = "";
		String date = "";

		// ✅ 각 뉴스 li 요소를 하나씩 반복하며 내용 추출
		for (Element element : elements) {
			// 클래스 이름이 'sub', 'read', 'date'인 하위 요소에서 텍스트 추출
			sub = element.getElementsByClass("sub").text();    // 제목
			read = element.getElementsByClass("read").text();  // 조회수
			date = element.getElementsByClass("date").text();  // 날짜

			// 추출한 정보 출력
			System.out.println(sub + "\n" + read + "\n" + date);
		}
	}
}
