package orgapi;

// 시/도 정보를 담는 클래스
public class City {
    private String cityCode;  // 시/도 코드 (예: 서울=11, 경기=41 등)
    private String cityName;  // 시/도 이름 (예: 서울특별시, 경기도 등)

    // 생성자: 객체 생성 시 시/도 코드와 이름을 설정
    public City(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    // cityCode 필드의 값을 반환하는 getter 메서드
    public String getCityCode() {
        return cityCode;
    }

    // cityCode 필드의 값을 설정하는 setter 메서드
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    // cityName 필드의 값을 반환하는 getter 메서드
    public String getCityName() {
        return cityName;
    }

    // cityName 필드의 값을 설정하는 setter 메서드
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // 객체를 문자열로 표현할 때 자동으로 호출되는 메서드 (디버깅이나 출력용)
    @Override
    public String toString() {
        return cityName + "(" + cityCode + ")";
    }
}
