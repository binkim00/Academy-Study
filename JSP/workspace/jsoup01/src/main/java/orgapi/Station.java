package orgapi;

// 역(Station) 정보를 담는 데이터 클래스
public class Station {
    private String stationCode;  // 역 코드 (예: NAT010000)
    private String stationName;  // 역 이름 (예: 서울역)

    // 생성자: 역 코드와 이름을 받아 초기화
    public Station(String stationCode, String stationName) {
        this.stationCode = stationCode;
        this.stationName = stationName;
    }

    // 역 코드 반환 (예: NAT123456)
    public String getStationCode() {
        return stationCode;
    }

    // 역 코드 설정
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    // 역 이름 반환 (예: 서울역)
    public String getStationName() {
        return stationName;
    }

    // 역 이름 설정
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    // 객체를 문자열로 표현할 때 자동 호출됨 (디버깅 또는 출력 용도)
    @Override
    public String toString() {
        return stationName + "(" + stationCode + ")";
    }
}