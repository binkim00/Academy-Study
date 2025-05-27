package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.google.gson.Gson;

public class q2 {

    static class Job {
        private String bsnsNm;
        private String performInst;
        private String bsnsKind;
        private String tel;
        private int people;
        private String bsnsMainActvt;
        private String bsnsSdate;
        private String bsnsFdate;
        private String gugun;
        private String dataDay;

        public String getBsnsNm() { return bsnsNm; }
        public String getPerformInst() { return performInst; }
        public int getPeople() { return people; }
        public String getBsnsMainActvt() { return bsnsMainActvt; }
        public String getGugun() { return gugun; }
    }

    static class ApiResult {
        Response response;
    }

    static class Response {
        Body body;
    }

    static class Body {
        Items items;
        int totalCount;
    }

    static class Items {
        List<Job> item;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String serviceKey = "sGd1gU4DHYuJ2ivcraGO5%2BuBqk%2BtiY135P%2FT2%2BzIKR0QkrS2%2F8cUr5noy9Mhybw%2BFoP%2BEOzMfcEpjQqW7c5tMg%3D%3D";
        int numOfRows = 100;

        String url = "http://apis.data.go.kr/6260000/BusanTblAgedjobStusService/getTblAgedjobStusInfo"
                   + "?serviceKey=" + serviceKey
                   + "&pageNo=1&numOfRows=" + numOfRows
                   + "&resultType=json";

        ApiResult firstPage = getApiResult(url);
        int totalCount = firstPage.response.body.totalCount;
        int totalPage = (int) Math.ceil(totalCount / (double) numOfRows);

        List<Job> allJobs = new ArrayList<>();

        for (int page = 1; page <= totalPage; page++) {
            String requestUrl = "http://apis.data.go.kr/6260000/BusanTblAgedjobStusService/getTblAgedjobStusInfo"
                              + "?serviceKey=" + serviceKey
                              + "&pageNo=" + page
                              + "&numOfRows=" + numOfRows
                              + "&resultType=json";

            ApiResult result = getApiResult(requestUrl);
            if (result.response.body.items.item == null) continue;

            allJobs.addAll(result.response.body.items.item);
        }

        Set<String> gugunSet = new TreeSet<>();
        for (Job job : allJobs) gugunSet.add(job.getGugun());

        System.out.println("=== 행정지역 목록 ===");
        for (String gugun : gugunSet) System.out.println("- " + gugun);

        while (true) {
            System.out.print("\n행정지역을 입력하세요 ('종료' 입력 시 종료): ");
            String inputGugun = scanner.nextLine().trim();

            if (inputGugun.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (!gugunSet.contains(inputGugun)) {
                System.out.println("입력한 지역은 목록에 없습니다. 다시 입력해주세요.");
                continue;
            }

            System.out.println("\n[" + inputGugun + "] 지역의 일자리 정보:");
            boolean found = false;
            for (Job job : allJobs) {
                if (inputGugun.equals(job.getGugun())) {
                    System.out.println("사업명: " + job.getBsnsNm());
                    System.out.println("수행기관: " + job.getPerformInst());
                    System.out.println("모집인원: " + job.getPeople() + "명");
                    System.out.println("주요활동: " + job.getBsnsMainActvt());
                    System.out.println("---------------------------");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("해당 지역의 사업 정보가 없습니다.");
            }
        }

        scanner.close();
    }

    private static ApiResult getApiResult(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        Gson gson = new Gson();
        return gson.fromJson(sb.toString(), ApiResult.class);
    }
}
