package orgapi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// ✅ 시/도 정보를 담는 클래스
class Sido {
	String citycode;   // 시/도 코드
	String cityname;   // 시/도 이름

	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Sido(String citycode, String cityname) {
		this.citycode = citycode;
		this.cityname = cityname;
	}
}

public class Tago2 {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		List<String> paramList = new ArrayList<String>();  // 열차 조회용 출발/도착역 ID 저장용
        StringBuilder sb = new StringBuilder();

        // ✅ 시/도 정보 요청 및 출력 (ex: 서울, 부산 등)
        for (int i = 0; i < 2; i++) {
        	sb = getOrgApi("sido", "", null); // 시/도 목록 조회
        	parseXml(sb); // XML 파싱 결과 출력

        	// ✅ 해당 시/도의 기차역 목록 조회 (여기선 "11" → 예: 부산)
        	sb = new StringBuilder();
        	sb = getOrgApi("station", "11", null); // "11"은 cityCode
        	parseXml(sb); // 기차역 목록 출력

        	// ✅ station ID 수동 추가 (서울역, 부산역 등)
        	paramList.add("NAT010000");  // 출발역: 서울역 (예시)
        }

        // ✅ 열차 운행정보 조회: 출발일, 출발역, 도착역 기준으로 조회
        sb = getOrgApi("train", "20230403", paramList); // 날짜는 예시용
        parseXml(sb); // 열차 정보 출력
	}

	// ✅ 오픈 API 호출 메서드 (type: sido/station/train)
	public static StringBuilder getOrgApi(String type, String option ,List<String> stationList) throws IOException {
		String baseUrl = "";                  // 기본 URL
		StringBuilder urlBuilder = null;     // 완성된 요청 URL

		// 시/도 목록 조회
		if (type.equals("sido")) {
			baseUrl = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList";
			urlBuilder = new StringBuilder(baseUrl);
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=..."); // 인증키
			urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=xml");      // XML 타입으로 요청

		// 특정 시/도에 속한 기차역 목록 조회
		} else if(type.equals("station")) {
			baseUrl = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyAcctoTrainSttnList";
			urlBuilder = new StringBuilder(baseUrl);
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=...");
			urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=xml");
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=10");
	        urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode(option, "UTF-8")); // 입력된 cityCode

		// 열차 조회 (출발/도착 역, 출발일 등 필요)
		} else {
			baseUrl = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyAcctoTrainSttnList"; // ❌ 잘못된 API URL 사용중, 실제는 getStrtpntAlocFndTrainInfo여야 함
			urlBuilder = new StringBuilder(baseUrl);
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=...");
			urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=xml");
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=10");
	        urlBuilder.append("&" + URLEncoder.encode("trainGradeCode","UTF-8") + "=00");  // 차량종류 (00 = 전체)
	        urlBuilder.append("&" + URLEncoder.encode("depPlaceId","UTF-8") + "=" + URLEncoder.encode(stationList.get(0), "UTF-8")); // 출발역 ID
	        urlBuilder.append("&" + URLEncoder.encode("arrPlaceId","UTF-8") + "=" + URLEncoder.encode(stationList.get(1), "UTF-8")); // 도착역 ID
	        urlBuilder.append("&" + URLEncoder.encode("depPlandTime","UTF-8") + "=" + URLEncoder.encode(option, "UTF-8"));          // 출발일
		}

        // ✅ HTTP 요청 및 응답 처리
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // GET 방식
        conn.setRequestProperty("Content-type", "application/json"); // 응답 형식

        System.out.println(conn.getResponseCode()); // 응답코드 출력

        BufferedReader br;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

        // 응답 결과 읽기
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
        	sb.append(line);
		}

        conn.disconnect();
        br.close();

        return sb; // 응답 결과 반환
	}

	// ✅ XML 파싱 메서드 (item 태그의 내용을 출력)
	public static List<Sido> parseXml(StringBuilder sb) throws ParserConfigurationException, SAXException, IOException {
		InputStream is = new ByteArrayInputStream(sb.toString().getBytes()); // 문자열 → 입력 스트림 변환
		List<Sido> sidoList = new ArrayList<Sido>();

		// XML 파서 준비
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is); // XML 파싱

        Element root = doc.getDocumentElement(); // 최상위 루트 엘리먼트
        NodeList nlist = root.getElementsByTagName("item"); // <item> 목록 가져오기

        // 각 item 요소 반복
        for (int i = 0; i < nlist.getLength(); i++) {
			Node node = nlist.item(i);
			NodeList childNodeList = node.getChildNodes();

			// 각 item 내 하위 태그 출력
			for (int j = 0; j < childNodeList.getLength() ; j++) {
				Node child = childNodeList.item(j);
				String nName = child.getNodeName();     // 태그 이름
				String nValue = child.getTextContent(); // 값
				System.out.println(nName + " : " + nValue); // 콘솔 출력
			}
		}

        return sidoList; // (현재는 미사용)
	}
}
