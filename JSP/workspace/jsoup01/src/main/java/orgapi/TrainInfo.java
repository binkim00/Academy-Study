package orgapi;

// 열차 정보를 담는 데이터 클래스
public class TrainInfo {
    private String trainNo;     // 열차 번호
    private String trainType;   // 열차 종류 (예: KTX, 무궁화 등)
    private String depTime;     // 출발 시간 (HHMM 형식)
    private String arrTime;     // 도착 시간 (HHMM 형식)
    private String depStation;  // 출발역 이름
    private String arrStation;  // 도착역 이름
    private int charge;         // 요금 (원 단위)

    // 생성자: 필드 초기화
    public TrainInfo(String trainNo, String trainType, String depTime, String arrTime, String depStation, String arrStation, int charge) {
        this.trainNo = trainNo;
        this.trainType = trainType;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.depStation = depStation;
        this.arrStation = arrStation;
        this.charge = charge;
    }

    // Getter/Setter 메서드들
    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getDepStation() {
        return depStation;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    // 객체 출력 시 정보를 보기 좋게 구성
    @Override
    public String toString() {
        return String.format("[%s] %s -> %s | %s~%s | 요금: %,d원",
            trainType, depStation, arrStation, depTime, arrTime, charge);
    }
}
