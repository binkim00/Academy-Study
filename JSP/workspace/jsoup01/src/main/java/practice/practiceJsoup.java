package practice;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class practiceJsoup {
    public static void main(String[] args) throws IOException {
        String url = "https://ko.wikipedia.org/wiki/자카르타_서버_페이지";
        Document doc = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0")
                            .timeout(5000)
                            .get();

        for (Element table : doc.select("table.wikitable")) {
            Element caption = table.selectFirst("caption");
            if (caption != null && caption.text().contains("자바서버 페이지 API 역사")) {

                System.out.println(caption.text() + "\n");

                Elements rows = table.select("tr");
                Elements headerCells = rows.get(0).select("th");
                int colCount = headerCells.size();
                String[] headers = new String[colCount];

                for (int i = 0; i < colCount; i++) {
                    headers[i] = headerCells.get(i).text();
                }

                for (String h : headers) {
                    System.out.printf("%-15s|", h);
                }
                System.out.println();
                System.out.println("-".repeat(colCount * 16));

                for (int i = 1; i < rows.size(); i++) {
                    Elements cols = rows.get(i).select("td");
                    for (int j = 0; j < colCount; j++) {
                        String cellText = j < cols.size() ? cols.get(j).text() : "";
                        System.out.printf("%-15s|", cellText);
                    }
                    System.out.println();
                }

                break;
            }
        }

        System.out.println("\n\n============================================");
        System.out.println("동작구조 문단 추출");
        System.out.println("============================================\n");

        Element img = doc.selectFirst("img[src*='JSP_Model_2.svg']");
        String imgUrl = (img != null) ? "https:" + img.attr("src") : "없음";

        Element paragraph = doc.select("p").stream()
                .filter(p -> p.text().contains("클라이언트") && p.text().contains("JSP"))
                .findFirst()
                .orElse(null);
        String paragraphText = (paragraph != null) ? paragraph.text() : "없음";

        System.out.println("이미지 URL:\n" + imgUrl);
        System.out.println("설명 문단:\n" + paragraphText);
    }
}
