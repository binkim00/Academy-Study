package selenium2;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobalioListScraper {
    public static void main(String[] args) {
        String baseUrl = "https://job.alio.go.kr/recruit.do?idx=287728&s_date=2025.05.02&e_date=2025.07.02&work_type=R1060&work_type=R1070&org_type=&org_name=&search_type=&keyword=&order=REG_DATE&sort=DESC&pageSet=10";
        String outputFile = "alio_list.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, false))) {
            // CSV 헤더 작성
            writer.println("seq,title,org_name,location,job_type,start_date,end_date,dday,status,detail_url");

            int page = 1;
            int maxPage = 30;

            while (page <= maxPage) {
                String url = baseUrl + "&pageNo=" + page;
                System.out.println("🔍 페이지 " + page + " 처리 중...");

                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0")
                        .get();

                Elements rows = doc.select("tbody > tr");

                if (rows.isEmpty()) {
                    System.out.println("📄 데이터 없음. 종료.");
                    break;
                }

                for (Element row : rows) {
                    // idx가 없으면 공고가 아님 → 건너뜀
                    String idx = row.select("input[name=idxs]").attr("value").trim();
                    if (idx.isEmpty()) {
                        continue;
                    }

                    String seq = row.select("td:nth-of-type(2)").text().trim();
                    String title = row.select("td:nth-of-type(3) a").text().trim();
                    String orgName = row.select("td:nth-of-type(4)").text().trim();
                    String location = row.select("td:nth-of-type(5)").text().trim();
                    String jobType = row.select("td:nth-of-type(6)").text().trim();
                    String startDate = row.select("td:nth-of-type(7)").text().trim();

                    String dateRangeHtml = row.select("td:nth-of-type(8)").html();
                    String[] parts = dateRangeHtml.split("<br>");
                    String endDate = parts.length > 0 ? Jsoup.parse(parts[0]).text().trim() : "";
                    String dday = parts.length > 1 ? Jsoup.parse(parts[1]).text().trim() : "";

                    String status = row.select("td:nth-of-type(9)").text().trim();
                    String detailUrl = "https://job.alio.go.kr/recruit.do?pageNo=1&idx=" + idx;

                    writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                            seq, title, orgName, location, jobType,
                            startDate, endDate, dday, status, detailUrl);
                }

                page++;
                Thread.sleep(500); // 서버 부담 방지
            }

            System.out.println("✅ 전체 공고 크롤링 완료 (최대 30페이지): " + outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
