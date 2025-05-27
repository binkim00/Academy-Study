package program.dto;

public class ProgramDTO {
    private int id;             // 프로그램 고유 ID
    private String idx;         // 다른 식별자 (있다면 사용)
    private String title;       // 제목
    private String area;        // 지역
    private String schedule;    // 일정
    private int price;          // 가격
    private String content;     // 본문/상세 설명
    private String text;        // 간략 설명
    private String subtext;     // 추가 설명
    private String img;         // 이미지 경로

    // 기본 생성자
    public ProgramDTO() {}

    // getter & setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIdx() {
        return idx;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }
    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
