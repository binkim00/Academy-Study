package orgapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// 시/도 정보를 API에서 불러와서 City 객체 리스트로 파싱하는 클래스
public class CityParser {

    // 정적 메서드: 시/도 목록을 가져옴
    public static List<City> getCityList() throws Exception {
        List<City> list = new ArrayList<>(); // 결과를 저장할 리스트 생성

        // 호출할 OpenAPI 주소
        String urlStr = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList?serviceKey=sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D";

        // URL 연결 설정
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // 응답 코드가 200(정상)이면 InputStream 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream()));

        // 응답 결과 문자열로 읽기
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        // XML 파싱 준비
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new java.io.ByteArrayInputStream(sb.toString().getBytes()));

        // 원하는 태그 목록 가져오기
        NodeList nodeList = document.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // 각 item에서 코드와 이름 가져오기
                String cityCode = element.getElementsByTagName("citycode").item(0).getTextContent();
                String cityName = element.getElementsByTagName("cityname").item(0).getTextContent();

                // City 객체 생성 후 리스트에 추가
                list.add(new City(cityCode, cityName));
            }
        }

        return list; // 파싱된 시/도 리스트 반환
    }
}
