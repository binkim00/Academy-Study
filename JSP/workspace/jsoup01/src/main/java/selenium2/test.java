package selenium2;

import java.io.*;
import java.net.*;
import org.json.*;

public class test {

	public static void main(String[] args) {
        try {
            URL url = new URL("https://www.youthcenter.go.kr/opi/empPolicyList.do");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Referer", "https://www.youthcenter.go.kr/");
            conn.setRequestProperty("Origin", "https://www.youthcenter.go.kr");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            // 요청 JSON 구성
            String jsonInput = """
            {
                "pageIndex": 1,
                "pageUnit": 5,
                "srchRegion": "서울특별시"
            }
            """;

            // JSON 바디 전송
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 응답 받기
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8")
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            // 응답 출력
            System.out.println("=== 원본 응답 ===");
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
