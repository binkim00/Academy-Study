package orgapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

// 🔸 열차 조회 메인 프로그램 클래스
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) { // try-with-resources로 Scanner 자동 닫힘
            while (true) {
                try {
                    // ① 시/도 목록 조회
                    List<City> cities = CityParser.getCityList();
                    System.out.println("🔹 시/도 목록:");
                    for (City city : cities) {
                        System.out.println("- " + city.getCityName() + " (" + city.getCityCode() + ")");
                    }

                    // ② 출발 시/도 선택 (이름 또는 코드 입력)
                    City depCity = null;
                    while (depCity == null) {
                        System.out.print("\n출발할 시/도 이름 또는 코드 입력: ");
                        String input = sc.nextLine().trim();
                        for (City city : cities) {
                            if (city.getCityCode().equals(input) || city.getCityName().contains(input)) {
                                depCity = city;
                                break;
                            }
                        }
                        if (depCity == null) System.out.println("❌ 존재하지 않는 시/도입니다.");
                    }

                    // ③ 출발역 목록 조회
                    List<Station> depStations = StationParser.getStationList(depCity.getCityCode());
                    System.out.println("\n🚉 출발역 목록:");
                    for (Station station : depStations) {
                        System.out.println("- " + station.getStationName() + " (" + station.getStationCode() + ")");
                    }

                    // ④ 출발역 선택 (이름 일부로 검색)
                    String depCode = null;
                    System.out.print("\n출발역 이름 입력: ");
                    String inputDep = sc.nextLine().trim();
                    for (Station station : depStations) {
                        if (station.getStationName().contains(inputDep)) {
                            depCode = station.getStationCode();
                            break;
                        }
                    }
                    if (depCode == null) {
                        System.out.println("❌ 해당 출발역을 찾을 수 없습니다.");
                        System.out.println("👉 처음부터 다시 시도해주세요.\n");
                        continue; // 🔁 전체 처음부터 반복
                    }

                    // ⑤ 도착 시/도 선택 (출발과 동일한 방식)
                    City arrCity = null;
                    while (arrCity == null) {
                        System.out.println("\n🔹 도착 가능한 시/도 목록:");
                        for (City city : cities) {
                            System.out.println("- " + city.getCityName() + " (" + city.getCityCode() + ")");
                        }

                        System.out.print("\n도착할 시/도 이름 또는 코드 입력: ");
                        String input = sc.nextLine().trim();

                        for (City city : cities) {
                            if (city.getCityCode().equals(input) || city.getCityName().contains(input)) {
                                arrCity = city;
                                break;
                            }
                        }

                        if (arrCity == null) {
                            System.out.println("❌ 존재하지 않는 시/도입니다.");
                        }
                    }

                    // ⑥ 도착역 목록 조회
                    List<Station> arrStations = StationParser.getStationList(arrCity.getCityCode());
                    System.out.println("\n🚉 도착역 목록:");
                    for (Station station : arrStations) {
                        System.out.println("- " + station.getStationName() + " (" + station.getStationCode() + ")");
                    }

                    // ⑦ 도착역 선택
                    String arrCode = null;
                    System.out.print("\n도착역 이름 입력: ");
                    String inputArr = sc.nextLine().trim();
                    for (Station station : arrStations) {
                        if (station.getStationName().contains(inputArr)) {
                            arrCode = station.getStationCode();
                            break;
                        }
                    }
                    if (arrCode == null) {
                        System.out.println("❌ 해당 도착역을 찾을 수 없습니다.");
                        System.out.println("👉 처음부터 다시 시도해주세요.\n");
                        continue; // 🔁 전체 처음부터 반복
                    }

                    // ⑧ 노선 미리보기 (오늘 날짜 기준)
                    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                    List<TrainInfo> preview = TrainParser.getTrainList(depCode, arrCode, today);
                    if (preview.isEmpty()) {
                        System.out.println("\n❌ 해당 출발역-도착역 노선은 존재하지 않습니다.");
                        System.out.println("👉 다시 처음부터 선택해주세요.\n");
                        continue;
                    }

                    // ⑨ 출발일 입력
                    System.out.print("\n✅ 노선이 확인되었습니다! 출발일을 입력해주세요 (예: 20240521): ");
                    String date = sc.nextLine().trim();

                    // ⑩ 열차 정보 조회
                    List<TrainInfo> trains = TrainParser.getTrainList(depCode, arrCode, date);

                    // ⑪ 시간 필터 입력 (예: 10시 이후)
                    System.out.print("\n몇 시 이후 열차만 조회할까요? (예: 10): ");
                    int afterHour = Integer.parseInt(sc.nextLine().trim());

                    // ⑫ 결과 출력
                    System.out.println("\n🔍 조회된 열차 목록:");
                    boolean found = false;
                    for (TrainInfo train : trains) {
                        int hour = Integer.parseInt(train.getDepTime().substring(0, 2));
                        if (hour >= afterHour) {
                            System.out.println(train);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("📭 해당 시간 이후의 열차가 없습니다.");
                    }

                    break; // 🔚 정상 종료 → while문 빠져나감

                } catch (Exception innerEx) {
                    System.out.println("❗ 오류 발생. 처음부터 다시 시도해주세요.\n");
                }
            }
        } catch (Exception e) {
            System.out.println("❗ 시스템 오류:");
            e.printStackTrace();
        }
    }
}