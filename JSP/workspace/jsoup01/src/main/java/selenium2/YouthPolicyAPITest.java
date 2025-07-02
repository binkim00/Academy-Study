package selenium2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class YouthPolicyAPITest {

    public static void main(String[] args) {
        try {
            String apiKey = "13464484-9c91-4cf0-bb2a-812087e0c6df";  // ← 인증키는 직접 입력하세요

            String apiUrl = "https://www.youthcenter.go.kr/go/ythip/getPlcy"
                    + "?apiKeyNm=" + apiKey
                    + "&pageNum=1"
                    + "&pageSize=10"
                    + "&rtnType=json";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            System.out.println("응답 코드: " + responseCode);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            responseCode == 200 ? conn.getInputStream() : conn.getErrorStream(), "UTF-8"));

            String inputLine;
            StringBuilder responseBuilder = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            br.close();

            String response = responseBuilder.toString();
            System.out.println("=== 응답 JSON ===");
            System.out.println(response);

            JSONObject obj = new JSONObject(response);

            int resultCode = obj.getInt("resultCode");
            String resultMsg = obj.getString("resultMsg");
            JSONArray dataArray = obj.getJSONArray("data");

            System.out.println("결과 코드: " + resultCode);
            System.out.println("결과 메시지: " + resultMsg);
            System.out.println("정책 개수: " + dataArray.length());

            if (dataArray.length() > 0) {
                JSONObject first = dataArray.getJSONObject(0);
                System.out.println("▶ 첫 번째 정책 제목: " + first.getString("polyBizSjnm"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
