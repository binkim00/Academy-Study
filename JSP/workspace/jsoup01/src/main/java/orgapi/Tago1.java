package orgapi;
//필요한 클래스들을 import (가져오기)
import java.io.InputStreamReader;        // 응답 데이터를 문자 단위로 읽기 위해 사용
import java.net.HttpURLConnection;       // 실제 API와 HTTP 연결을 하기 위해 사용
import java.net.URL;                     // 웹 주소(URL)를 다루기 위한 클래스
import java.net.URLEncoder;              // 한글이나 특수문자를 웹에서 안전하게 사용할 수 있도록 인코딩
import java.io.BufferedReader;           // 응답을 한 줄씩 읽어오기 위해 사용
import java.io.IOException;              // 입출력 관련 예외 처리를 위해 사용

//메인 클래스 정의
public class Tago1 {

 // 프로그램이 시작되는 main 메서드
 public static void main(String[] args) throws IOException {

     // 1. 공공데이터포털에서 발급받은 '인증키'를 문자열로 저장함
     // 이 키는 오픈 API를 사용할 수 있도록 허가해주는 열쇠 같은 역할을 함
     String serviceKey = "sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D";

     // 2. 오픈API 요청 주소를 StringBuilder로 하나씩 붙여서 만들기 시작
     StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo");

     // 3. 요청에 필요한 정보들(=파라미터들)을 하나씩 URL 뒤에 붙임
     // 모든 파라미터는 웹에 안전하게 보내기 위해 URLEncoder.encode()로 감쌈
     // 3-1. 인증키 붙이기 (반드시 포함되어야 함)
     urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
     // 3-2. 페이지 번호 (보통 첫 페이지부터 조회하므로 1)
     urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
     // 3-3. 한 페이지에 몇 개의 데이터를 보여줄지 설정 (여기선 10개)
     urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
     // 3-4. 응답 데이터 형식 선택 (xml 또는 json 중 하나, 여기선 xml로 설정)
     urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
     // 3-5. 출발역의 ID를 입력 (예: 서울역 = NAT010000)
     urlBuilder.append("&" + URLEncoder.encode("depPlaceId", "UTF-8") + "=" + URLEncoder.encode("NAT010000", "UTF-8"));
     // 3-6. 도착역의 ID를 입력 (예: 대전역 = NAT011668)
     urlBuilder.append("&" + URLEncoder.encode("arrPlaceId", "UTF-8") + "=" + URLEncoder.encode("NAT011668", "UTF-8"));
     // 3-7. 원하는 출발 날짜 입력 (형식은 YYYYMMDD, 예: 2025년 5월 21일)
     urlBuilder.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode("20230403", "UTF-8"));
     // 3-8. 차량 종류 코드 입력 (00은 KTX, 필수가 아니지만 넣으면 더 정확함)
     urlBuilder.append("&" + URLEncoder.encode("trainGradeCode", "UTF-8") + "=" + URLEncoder.encode("00", "UTF-8"));

     
     // 4. 위에서 만든 주소(StringBuilder)를 URL 객체로 변환
     URL url = new URL(urlBuilder.toString());
     // 5. URL로 HTTP 연결을 만듦
     HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 연결 객체 생성
     conn.setRequestMethod("GET"); // GET 방식으로 요청함 (보통 API는 GET 방식)
     conn.setRequestProperty("Content-type", "application/xml"); // 응답 데이터를 xml로 받겠다고 서버에 알려줌

     // 6. 서버로부터 받은 응답 코드 확인 (200번대면 정상, 400~500번은 에러)
     System.out.println("🔍 Response code: " + conn.getResponseCode());

     // 7. 서버 응답을 받을 준비 (성공인지 실패인지에 따라 다르게 읽음)
     BufferedReader rd;
     if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         // 성공 시: 일반 입력 스트림으로 읽기
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     } else {
         // 실패 시: 에러 스트림으로 읽기
         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
     }

     // 8. 읽어온 데이터를 한 줄씩 StringBuilder에 저장함
     StringBuilder sb = new StringBuilder();
     String line;
     while ((line = rd.readLine()) != null) {
         sb.append(line).append("\n"); // 줄 바꿈 포함해서 붙이기
     }

     // 9. 리소스 정리 (읽기 종료, 연결 종료)
     rd.close();
     conn.disconnect();

     // 10. 최종 결과(XML 형식 응답)를 출력
     System.out.println("📦 응답 결과:\n" + sb.toString());
 }
}
