package member.dao;

import java.util.List;
import java.util.ArrayList;
import common.JDBConnect;
import member.dto.MemberDTO;

public class MemberDAO extends JDBConnect {

    // ▶ 전체 회원 목록 조회
    public List<MemberDTO> getMemberList() {
        List<MemberDTO> memberList = new ArrayList<>();
        String query = "SELECT id, email, name, phone, gender, agree, content, regidate FROM TOURIST_MEMBER";

        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();

            while (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setId(rs.getString("id"));
                member.setEmail(rs.getString("email"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setGender(rs.getString("gender"));
                member.setAgree(rs.getInt("agree"));
                member.setContent(rs.getString("content"));
                member.setRegidate(rs.getDate("regidate"));
                memberList.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberList;
    }

    // ▶ 로그인 시 회원 정보 조회 (비밀번호 트림 처리)
    public MemberDTO getMemberDTO(String id, String password) {
        MemberDTO dto = null;
        String query = "SELECT * FROM TOURIST_MEMBER WHERE id=? AND password=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, id);
            psmt.setString(2, password != null ? password.trim() : null);
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setId(rs.getString("id"));
                dto.setEmail(rs.getString("email"));
                dto.setName(rs.getString("name"));
                dto.setPassword(rs.getString("password"));
                dto.setPhone(rs.getString("phone"));
                dto.setGender(rs.getString("gender"));
                dto.setAgree(rs.getInt("agree"));
                dto.setContent(rs.getString("content"));
                dto.setRegidate(rs.getDate("regidate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    // ▶ 마이페이지용 회원 정보 조회
    public MemberDTO getMemberDTO(String id) {
        MemberDTO dto = null;
        String query = "SELECT * FROM TOURIST_MEMBER WHERE id=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, id);
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setId(rs.getString("id"));
                dto.setEmail(rs.getString("email"));
                dto.setName(rs.getString("name"));
                dto.setPassword(rs.getString("password"));
                dto.setPhone(rs.getString("phone"));
                dto.setGender(rs.getString("gender"));
                dto.setAgree(rs.getInt("agree"));
                dto.setContent(rs.getString("content"));
                dto.setRegidate(rs.getDate("regidate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    // ▶ 회원 추가
    public int insertMember(MemberDTO dto) {
        int result = 0;
        String query = "INSERT INTO TOURIST_MEMBER " +
                       "(id, email, name, password, phone, gender, agree, content, regidate) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getId());
            psmt.setString(2, dto.getEmail());
            psmt.setString(3, dto.getName());
            psmt.setString(4, dto.getPassword());
            psmt.setString(5, dto.getPhone());
            psmt.setString(6, dto.getGender());
            psmt.setInt(7, dto.getAgree());
            psmt.setString(8, dto.getContent());

            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // ▶ 회원 정보 수정
    public void updateMember(String id, String password, String email) {
        try {
            String query = "";
            if (password != null) {
                query = "UPDATE TOURIST_MEMBER SET password=? WHERE id=?";
                psmt = con.prepareStatement(query);
                psmt.setString(1, password);
                psmt.setString(2, id);
            } else if (email != null) {
                query = "UPDATE TOURIST_MEMBER SET email=? WHERE id=?";
                psmt = con.prepareStatement(query);
                psmt.setString(1, email);
                psmt.setString(2, id);
            }
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ▶ 회원 삭제
    public void deleteMember(String id, String password) {
        try {
            String query = "DELETE FROM TOURIST_MEMBER WHERE id=? AND password=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, id);
            psmt.setString(2, password);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
