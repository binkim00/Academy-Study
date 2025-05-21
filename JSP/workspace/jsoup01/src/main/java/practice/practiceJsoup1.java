package practice;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class practiceJsoup1 {
    public static void main(String[] args) throws IOException {

        // ✅ 1. 크롤링할 페이지 URL 설정
        String url = "https://ko.wikipedia.org/wiki/자카르타_서버_페이지";

        // ✅ 2. Jsoup으로 해당 페이지를 HTML 형태로 가져옴
        Document doc = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0") // 크롤링 차단 우회를 위한 유저 에이전트 설정
                            .timeout(5000)            // 응답 대기 시간 5초 설정
                            .get();                   // 실제 페이지 요청

        // ✅ 3. 위키백과 내의 표 중 class="wikitable"인 테이블들 중에서 반복 탐색
        for (Element table : doc.select("table.wikitable")) {

            // 테이블 캡션(caption)이 존재하는 경우에만 처리
            Element caption = table.selectFirst("caption");

            // 원하는 표인지 확인 (캡션에 특정 키워드 포함되어 있는지 확인)
            if (caption != null && caption.text().contains("자바서버 페이지 API 역사")) {
                System.out.println(caption.text() + "\n");

                // ✅ 4. 표의 모든 행(tr)을 가져오기
                Elements rows = table.select("tr");

                // ✅ 5. 첫 번째 행(th들)을 헤더로 저장
                Elements headerCells = rows.get(0).select("th");
                int colCount = headerCells.size();
                String[] headers = new String[colCount];

                // 헤더 텍스트 추출
                for (int i = 0; i < colCount; i++) {
                    headers[i] = headerCells.get(i).text();
                }

                // ✅ 6. 표의 헤더 출력
                for (String h : headers) {
                    System.out.printf("%-15s|", h);  // 15글자 너비 정렬
                }
                System.out.println();
                System.out.println("-".repeat(colCount * 16)); // 구분선 출력

                // ✅ 7. 표의 데이터 행들 출력
                for (int i = 1; i < rows.size(); i++) { // 첫 번째 행은 헤더이므로 1부터 시작
                    Elements cols = rows.get(i).select("td"); // 현재 행의 <td> 셀들을 모두 선택

                    for (int j = 0; j < colCount; j++) { // 헤더의 열 개수만큼 반복
                        // 셀 개수가 부족한 경우 빈 문자열로 출력
                        String cellText = j < cols.size() ? cols.get(j).text() : "";
                        
                        // 셀 내용을 15자리 너비로 왼쪽 정렬하여 출력하고, | 로 구분
                        System.out.printf("%-15s|", cellText);
                    }

                    System.out.println(); // 각 행마다 줄바꿈 처리
                }

                break; // 해당 테이블만 출력하고 종료
            }
        }

        // 구분선
        System.out.println("\n\n============================================");
        System.out.println("동작구조 문단 추출");
        System.out.println("============================================\n");

        // ✅ 8. 이미지 태그 중에서 "JSP_Model_2.svg"가 포함된 이미지를 탐색
        Element img = doc.selectFirst("img[src*='JSP_Model_2.svg']");
        String imgUrl = (img != null) ? "https:" + img.attr("src") : "없음";

        // ✅ 9. <p> 태그 중에서 "클라이언트" + "JSP"라는 키워드를 포함한 첫 문단을 찾음
        Element paragraph = doc.select("p").stream()
                .filter(p -> p.text().contains("클라이언트") && p.text().contains("JSP"))
                .findFirst()
                .orElse(null);
        String paragraphText = (paragraph != null) ? paragraph.text() : "없음";

        // ✅ 10. 결과 출력
        System.out.println("이미지 URL:\n" + imgUrl);
        System.out.println("\n설명 문단:\n" + paragraphText);
    }
}
