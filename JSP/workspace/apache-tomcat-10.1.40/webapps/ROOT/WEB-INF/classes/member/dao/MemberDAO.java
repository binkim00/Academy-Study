package member.dao;

import common.DBConnPool;
import member.dto.MemberDTO;

public class MemberDAO extends DBConnPool{
	public MemberDTO getMember(String userId, String userPw) {
		MemberDTO dto = new MemberDTO();
		try {
			String query = "SELECT * FROM tourist_member WHERE id=? and password=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			psmt.setString(2, userPw);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setGender(rs.getString("gender"));
				dto.setAgree(rs.getBoolean("agree"));
				dto.setContent(rs.getString("content"));
				dto.setRegidate(rs.getString("regidate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void insertMember(MemberDTO dto) {
		try {
			String query = "INSERT INTO tourist_member(id,email,name,password,phone,gender,agree,content) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getEmail());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getPassword());
			psmt.setString(5, dto.getPhone());
			psmt.setString(6, dto.getGender());
			psmt.setBoolean(7, dto.isAgree());
			psmt.setString(8, dto.getContent());
			psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
