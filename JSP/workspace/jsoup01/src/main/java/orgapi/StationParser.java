package orgapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// 시/도 코드에 해당하는 역 목록을 API에서 가져와 파싱하는 클래스
public class StationParser {

    // 특정 시/도 코드에 해당하는 역 목록을 가져오는 메서드
    public static List<Station> getStationList(String cityCode) throws Exception {
        List<Station> list = new ArrayList<>(); // 결과 리스트

        // API 요청 URL 생성 (인코딩 필수)
        String urlStr = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyAcctoTrainSttnList"
                + "?serviceKey=sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D"
                + "&cityCode=" + URLEncoder.encode(cityCode, "UTF-8")
                + "&numOfRows=100";

        // HTTP 연결 설정
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // 응답 읽기
        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        // XML 파싱 준비
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new java.io.ByteArrayInputStream(sb.toString().getBytes()));

        // <item> 태그 목록 가져오기
        NodeList nodeList = document.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // 역 ID와 이름 추출
                String nodeId = element.getElementsByTagName("nodeid").item(0).getTextContent();
                String nodeName = element.getElementsByTagName("nodename").item(0).getTextContent();

                // Station 객체로 변환해 리스트에 추가
                list.add(new Station(nodeId, nodeName));
            }
        }

        return list; // 결과 반환
    }
}
