package board.dto;

public class BoardDTO {
    private String num;        // 게시글 번호
    private String title;      // 제목
    private String content;    // 내용
    private String id;         // 작성자 ID
    private java.sql.Date postdate; // 작성일
    private int visitcount;    // 조회수
    private String name;  // 작성자 이름


    // 기본 생성자
    public BoardDTO() {}

    // getter/setter
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public java.sql.Date getPostdate() {
        return postdate;
    }
    public void setPostdate(java.sql.Date postdate) {
        this.postdate = postdate;
    }

    public int getVisitcount() {
        return visitcount;
    }
    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
