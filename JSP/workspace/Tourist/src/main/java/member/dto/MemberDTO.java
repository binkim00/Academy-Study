package member.dto;

import java.sql.Date;  // REGIDATE 컬럼 대응

public class MemberDTO {
    // 필드 선언 (테이블의 각 컬럼에 해당)
    private String id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private int agree;
    private String content;
    private Date regidate;

    // 기본 생성자
    public MemberDTO() {}

    // Getter / Setter 메서드들

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAgree() {
        return agree;
    }
    public void setAgree(int agree) {
        this.agree = agree;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegidate() {
        return regidate;
    }
    public void setRegidate(Date regidate) {
        this.regidate = regidate;
    }
}
