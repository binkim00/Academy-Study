package jsoup01;

// ✅ Jsoup 라이브러리의 핵심 클래스 임포트
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class test01 {

	public static void main(String[] args) {

		// ✅ 직접 작성한 HTML 문자열을 strHtml 변수에 저장
		// 이 HTML은 간단한 구조로, <title>과 <h1> 태그를 포함함
		String strHtml = "<!DOCTYPE html>"
			+ "<html>"
	        + "<head>"
	        + "<title>JSoup Example</title>"  // 웹 문서의 제목
	        + "</head>"
	        + "<body>"
	        + "<table><tr><td><h1>HelloWorld</h1></tr>"  // 본문 내용에 <h1> 포함
	        + "</table>"
	        + "</body>"
	        + "</html>";
		
		// ✅ Jsoup.parse(): HTML 문자열을 파싱하여 Document 객체로 변환
		Document html = Jsoup.parse(strHtml);

		// ✅ .title(): <title> 태그의 내용을 추출 ("JSoup Example")
		String title = html.title();

		// ✅ .getElementsByTag("h1").text(): <h1> 태그의 텍스트만 추출 ("HelloWorld")
		String h1 = html.body().getElementsByTag("h1").text();

		// ✅ 추출한 데이터를 콘솔에 출력
		System.out.println(title);  // 결과: JSoup Example
		System.out.println(h1);     // 결과: HelloWorld
	}
}
