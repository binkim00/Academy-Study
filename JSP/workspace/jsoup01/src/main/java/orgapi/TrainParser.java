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

// 출발역, 도착역, 출발일을 기반으로 열차 목록을 파싱하는 클래스
public class TrainParser {

    // 열차 정보 목록을 반환하는 메서드
    public static List<TrainInfo> getTrainList(String depCode, String arrCode, String depDate) throws Exception {
        List<TrainInfo> list = new ArrayList<>();

        // API 요청 URL 구성
        String urlStr = "http://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo"
                + "?serviceKey=sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D"
                + "&depPlaceId=" + URLEncoder.encode(depCode, "UTF-8")
                + "&arrPlaceId=" + URLEncoder.encode(arrCode, "UTF-8")
                + "&depPlandTime=" + URLEncoder.encode(depDate, "UTF-8")
                + "&numOfRows=100";

        // HTTP 요청/응답 처리
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

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

        // <item> 태그 추출
        NodeList nodeList = document.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // 태그에서 데이터 추출
                String trainNo = element.getElementsByTagName("trainno").item(0).getTextContent();
                String trainType = element.getElementsByTagName("traingradename").item(0).getTextContent();
                String depTime = element.getElementsByTagName("depplandtime").item(0).getTextContent().substring(8, 12);
                String arrTime = element.getElementsByTagName("arrplandtime").item(0).getTextContent().substring(8, 12);
                String depStation = element.getElementsByTagName("depplacename").item(0).getTextContent();
                String arrStation = element.getElementsByTagName("arrplacename").item(0).getTextContent();
                int charge = Integer.parseInt(element.getElementsByTagName("adultcharge").item(0).getTextContent());

                // TrainInfo 객체로 리스트에 추가
                list.add(new TrainInfo(trainNo, trainType, depTime, arrTime, depStation, arrStation, charge));
            }
        }

        return list; // 열차 목록 반환
    }
}
