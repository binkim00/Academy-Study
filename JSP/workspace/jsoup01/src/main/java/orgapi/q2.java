package orgapi; // 현재 파일의 패키지 이름

// 필요한 라이브러리 import
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*; // List, Set, ArrayList, TreeSet, Scanner 등을 사용
import com.google.gson.Gson; // JSON 데이터를 자바 객체로 변환하기 위한 Gson 라이브러리

public class q2 { // 프로그램의 메인 클래스

    // ✅ Job 클래스: JSON에서 개별 일자리 데이터를 담을 클래스
    static class Job {
        // JSON 응답에서 매핑될 항목들 (필드 이름은 JSON과 정확히 일치해야 함)
        private String bsnsNm;           // 사업명
        private String performInst;      // 수행기관
        private String bsnsKind;         // 사업종류
        private String tel;              // 연락처
        private int people;              // 모집인원
        private String bsnsMainActvt;    // 주요활동
        private String bsnsSdate;        // 시작일
        private String bsnsFdate;        // 종료일
        private String gugun;            // 행정구 (예: 해운대구)
        private String dataDay;          // 데이터 기준일자

        // Getter 메서드들 → 외부에서 데이터를 가져올 때 사용
        public String getBsnsNm() { return bsnsNm; }
        public String getPerformInst() { return performInst; }
        public int getPeople() { return people; }
        public String getBsnsMainActvt() { return bsnsMainActvt; }
        public String getGugun() { return gugun; }
    }

    // ✅ 아래는 JSON 구조와 동일한 계층 구조를 표현한 클래스들
    static class ApiResult {
        Response response; // 최상위 JSON 객체의 response 필드
    }

    static class Response {
        Body body; // response 안의 body 필드
    }

    static class Body {
        Items items;     // body 안의 items 필드
        int totalCount;  // 전체 데이터 개수
    }

    static class Items {
        List<Job> item;  // 실제 Job 데이터들이 들어 있는 배열 (JSON의 item)
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체

        // ✅ API 호출을 위한 정보 세팅
        String serviceKey = "sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D";
        int numOfRows = 100; // 한 페이지에 요청할 데이터 수

        // ✅ 첫 페이지를 호출해서 전체 데이터 개수를 먼저 확인
        String url = "http://apis.data.go.kr/6260000/BusanTblAgedjobStusService/getTblAgedjobStusInfo"
                   + "?serviceKey=" + serviceKey
                   + "&pageNo=1&numOfRows=" + numOfRows
                   + "&resultType=json";

        ApiResult firstPage = getApiResult(url); // API 호출 및 JSON 파싱
        int totalCount = firstPage.response.body.totalCount; // 전체 데이터 개수
        int totalPage = (int) Math.ceil(totalCount / (double) numOfRows); // 페이지 수 계산

        // ✅ 모든 Job 데이터를 모아둘 리스트
        List<Job> allJobs = new ArrayList<>();

        // ✅ 모든 페이지를 반복해서 전체 데이터를 수집
        for (int page = 1; page <= totalPage; page++) {
            String requestUrl = "http://apis.data.go.kr/6260000/BusanTblAgedjobStusService/getTblAgedjobStusInfo"
                              + "?serviceKey=" + serviceKey
                              + "&pageNo=" + page
                              + "&numOfRows=" + numOfRows
                              + "&resultType=json";

            ApiResult result = getApiResult(requestUrl); // 해당 페이지의 API 결과 받아오기

            if (result.response.body.items.item == null) continue; // 데이터 없으면 skip

            allJobs.addAll(result.response.body.items.item); // 리스트에 전체 추가
        }

        // ✅ 중복 없는 행정구 목록을 TreeSet으로 정리 (자동 정렬됨)
        Set<String> gugunSet = new TreeSet<>();
        for (Job job : allJobs) gugunSet.add(job.getGugun());

        // ✅ 행정지역 목록 출력
        System.out.println("=== 행정지역 목록 ===");
        for (String gugun : gugunSet) System.out.println("- " + gugun);

        // ✅ 사용자 입력 루프 (종료 전까지 계속 반복)
        while (true) {
            System.out.print("\n행정지역을 입력하세요 ('종료' 입력 시 종료): ");
            String inputGugun = scanner.nextLine().trim(); // 사용자 입력 받기

            if (inputGugun.equals("종료")) { // 종료 조건
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (!gugunSet.contains(inputGugun)) { // 유효한 지역인지 검사
                System.out.println("입력한 지역은 목록에 없습니다. 다시 입력해주세요.");
                continue;
            }

            // ✅ 해당 지역 일자리 정보 출력
            System.out.println("\n[" + inputGugun + "] 지역의 일자리 정보:");
            boolean found = false;

            for (Job job : allJobs) {
                if (inputGugun.equals(job.getGugun())) {
                    // 원하는 필드들만 출력
                    System.out.println("사업명: " + job.getBsnsNm());
                    System.out.println("수행기관: " + job.getPerformInst());
                    System.out.println("모집인원: " + job.getPeople() + "명");
                    System.out.println("주요활동: " + job.getBsnsMainActvt());
                    System.out.println("---------------------------");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("해당 지역의 사업 정보가 없습니다."); // 실제로는 거의 안 뜰 가능성이 높음
            }
        }

        scanner.close(); // Scanner 닫기 (자원 정리)
    }

    // ✅ API 호출 → 응답을 JSON으로 받아서 → ApiResult 객체로 파싱해주는 메서드
    private static ApiResult getApiResult(String urlStr) throws Exception {
        URL url = new URL(urlStr); // 문자열을 URL 객체로 변환
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // HTTP 연결 열기
        conn.setRequestMethod("GET"); // GET 방식으로 설정

        // 응답 스트림을 읽기 위한 BufferedReader 생성 (UTF-8 인코딩)
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder(); // 전체 문자열 저장할 StringBuilder

        String line;
        while ((line = br.readLine()) != null) sb.append(line); // 한 줄씩 읽어서 붙이기
        br.close(); // 스트림 닫기

        // JSON 문자열을 ApiResult 객체로 변환
        Gson gson = new Gson();
        return gson.fromJson(sb.toString(), ApiResult.class);
    }
}
