package orgapi;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class TagoJson {
    // 🔑 서비스 키 (공공데이터포털에서 발급받은 인증키, 반드시 URL 인코딩된 상태여야 함)
    static final String SERVICE_KEY = "sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D";
    // 🌐 공통 API 기본 URL
    static final String BASE_URL = "http://apis.data.go.kr/1613000/TrainInfoService";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) { // 👉 전체 흐름 반복 루프
            try {
                // [1] 시/도 목록 출력
                Map<String, String> cityMap = getCityList();
                System.out.println("▶ 시/도 목록:");
                cityMap.forEach((code, name) -> System.out.println(name + " (" + code + ")"));

                // [2] 출발 시/도 코드 입력
                System.out.print("\n출발 시/도 코드 입력: ");
                String depCityCode = sc.nextLine();
                if (!cityMap.containsKey(depCityCode)) {
                    System.out.println("❌ 존재하지 않는 시/도 코드입니다.");
                    continue;
                }

                // [3] 해당 시/도의 출발역 목록 조회
                Map<String, String> depStationMap = getStationList(depCityCode);
                System.out.println("▶ 출발역 목록:");
                depStationMap.forEach((code, name) -> System.out.println(name + " (" + code + ")"));

                // [4] 출발역 이름 입력
                System.out.print("\n출발역 이름 입력: ");
                String depStationName = sc.nextLine();
                String depStationCode = getStationCode(depStationMap, depStationName); // ❌ 없는 이름이면 예외 발생

                // [5] 도착 시/도 코드 입력
                System.out.print("\n도착 시/도 코드 입력: ");
                String arrCityCode = sc.nextLine();
                if (!cityMap.containsKey(arrCityCode)) {
                    System.out.println("❌ 존재하지 않는 시/도 코드입니다.");
                    continue;
                }

                // [6] 해당 시/도의 도착역 목록 조회
                Map<String, String> arrStationMap = getStationList(arrCityCode);
                System.out.println("▶ 도착역 목록:");
                arrStationMap.forEach((code, name) -> System.out.println(name + " (" + code + ")"));

                // [7] 도착역 이름 입력
                System.out.print("\n도착역 이름 입력: ");
                String arrStationName = sc.nextLine();
                String arrStationCode = getStationCode(arrStationMap, arrStationName); // ❌ 없는 이름이면 예외 발생

                // [8] 출발 날짜 입력 (예: 20250601)
                System.out.print("\n출발 날짜 입력 (예: 20250601): ");
                String depDate = sc.nextLine();
                if (!depDate.matches("\\d{8}")) {
                    System.out.println("❌ 날짜 형식이 잘못되었습니다. 예: 20250601");
                    continue;
                }

                // [9] 열차 정보 조회 및 출력
                getTrainInfo(depStationCode, arrStationCode, depDate);
                break; // 모든 절차 성공 → 루프 종료

            } catch (Exception e) {
                System.out.println("\n⚠️ 오류: " + e.getMessage());
                System.out.println("⏪ 처음부터 다시 시도해주세요.\n");
            }
        }
    }


    // ✅ 도시 목록(JSON) 가져오기
    static Map<String, String> getCityList() throws Exception {
        String url = BASE_URL + "/getCtyCodeList?serviceKey=" + SERVICE_KEY + "&_type=json";
        JSONObject json = readJsonFromUrl(url); // JSON 전체 응답을 파싱 (XML과 달리 루트 태그 없이 바로 객체 시작)
        JSONObject response = json.getJSONObject("response"); // "response"라는 이름의 객체 안으로 접근
        JSONObject body = response.getJSONObject("body"); // "body" 객체 접근
        JSONObject items = body.getJSONObject("items"); // "items"는 실제 데이터가 담긴 영역 (객체 또는 배열)

        JSONArray itemArray;
        if (items.get("item") instanceof JSONArray) { // item이 배열이면 그대로 사용
            itemArray = items.getJSONArray("item");
        } else {
            itemArray = new JSONArray(); // 단일 객체면 새 배열로 감싸줌 (JSON은 개수가 1개일 때 배열이 아닌 객체로 응답되는 경우 많음)
            itemArray.put(items.getJSONObject("item"));
        }

        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject item = itemArray.getJSONObject(i); // 배열 내 각 원소에 접근
            map.put(String.valueOf(item.get("citycode")), item.getString("cityname")); // 숫자는 String.valueOf()로 문자열 변환
        }
        return map;
    }

    // ✅ 시/도에 따른 역 목록 조회 (JSON)
    static Map<String, String> getStationList(String cityCode) throws Exception {
        String url = BASE_URL + "/getCtyAcctoTrainSttnList?serviceKey=" + SERVICE_KEY +
                "&cityCode=" + cityCode + "&numOfRows=100&pageNo=1&_type=json";
        JSONObject json = readJsonFromUrl(url); // 전체 JSON 응답 파싱
        JSONObject items = json.getJSONObject("response")
                               .getJSONObject("body")
                               .getJSONObject("items"); // "items" 안에 실제 데이터 존재

        JSONArray itemArray;
        if (items.get("item") instanceof JSONArray) {
            itemArray = items.getJSONArray("item");
        } else {
            itemArray = new JSONArray();
            itemArray.put(items.getJSONObject("item"));
        }

        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject item = itemArray.getJSONObject(i);
            map.put(item.getString("nodeid"), item.getString("nodename"));
        }
        return map;
    }

    // ✅ 역 이름으로 코드 찾기
    static String getStationCode(Map<String, String> map, String name) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(name))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("❌ 역 이름이 일치하지 않습니다."));
    }

    // ✅ 열차 정보 조회 및 출력 (JSON)
    static void getTrainInfo(String depCode, String arrCode, String date) throws Exception {
        int page = 1;               // 현재 조회할 페이지 번호 (1부터 시작)
        int totalCount = 0;         // 전체 열차 개수
        boolean first = true;       // 첫 페이지 조회 여부 체크

        while (true) {
            // 🔗 JSON 형식으로 열차 정보 요청 URL 구성
            String url = BASE_URL + "/getStrtpntAlocFndTrainInfo?serviceKey=" + SERVICE_KEY +
                    "&depPlaceId=" + depCode + "&arrPlaceId=" + arrCode + "&depPlandTime=" + date +
                    "&numOfRows=50&pageNo=" + page + "&_type=json";

            // 📦 JSON 응답 파싱 (문자열 → JSONObject)
            JSONObject json = readJsonFromUrl(url);

            // 📂 JSON 구조 분석: response > body > items
            JSONObject body = json.getJSONObject("response").getJSONObject("body");

            // 📛 열차 정보가 없는 경우 (items 자체가 없는 경우)
            if (!body.has("items")) {
                if (first) System.out.println("❌ 조회된 열차가 없습니다.");
                break;
            }

            // 🔢 첫 번째 페이지일 때만 전체 개수 출력 준비
            if (first) {
                System.out.println("\n🚄 열차 정보:");
                totalCount = body.getInt("totalCount");  // 전체 열차 수
                first = false;
            }

            // 🔍 실제 열차 목록이 담긴 items 영역
            JSONObject items = body.getJSONObject("items");

            // 🧾 JSON에서는 결과가 2개 이상이면 JSONArray, 1개면 JSONObject로 올 수 있음
            //    XML에서는 항상 <item>태그 반복이지만 JSON에서는 구조가 다를 수 있으므로 아래 분기 필요
            JSONArray itemArray;
            if (items.get("item") instanceof JSONArray) {
                itemArray = items.getJSONArray("item"); // 여러 개일 경우
            } else {
                itemArray = new JSONArray();            // 하나일 경우 배열로 변환
                itemArray.put(items.getJSONObject("item"));
            }

            // 📋 각 열차 정보를 출력
            for (int i = 0; i < itemArray.length(); i++) {
                JSONObject item = itemArray.getJSONObject(i); // 각 열차 객체

                // 🔎 JSON은 타입을 구분함: 숫자는 정수, 문자열은 문자열
                //    그래서 trainno처럼 숫자로 오는 경우 String.valueOf로 감싸야 안전함
                String train = item.getString("traingradename");             // 열차 종류 (예: KTX)
                String no = String.valueOf(item.get("trainno"));             // 열차 번호 (정수 → 문자열 변환)
                String dep = String.valueOf(item.get("depplandtime"));       // 출발 시간 (YYYYMMDDHHMMSS)
                String arr = String.valueOf(item.get("arrplandtime"));       // 도착 시간 (YYYYMMDDHHMMSS)
                int charge = item.getInt("adultcharge");                     // 요금 (정수형 그대로)

                // 💬 결과 출력
                System.out.printf("[%s] %s → %s | 열차번호 %s | 요금: %,d원\n",
                        train, formatTime(dep), formatTime(arr), no, charge);
            }

            // 🔁 다음 페이지로 넘어갈 조건: 지금까지 본 개수가 전체보다 적을 경우
            if (page * 50 >= totalCount) break;  // 더 이상 조회할 데이터 없음
            page++; // 다음 페이지 조회
        }
    }


    // ✅ JSON 응답 파싱 메서드 (기본기)
    static JSONObject readJsonFromUrl(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // JSON은 XML처럼 태그로 감싸지 않고, 중괄호{}와 대괄호[]를 통해 구조 표현함
        // XML 예: <key>value</key> → JSON 예: "key": "value"
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line); // 응답을 한 줄씩 읽어서 문자열로 저장
            return new JSONObject(sb.toString()); // 문자열 → JSONObject 로 파싱
        }
    }

    // ✅ 시간 포맷 변환 (예: 20250601123000 → 12:30)
    static String formatTime(String time) {
        return time.substring(8, 10) + ":" + time.substring(10, 12);
    }
}
